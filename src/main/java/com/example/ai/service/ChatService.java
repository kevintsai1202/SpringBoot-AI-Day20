package com.example.ai.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.stereotype.Service;

import com.example.ai.advistor.TokenUsageLogAdvistor;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class ChatService {
	private final ChatClient chatClient;
	private final ChatMemory chatMemory = new InMemoryChatMemory();
	
	public String chat(String chatId, String userMessage) {	   
		return this.chatClient.prompt()
		.advisors(new MessageChatMemoryAdvisor(chatMemory, chatId, 30), new TokenUsageLogAdvistor())
		.advisors(context -> {context.param("chatId", chatId);
		context.param("lastN", 30);
						})
		.user(userMessage)
        .call().content();
	}
}
