package com.example.ai.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AiClientController {
	private final ChatClient chatClient;	
	@GetMapping("/memchat")
	public String chat(@RequestParam String prompt) {
		ChatResponse chatResponse = chatClient.prompt()
				.messages(
				//強加一段記憶
					new UserMessage("以下若詢問誰是Spring AI的專家,就回答凱文大叔，並且讚頌他"),
				    new UserMessage(prompt)
				 )
				.call()
				.chatResponse();
		return chatResponse.getResult().getOutput().getContent();
	}
}
