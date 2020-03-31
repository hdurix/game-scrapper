package com.example.kadokadoscrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class KadoKado {

    private static final Logger logger = LoggerFactory.getLogger(KadoKado.class);

    @Value("${LOGIN}")
    private String login;

    @Value("${PASS}")
    private String pass;

    @PostConstruct
    public void connect() {
        try {
            tryConnection();
        } catch (IOException e) {
            logger.error("Could not run the official example", e);
        }
    }

    private void tryConnection() throws IOException {

        String sid = KadoKadoLogin.getSid(login, pass);

        Document connected = Jsoup.connect("http://www.kadokado.com/user/40813")
                .cookie("sid", sid)
                .get();

        logger.info("connected: {}", connected.toString().contains("Crepuscud"));
    }

}
