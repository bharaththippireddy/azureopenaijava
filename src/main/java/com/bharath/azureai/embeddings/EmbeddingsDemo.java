package com.bharath.azureai.embeddings;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.*;
import com.azure.core.credential.AzureKeyCredential;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmbeddingsDemo {
    public static void main(String[] args) {
        OpenAIClient client = new OpenAIClientBuilder()
                .endpoint("https://neyahopenai.openai.azure.com/")
                .credential(new AzureKeyCredential(System.getenv("OPENAI_API_KEY")))
                .buildClient();

        EmbeddingsOptions options = new EmbeddingsOptions(Arrays.asList("Learn share improve succeed"));
        Embeddings embeddings = client.getEmbeddings("embed_demo", options);
        for(EmbeddingItem item:embeddings.getData()){
            for(Double embedding:item.getEmbedding()){
                System.out.println(embedding);
            }
        }
    }
}
