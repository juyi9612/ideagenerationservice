package com.aifound.ideagenerationservice.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;

import java.util.Date;
import java.util.List;

@Container(containerName = "RedBooks", autoCreateContainer = true)
public class RedBookEntity {
    private String id;
    private String articleTitle;
    private String hackerInfo;
    private String productInfo;
    private String learnning;
    private String keysuccess;
    private String gtm;
    private String storyline;
    private String mrr;
    private String tags;
    private boolean isUsed;
    private Date usedTime;
    private Date createDateTime;
    private List<String> posterUrls;
    private String articleId;

    public RedBookEntity() {
    }

    public RedBookEntity(String id, String articleTitle, String hackerInfo, String productinfo, String learnning, String keysuccess, String gtm, String storyline, String mrr, String tags, boolean isUsed, Date usedTime, Date createDateTime, List<String> posterUrls, String articleId) {
        this.id = id;
        this.articleTitle = articleTitle;
        this.hackerInfo = hackerInfo;
        this.productInfo = productinfo;
        this.learnning = learnning;
        this.keysuccess = keysuccess;
        this.gtm = gtm;
        this.storyline = storyline;
        this.mrr = mrr;
        this.tags = tags;
        this.isUsed = isUsed;
        this.usedTime = usedTime;
        this.createDateTime = createDateTime;
        this.posterUrls = posterUrls;
        this.articleId = articleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getHackerInfo() {
        return hackerInfo;
    }

    public void setHackerInfo(String hackerInfo) {
        this.hackerInfo = hackerInfo;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public String getLearnning() {
        return learnning;
    }

    public void setLearnning(String learnning) {
        this.learnning = learnning;
    }

    public String getKeysuccess() {
        return keysuccess;
    }

    public void setKeysuccess(String keysuccess) {
        this.keysuccess = keysuccess;
    }

    public String getGtm() {
        return gtm;
    }

    public void setGtm(String gtm) {
        this.gtm = gtm;
    }

    public String getStoryline() {
        return storyline;
    }

    public void setStoryline(String storyline) {
        this.storyline = storyline;
    }

    public String getMrr() {
        return mrr;
    }

    public void setMrr(String mrr) {
        this.mrr = mrr;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public Date getUsedTime() {
        return usedTime;
    }

    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }

    public List<String> getPosterUrls() {
        return posterUrls;
    }

    public void setPosterUrls(List<String> posterUrls) {
        this.posterUrls = posterUrls;
    }

    public String getArticleId() {
        return articleId;
    }

    public void setArticleId(String articleId) {
        this.articleId = articleId;
    }
}
