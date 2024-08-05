package com.aifound.ideagenerationservice.repository;

import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Repository
public class PosterBlobRepository {

    @Value("${blob.storage.posters.constr}")
    private String connectionString;

    private BlobServiceClient blobServiceClient;

    private final static String CONTAINER_NAME = "posters";

    @PostConstruct
    public void init() {
        blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
    }

    public void uploadBlobFile(InputStream input, String fileName) throws IOException {
        blobServiceClient.getBlobContainerClient(CONTAINER_NAME).getBlobClient(fileName).upload(input);
    }
}
