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
public class JsoupExample {

    private static final Logger logger = LoggerFactory.getLogger(JsoupExample.class);

    @Value("${SID}")
    private String sid;

    @PostConstruct
    public void connect() {
        try {
            tryConnection();
        } catch (IOException e) {
            logger.error("Could not run the official example", e);
        }
    }

    private void tryConnection() throws IOException {
        Document connected = Jsoup.connect("http://www.kadokado.com/user/40813")
                .cookie("sid", sid)
                .get();

        logger.info("connected: {}", connected.toString().contains("Crepuscud"));
    }

}
