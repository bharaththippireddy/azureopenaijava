package com.bharath.azureai.audio;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.OpenAIClientBuilder;
import com.azure.ai.openai.models.*;
import com.azure.core.credential.AzureKeyCredential;
import com.azure.core.util.BinaryData;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Transriber {
    public static void main(String[] args) {
        OpenAIClient client = new OpenAIClientBuilder()
                .endpoint("https://neyaheurope.openai.azure.com/")
                .credential(new AzureKeyCredential(System.getenv("OPENAI_API_KEY")))
                .buildClient();

        String fileName = "aws_lambda.mp3";
        Path path = Paths.get("/Users/bharaththippireddy/Documents/audio/" + fileName);
        byte[] bytes = BinaryData.fromFile(path).toBytes();

        AudioTranscriptionOptions options = new AudioTranscriptionOptions(bytes).setResponseFormat(AudioTranscriptionFormat.JSON);
        AudioTranscription response = client.getAudioTranscription("audio_demo", fileName, options);
        System.out.println(response.getText());



    }
}
