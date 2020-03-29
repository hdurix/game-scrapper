package com.example.kadokadoscrapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KadokadoScrapperApplication {

	public static void main(String[] args) {
		SpringApplication.run(KadokadoScrapperApplication.class, args);

		JsoupExample.officialExample();
	}

}
