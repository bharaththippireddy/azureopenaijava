package com.bharath.azureai.text;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.*;
import com.azure.core.credential.AzureKeyCredential;

import java.util.ArrayList;
import java.util.List;

public class TextDemo {
    public static void main(String[] args) {
        OpenAIClient client = new OpenAIClientBuilder()
                .endpoint("https://neyahopenai.openai.azure.com/")
                .credential(new AzureKeyCredential(System.getenv("OPENAI_API_KEY")))
                .buildClient();

        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage(ChatRole.SYSTEM,"You are a Data Science tutor"));
        messages.add(new ChatMessage(ChatRole.USER,"What is AI"));

        ChatCompletionsOptions options = new ChatCompletionsOptions(messages);
        options.setN(3);

        ChatCompletions response = client.getChatCompletions("text_demo", options);
        System.out.println("Id: "+response.getId());
        System.out.println("Created Time: "+response.getCreatedAt());
        for (ChatChoice choice:response.getChoices()){
            ChatMessage message = choice.getMessage();
            ChatRole role = message.getRole();
            String content = message.getContent();
            System.out.println("Role: "+role );
            System.out.println("Content: "+content );


        }
    }
}
