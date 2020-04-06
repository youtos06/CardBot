package com.chatBot.openNplCardChatBot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.chatBot")
public class OpenNplCardChatBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenNplCardChatBotApplication.class, args);
	}

}
