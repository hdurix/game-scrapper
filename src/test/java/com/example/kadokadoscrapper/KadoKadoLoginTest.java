package com.example.kadokadoscrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

public class KadoKadoLoginTest {

    @Test
    void shoudGetSid() throws IOException {

        // Change accordingly
        String login = "email@hotmail.fr";
        String pass = "MyPassword";

        String sid = KadoKadoLogin.getSid(login, pass);

        assertThat(sid).isNotNull();

        Document connected = Jsoup.connect("http://www.kadokado.com/user/40813")
                .cookie("sid", sid)
                .get();

        assertThat(connected.toString().contains("Crepuscud")).isTrue();

        Elements imgs = connected.select("td.tiny img");

        for (Element img : imgs) {
            System.out.println(img.attr("alt"));
        }

    }

}
