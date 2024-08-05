package com.aifound.ideagenerationservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RedBookDto {

    private String articletitle;
    private String hackerInfo;
    private String productinfo;
    private String learnning;
    private String keysuccess;
    private String gtm;
    private String storyline;
    private String mrr;
    private String tags;

    public RedBookDto() {
    }

    @JsonCreator
    public RedBookDto(
            @JsonProperty("Articletitle")String articletitle,
            @JsonProperty("HackerInfo")String hackerInfo,
            @JsonProperty("Productinfo")String productinfo,
            @JsonProperty("Learnning")String learnning,
            @JsonProperty("keysuccess")String keysuccess,
            @JsonProperty("MRR")String mrr,
            @JsonProperty("GTM")String gtm,
            @JsonProperty("Storyline")String storyline,
            @JsonProperty("Tags")String tags) {
        this.articletitle = articletitle;
        this.hackerInfo = hackerInfo;
        this.productinfo = productinfo;
        this.learnning = learnning;
        this.keysuccess = keysuccess;
        this.mrr = mrr;
        this.gtm = gtm;
        this.storyline = storyline;
        this.tags = tags;
    }

    public String getArticletitle() {
        return articletitle;
    }

    public void setArticletitle(String articletitle) {
        this.articletitle = articletitle;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getMrr() {
        return mrr;
    }

    public void setMrr(String mrr) {
        this.mrr = mrr;
    }

    public String getStoryline() {
        return storyline;
    }

    public void setStoryline(String storyline) {
        this.storyline = storyline;
    }

    public String getGtm() {
        return gtm;
    }

    public void setGtm(String gtm) {
        this.gtm = gtm;
    }

    public String getKeysuccess() {
        return keysuccess;
    }

    public void setKeysuccess(String keysuccess) {
        this.keysuccess = keysuccess;
    }

    public String getLearnning() {
        return learnning;
    }

    public void setLearnning(String learnning) {
        this.learnning = learnning;
    }

    public String getProductinfo() {
        return productinfo;
    }

    public void setProductinfo(String productinfo) {
        this.productinfo = productinfo;
    }

    public String getHackerInfo() {
        return hackerInfo;
    }

    public void setHackerInfo(String hackerInfo) {
        this.hackerInfo = hackerInfo;
    }
}
