package com.aifound.ideagenerationservice.service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aifound.ideagenerationservice.config.IdeaDomainsConfig;
import com.aifound.ideagenerationservice.entity.IdeaDomain;
import com.aifound.ideagenerationservice.entity.IdeaEntity;
import com.aifound.ideagenerationservice.model.IdeaDto;
import com.aifound.ideagenerationservice.repository.IdeaRepository;
import com.azure.cosmos.models.PartitionKey;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class IdeaService {
    private final IdeaRepository ideaRepository;
    private final IdeaDomainsConfig ideaDomainsConfig;
    private final ModelMapper modelMapper;

    @Autowired
    public IdeaService(IdeaRepository ideaRepository, IdeaDomainsConfig ideaDomainsConfig) {
        this.ideaRepository = ideaRepository;
        this.ideaDomainsConfig = ideaDomainsConfig;
        this.modelMapper = new ModelMapper();
    }

    public List<IdeaDto> getIdeasByDomain(List<String> domains, int count) throws Exception {
        if (domains.isEmpty()) throw new Exception("Selected domain count cannot be 0.");
        int countPerDomain = (count / domains.size()) + 1;
        List<IdeaDto> ideaDtos = new ArrayList<>();
        for (String domain : domains) {
            List<IdeaEntity> ideaEntities = new ArrayList<>();
            this.ideaRepository.findAll(new PartitionKey(domain)).forEach((entity) -> {
                ideaEntities.add(entity);
            });
            List<IdeaEntity> selectedEntities = this.getRandomIdeas(ideaEntities, countPerDomain);
            List<IdeaDto> dtos = new ArrayList<>();
            selectedEntities.stream().forEach((entity) -> {
                IdeaDto dto = this.modelMapper.map(entity, IdeaDto.class);
                dtos.add(dto);
            });
            ideaDtos.addAll(dtos.subList(0, Math.min(count, dtos.size())));
            count -= dtos.size();
        }

        return ideaDtos;
    }

    private List<IdeaEntity> getRandomIdeas(List<IdeaEntity> ideas, int count) {
         // Shuffle the list to randomize the order of elements
        Collections.shuffle(ideas, new Random());

        // If count is greater than the size of the list, return the entire list
        if (count > ideas.size()) {
            return ideas;
        }

        // Return the first 'count' elements of the shuffled list
        return ideas.subList(0, count);
    }

    public void cookIdeas() throws Exception {
        Map<String, List<String>> domains = ideaDomainsConfig.getDomain();
        HttpClient client = HttpClient.newHttpClient();

        for (Map.Entry<String, List<String>> entry : domains.entrySet()) {
            String domain = entry.getKey();
            List<String> topics = entry.getValue();
            for (String topic : topics) {
                System.out.println("Topic: " + topic);
                try {
                    IdeaDto ideaDto = this.getIdeaByTopic(topic, client);
                    IdeaEntity entity = this.modelMapper.map(ideaDto, IdeaEntity.class);
                    UUID uuid = UUID.randomUUID();
                    entity.setId(uuid.toString());
                    entity.setDomain(IdeaDomain.valueOf(domain));
                    entity.setTier(1);
                    ideaRepository.save(entity);
                } catch(Exception e) {
                    System.out.println("!!!!!!Failed topic: " + topic);
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private IdeaDto getIdeaByTopic(String topic, HttpClient client) throws Exception{
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
