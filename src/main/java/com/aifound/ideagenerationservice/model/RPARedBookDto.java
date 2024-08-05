package com.aifound.ideagenerationservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RPARedBookDto {
    private String title;
    private String description;
    private String tags;
    private List<String> posterUrls;

    public RPARedBookDto(
            @JsonProperty("rebbook_title")String title,
            @JsonProperty("rebbook_description")String description,
            @JsonProperty("rebbook_tags")String tags,
            @JsonProperty("redbook_image_urls")List<String> posterUrls) {
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.posterUrls = posterUrls;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<String> getPosterUrls() {
        return posterUrls;
    }

    public void setPosterUrls(List<String> posterUrls) {
        this.posterUrls = posterUrls;
    }
}
