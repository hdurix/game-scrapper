package com.example.kadokadoscrapper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsoupExample {

    private static final Logger logger = LoggerFactory.getLogger(JsoupExample.class);

    public static void officialExample() {
        try {
            tryOfficialExample();
        } catch (IOException e) {
            logger.error("Could not run the official example", e);
        }
    }

    private static void tryOfficialExample() throws IOException {
        Document doc = Jsoup.connect("https://en.wikipedia.org/").get();
        logger.info("Title is: {}",  doc.title());
        Elements newsHeadlines = doc.select("#mp-itn b a");
        for (Element headline : newsHeadlines) {
            logger.info("{} -> {}", headline.attr("title"), headline.absUrl("href"));
        }
    }
}
