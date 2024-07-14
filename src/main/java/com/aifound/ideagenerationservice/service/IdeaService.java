package com.aifound.ideagenerationservice.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aifound.ideagenerationservice.config.IdeaDomainsConfig;
import com.aifound.ideagenerationservice.entity.IdeaDomain;
import com.aifound.ideagenerationservice.entity.IdeaEntity;
import com.aifound.ideagenerationservice.model.IdeaDto;
import com.aifound.ideagenerationservice.repository.IdeaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class IdeaService {
    private final IdeaRepository ideaRepository;
    private final IdeaDomainsConfig ideaDomainsConfig;

    @Autowired
    public IdeaService(IdeaRepository ideaRepository, IdeaDomainsConfig ideaDomainsConfig) {
        this.ideaRepository = ideaRepository;
        this.ideaDomainsConfig = ideaDomainsConfig;
    }

    public void cookIdeas() throws Exception {
        Map<String, List<String>> domains = ideaDomainsConfig.getDomain();
        for (Map.Entry<String, List<String>> entry : domains.entrySet()) {
            String domain = entry.getKey();
            List<String> topics = entry.getValue();
            for (String topic : topics) {
                System.out.println("Topic: " + topic);
                IdeaDto ideaDto = this.getIdeaByTopic(topic);
                ModelMapper modelMapper = new ModelMapper();
                IdeaEntity entity = modelMapper.map(ideaDto, IdeaEntity.class);
                UUID uuid = UUID.randomUUID();
                entity.setId(uuid.toString());
                entity.setDomain(IdeaDomain.valueOf(domain));
                entity.setTier(1);
                ideaRepository.save(entity);
            }
        }
    }

    private IdeaDto getIdeaByTopic(String topic) throws Exception{
        HttpClient client = HttpClient.newHttpClient();
        String jsonBody = String.format("{\"question\":\"%s\"}", topic);
        String serviceUrl = "https://flowise-np5j.onrender.com/api/v1/prediction/8efbdd5b-ba78-4856-b492-9188b5a673ba";
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(serviceUrl))
            .header("Content-Type", "application/json") // Set appropriate content type
            .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        JSONObject json = new JSONObject(body);
        ObjectMapper mapper = new ObjectMapper();
        String value = json.get("json").toString();
        IdeaDto idea =  mapper.readValue(value, IdeaDto.class);
        return idea;
    }
}
