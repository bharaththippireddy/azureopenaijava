package com.bharath.azureai.embeddings;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.EmbeddingItem;
import com.azure.ai.openai.models.Embeddings;
import com.azure.ai.openai.models.EmbeddingsOptions;
import com.azure.core.credential.AzureKeyCredential;

import java.util.Arrays;

public class SimilarityFinder {
    public static void main(String[] args) {
        OpenAIClient client = new OpenAIClientBuilder()
                .endpoint("https://neyahopenai.openai.azure.com/")
                .credential(new AzureKeyCredential(System.getenv("OPENAI_API_KEY")))
                .buildClient();

        EmbeddingsOptions options = new EmbeddingsOptions(Arrays.asList("You are good","You are bad"));
        Embeddings embeddings = client.getEmbeddings("embed_demo", options);

        double[] arr1 = CosUtil.toArray(embeddings.getData().get(0).getEmbedding());
        double[] arr2 = CosUtil.toArray(embeddings.getData().get(1).getEmbedding());
        System.out.println(CosUtil.cos(arr1,arr2)*100);


    }
}
