package com.example.kadokadoscrapper;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class KadoKado {

    static String getSid(String login, String pass) throws IOException {
        Document twinoidLogin = Jsoup.connect("https://twinoid.com/user/login")
                .data("login", login)
                .data("pass", pass)
                .data("submit", "Login")
                .data("host", "www.kadokado.com")
                .data("mid", "")
                .post();

        String url = twinoidLogin.select("input[name='url']")
                .attr("value");

        String twSid = twinoidLogin.select("input[name='sid']")
                .attr("value");

        Connection.Response userRedir = Jsoup.connect("https://twinoid.com/user/redir")
                .cookie("tw_sid", twSid)
                .data("login", login)
                .data("pass", pass)
                .data("url", url)
                .method(Connection.Method.GET)
                .execute();

        return userRedir.cookie("sid");
    }
}
