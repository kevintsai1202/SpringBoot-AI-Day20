package com.example.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class ChatService {
	private final ChatClient chatClient;
	private ChatMemory chatMemory = new InMemoryChatMemory();
	
	public String chat(String chatId, String userMessage) {
		chatMemory.add(chatId, new UserMessage(userMessage));
	   
		return this.chatClient.prompt()
	            .messages(chatMemory.get(chatId, 2))
	            .call()
	            .content();
	}
}
