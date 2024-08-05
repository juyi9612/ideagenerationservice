package com.aifound.ideagenerationservice.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;

import java.util.Date;

@Container(containerName = "Articles", autoCreateContainer = true)
public class ArticleEntity {
    private String id;
    private String title;
    private String content;
    private String company;
    private String founder;
    private String revenue;
    private boolean isUsed;
    private Date usedTime;
    private Date createDateTime;

    public ArticleEntity() {

    }

    public ArticleEntity(Date usedTime, String id, String revenue, String founder, String company, String content, String title, boolean isUsed, Date createDateTime) {
        this.id = id;
        this.revenue = revenue;
        this.founder = founder;
        this.company = company;
        this.content = content;
        this.title = title;
        this.createDateTime = createDateTime;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFounder() {
        return founder;
    }

    public void setFounder(String founder) {
        this.founder = founder;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(Date createDateTime) {
        this.createDateTime = createDateTime;
    }
}
