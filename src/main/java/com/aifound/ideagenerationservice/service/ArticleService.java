package com.aifound.ideagenerationservice.service;

import com.aifound.ideagenerationservice.entity.ArticleEntity;
import com.aifound.ideagenerationservice.entity.RedBookEntity;
import com.aifound.ideagenerationservice.model.RedBookDto;
import com.aifound.ideagenerationservice.repository.ArticleRepository;
import com.aifound.ideagenerationservice.repository.PosterBlobRepository;
import com.aifound.ideagenerationservice.repository.RedBookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final RedBookRepository redBookRepository;
    private final PosterBlobRepository posterBlobRepository;
    private final ModelMapper modelMapper;

    private final String blob_url = "https://posters.blob.core.windows.net/posters/";

    @Autowired
    public ArticleService(ArticleRepository articleRepository, RedBookRepository redBookRepository, PosterBlobRepository posterBlobRepository) {
        this.articleRepository = articleRepository;
        this.redBookRepository = redBookRepository;
        this.posterBlobRepository = posterBlobRepository;
        this.modelMapper = new ModelMapper();
    }

    public void preCookArticles() throws IOException {
        FileInputStream file = new FileInputStream(new File("indiehackers.xls"));

        // Workbook workbook = new XSSFWorkbook(file);
        HSSFWorkbook hssfWorkbook=new HSSFWorkbook(file);
        HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
        for (Row row : hssfSheet) {
            ArticleEntity articleEntity = new ArticleEntity();
            for (Cell cell : row) {
                if (cell.toString().equals("Title")) break; // skip 1st row
                articleEntity.setId(UUID.randomUUID().toString());
                switch(cell.getColumnIndex()) {
                    case 0:
                        articleEntity.setTitle(cell.toString());
                        break;
                    case 1:
                        articleEntity.setContent(cell.toString());
                        break;
                    case 2:
                        articleEntity.setCompany(cell.toString());
                        break;
                    case 3:
                        articleEntity.setFounder(cell.toString());
                        break;
                    case 4:
                        articleEntity.setRevenue(cell.toString());
                        break;
                    default:
                        System.out.println("other--->" + cell);
                }
            }

            if (articleEntity.getId() != null) {
                this.articleRepository.save(articleEntity);
            }
        }
    }

    public void preCookRedBookContent(int cookCount) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        int maxBatchNum = 10;
        while (cookCount > 0) {
            int batch = Math.min(maxBatchNum, cookCount);

            // get unused articles
            List<ArticleEntity> articleEntities = this.getUnusedArticles(batch);

            // generate redbooks by articles
            List<RedBookEntity> entities = this.getRedbookByArticle(client, articleEntities);

            // poster render
            for (RedBookEntity entity : entities) {
                this.posterRender(client, entity);
            }

            this.redBookRepository.saveAll(entities);
            cookCount -= batch;
            System.out.println("Successfully cook count: " + batch);

            Date now = new Date();
            TimeZone.setDefault( TimeZone.getTimeZone("UTC"));
            for (ArticleEntity entity: articleEntities) {
                entity.setUsed(true);
                entity.setUsedTime(now);
            }

            this.articleRepository.saveAll(articleEntities);
            System.out.println("Successfully mark articles as used count: " + batch);
        }
    }

    private List<ArticleEntity> getUnusedArticles(int batch) throws Exception {
        List<ArticleEntity> articleEntities = this.articleRepository.getUnusedArticle(batch);
        if (articleEntities.isEmpty()) {
            throw new Exception("No article can be used.");
        }

        return articleEntities;
    }

    private List<RedBookEntity> getRedbookByArticle(HttpClient client, List<ArticleEntity> articleEntities) throws Exception {
        List<RedBookEntity> redBookEntities = new ArrayList<>();
        for (ArticleEntity articleEntity : articleEntities) {
            String question = articleEntity.getTitle().replaceAll("\\n",",").replaceAll("\"","'") + articleEntity.getContent().replaceAll("\\n",",").replaceAll("\"","'");
            String jsonBody = String.format("{\"question\":\"%s\"}", question);
            String serviceUrl = "https://flowise-np5j.onrender.com/api/v1/prediction/b4fe86f5-4d2a-4eb0-9db1-bcfb3a8ba5e1";
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(serviceUrl))
                    .header("Content-Type", "application/json") // Set appropriate content type
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                String body = response.body();
                JSONObject json = new JSONObject(body);
                ObjectMapper mapper = new ObjectMapper();
                String value = json.get("json").toString();
                RedBookDto dto =  mapper.readValue(value, RedBookDto.class);
                RedBookEntity entity = this.modelMapper.map(dto, RedBookEntity.class);

                // aggregate data
                entity.setId(UUID.randomUUID().toString());
                Date now = new Date();
                TimeZone.setDefault( TimeZone.getTimeZone("UTC"));
                entity.setCreateDateTime(now);
                entity.setArticleId(articleEntity.getId());
                redBookEntities.add(entity);
            } else {
                System.out.println("Call article2redbook api failed, aid: " + articleEntity.getId());
                throw new Exception(response.body());
            }
        }

        return redBookEntities;
    }

    private void posterRender(HttpClient client, RedBookEntity entity) throws Exception {
        List<String> imageUrls = new ArrayList<>();
        imageUrls.add(this.poster1Render(client, entity));
        imageUrls.add(this.poster2Render(client, entity));
        imageUrls.add(this.poster3Render(client, entity));
        imageUrls.add(this.poster4Render(client, entity));
        entity.setPosterUrls(imageUrls);
    }

    private String poster4Render(HttpClient client, RedBookEntity entity) throws Exception {
        String jsonBody = String.format("{\"title1\":\"%s\",\"content1\":\"%s\"}",
                "Learnning",
                entity.getLearnning().replaceAll("\\n", "/n"));
        String serviceUrl = "http://localhost:80/generate-poster/4";
        String fileName = entity.getId() + "_4.png";
        this.posterRenderRequest(client, jsonBody, serviceUrl, fileName);
        return blob_url + fileName;
    }

    private String poster3Render(HttpClient client, RedBookEntity entity) throws Exception {
        String jsonBody = String.format("{\"title1\":\"%s\",\"content1\":\"%s\",\"title2\":\"%s\",\"content2\":\"%s\"}",
                "The story",
                entity.getStoryline().replaceAll("\\n", "/n"),
                "GTM",
                entity.getGtm().replaceAll("\\n", "/n"));
        String serviceUrl = "http://localhost:80/generate-poster/3";
        String fileName = entity.getId() + "_3.png";
        this.posterRenderRequest(client, jsonBody, serviceUrl, fileName);
        return blob_url + fileName;
    }

    private String poster2Render(HttpClient client, RedBookEntity entity) throws Exception {
        String jsonBody = String.format("{\"title1\":\"%s\",\"content1\":\"%s\",\"title2\":\"%s\",\"content2\":\"%s\"}",
                "Founder",
                entity.getHackerInfo(),
                "Idea",
                entity.getProductInfo());
        String serviceUrl = "http://localhost:80/generate-poster/2";
        String fileName = entity.getId() + "_2.png";
        this.posterRenderRequest(client, jsonBody, serviceUrl, fileName);
        return blob_url + fileName;
    }

    private String poster1Render(HttpClient client, RedBookEntity entity) throws Exception {
        String jsonBody = String.format("{\"title1\":\"%s\",\"content1\":\"%s\",\"mrr\":\"%s\"}",
                entity.getArticleTitle(),
                entity.getProductInfo(),
                entity.getMrr());
        String serviceUrl = "http://localhost:80/generate-poster/1";
        String fileName = entity.getId() + "_1.png";
        this.posterRenderRequest(client, jsonBody, serviceUrl, fileName);
        return blob_url + fileName;
    }

    private void posterRenderRequest(HttpClient client, String jsonBody, String serviceUrl, String fileName) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(serviceUrl))
                .header("Content-Type", "application/json") // Set appropriate content type
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();
        HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());
        if (response.statusCode() == 200) {
            byte[] imageData = response.body();
            InputStream targetStream = new ByteArrayInputStream(imageData);
            posterBlobRepository.uploadBlobFile(targetStream, fileName);
        } else {
            System.err.println("Failed to upload image. HTTP response code: " + response.statusCode());
            throw new Exception("Upload image to blob failed.");
        }
    }
}
