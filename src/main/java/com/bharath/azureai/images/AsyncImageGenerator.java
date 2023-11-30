package com.bharath.azureai.images;

import com.azure.ai.openai.OpenAIAsyncClient;
import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.ImageGenerationOptions;
import com.azure.ai.openai.models.ImageLocation;
import com.azure.ai.openai.models.ImageResponse;
import com.azure.ai.openai.models.ImageSize;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.models.ResponseError;
import reactor.core.publisher.Mono;

public class AsyncImageGenerator {
    public static void main(String[] args) throws InterruptedException {
        OpenAIAsyncClient client = new OpenAIClientBuilder()
                .endpoint("https://neyahopenai.openai.azure.com/")
                .credential(new AzureKeyCredential(System.getenv("OPENAI_API_KEY")))
                .buildAsyncClient();

        ImageGenerationOptions options = new ImageGenerationOptions("Cityscape painting of " +
                "Zurich focussing on the reflections in the lake").setN(3).setSize(ImageSize.SIZE512X512);
        Mono<ImageResponse> response = client.getImages(options);

       response.subscribe(images ->{
           for(ImageLocation location:images.getData()){
               ResponseError error = location.getError();
               if(error!=null){
                   System.out.printf("Image generation failed with a error code %s and the" +
                           "error message is %s.%n",error.getCode(),error.getMessage());
               }else {
                   System.out.println("Image Location: " + location.getUrl());
               }
           }
       } ,error-> System.out.println(error),
               ()-> System.out.println("Completed Image Processing"));

       Thread.sleep(10000);

    }
}
