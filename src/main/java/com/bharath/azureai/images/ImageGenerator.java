package com.bharath.azureai.images;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.*;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.models.ResponseError;

import java.util.ArrayList;
import java.util.List;

public class ImageGenerator {
    public static void main(String[] args) {
        OpenAIClient client = new OpenAIClientBuilder()
                .endpoint("https://neyahopenai.openai.azure.com/")
                .credential(new AzureKeyCredential(System.getenv("OPENAI_API_KEY")))
                .buildClient();

        ImageGenerationOptions options = new ImageGenerationOptions("Cityscape painting of " +
                "Zurich focussing on the reflections in the lake").setN(3).setSize(ImageSize.SIZE512X512);
        ImageResponse response = client.getImages(options);

        for(ImageLocation location:response.getData()){
            ResponseError error = location.getError();
            if(error!=null){
                System.out.printf("Image generation failed with a error code %s and the" +
                        "error message is %s.%n",error.getCode(),error.getMessage());
            }else {
                System.out.println("Image Location: " + location.getUrl());
            }
        }

    }
}
