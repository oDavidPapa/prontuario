package com.ufes.prontuario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication
public class ProntuarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProntuarioApplication.class, args);
	}
}
