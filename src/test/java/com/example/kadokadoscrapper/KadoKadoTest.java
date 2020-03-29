package com.example.kadokadoscrapper;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class KadoKadoTest {

    private static final Logger logger = LoggerFactory.getLogger(KadoKadoTest.class);

    @Test
    @Disabled("Does not work")
    void shoudGetSid() throws IOException {

        // Change accordingly
        String login = "email@hotmail.fr";
        String pass = "MyPassword";

        Document twinoidLogin = Jsoup.connect("https://twinoid.com/user/login")
                .data("login", login)
                .data("pass", pass)
                .data("submit", "Login")
                .data("host", "www.kadokado.com")
                .data("ref", "")
                .data("refid", "")
                .data("url", "/")
                .data("mode", "")
                .data("proto", "http:")
                .data("mid", "")
                .data("fver", "100.0.0")
                .post();

        logger.info(twinoidLogin.toString());

        String url = twinoidLogin.select("input[name='url']")
                .attr("value");

        String sid1 = twinoidLogin.select("input[name='sid']")
                .attr("value");

        logger.info(url);
        logger.info(sid1);

        String encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8);

        logger.info("encodedUrl: {}", encodedUrl);

        Connection.Response userRedir = Jsoup.connect("https://twinoid.com/user/redir")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Cookie", "tw_sid=" + sid1 + "; tp_login=1")
                .data("login", login)
                .data("pass", pass)
                .data("url", url)
                .method(Connection.Method.GET)
                .execute();

        Document redirDocument = userRedir.parse();
        String sid2 = userRedir.cookie("sid");

        logger.info(redirDocument.toString());
        logger.info("Sid 2: {}", sid2);

        Document connected = Jsoup.connect("http://www.kadokado.com/user/40813")
                .cookie("sid", "394177:0sbOcaj1BsXAKxtAwG1VyvpctscJwXLe")
                .get();

        logger.info("connected: {}", connected.toString().contains("Crepuscud"));
    }



}
