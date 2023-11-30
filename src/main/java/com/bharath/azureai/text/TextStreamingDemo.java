package com.bharath.azureai.text;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.*;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.IterableStream;

import java.util.ArrayList;
import java.util.List;

public class TextStreamingDemo {
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

        IterableStream<ChatCompletions> response = client.getChatCompletionsStream("text_demo", options);
        response.stream().skip(1).forEach(chatCompletions -> {
            ChatMessage delta = chatCompletions.getChoices().get(0).getDelta();
            if(delta.getContent()!=null){
                System.out.println(delta.getContent());
            }
        });
    }
}
