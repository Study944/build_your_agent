package com.buildyouragent;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BuildYourAgentApplicationTests {

    @Resource
    private ChatModel dashscopeChatModel;

    @Test
    void testSpringAI(){
        ChatClient chatClient = ChatClient.builder(dashscopeChatModel).build();
        InMemoryChatMemory chatMemory = new InMemoryChatMemory();
        ChatResponse chatResponse = chatClient.prompt()
                .user("你好，我是一名个人开发者Zxc")
                .advisors(new MessageChatMemoryAdvisor(chatMemory))
                .call()
                .chatResponse();
        System.out.println(chatResponse.getResult().getOutput().getText());
    }
}
