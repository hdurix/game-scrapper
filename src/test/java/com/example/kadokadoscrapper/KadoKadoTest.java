package com.example.kadokadoscrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.*;

public class KadoKadoTest {

    @Test
    void shoudGetSid() throws IOException {

        // Change accordingly
        String login = "email@hotmail.fr";
        String pass = "MyPassword";

        String sid = KadoKado.getSid(login, pass);

        assertThat(sid).isNotNull();

        Document connected = Jsoup.connect("http://www.kadokado.com/user/40813")
                .cookie("sid", sid)
                .get();

        assertThat(connected.toString().contains("Crepuscud")).isTrue();
    }

}
