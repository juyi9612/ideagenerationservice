package com.aifound.ideagenerationservice.entity;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;

@Container(containerName = "Ideas", autoCreateContainer = false)
public class IdeaEntity {
    private String id;
    private String ideaName;
    private String ideaDescription;
    private String customerSegmentation;
    private String painpoint;
    private String valueProposition1;
    private String valueProposition2;
    private String goldenUserCases1;
    private String goldenUserCases2;
    private String goldenUserCases3;
    private String builtProcessDetail;
    private String growthChannels;
    private Integer mrrExpectationNumber;
    private String quoteSource;
    private String quote;
    private String tags;
    private Integer tier;

    @PartitionKey
    private IdeaDomain domain;

    public IdeaEntity() {

    }


    public IdeaEntity(String builtProcessDetail, String customerSegmentation, IdeaDomain domain, String goldenUserCases1, String goldenUserCases2, String goldenUserCases3, String growthChannels, String id, String ideaDescription, String ideaName, Integer mrrExpectationNumber, String painpoint, String quote, String quoteSource, String tags, Integer tier, String valueProposition1, String valueProposition2) {
        this.builtProcessDetail = builtProcessDetail;
        this.customerSegmentation = customerSegmentation;
        this.domain = domain;
        this.goldenUserCases1 = goldenUserCases1;
        this.goldenUserCases2 = goldenUserCases2;
        this.goldenUserCases3 = goldenUserCases3;
        this.growthChannels = growthChannels;
        this.id = id;
        this.ideaDescription = ideaDescription;
        this.ideaName = ideaName;
        this.mrrExpectationNumber = mrrExpectationNumber;
        this.painpoint = painpoint;
        this.quote = quote;
        this.quoteSource = quoteSource;
        this.tags = tags;
        this.tier = tier;
        this.valueProposition1 = valueProposition1;
        this.valueProposition2 = valueProposition2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdeaName() {
        return ideaName;
    }

    public void setIdeaName(String ideaName) {
        this.ideaName = ideaName;
    }

    public String getIdeaDescription() {
        return ideaDescription;
    }

    public void setIdeaDescription(String ideaDescription) {
        this.ideaDescription = ideaDescription;
    }

    public String getCustomerSegmentation() {
        return customerSegmentation;
    }

    public void setCustomerSegmentation(String customerSegmentation) {
        this.customerSegmentation = customerSegmentation;
    }

    public String getPainpoint() {
        return painpoint;
    }

    public void setPainpoint(String painpoint) {
        this.painpoint = painpoint;
    }

    public String getValueProposition1() {
        return valueProposition1;
    }

    public void setValueProposition1(String valueProposition1) {
        this.valueProposition1 = valueProposition1;
    }

    public String getValueProposition2() {
        return valueProposition2;
    }

    public void setValueProposition2(String valueProposition2) {
        this.valueProposition2 = valueProposition2;
    }

    public String getGoldenUserCases1() {
        return goldenUserCases1;
    }

    public void setGoldenUserCases1(String goldenUserCases1) {
        this.goldenUserCases1 = goldenUserCases1;
    }

    public String getGoldenUserCases2() {
        return goldenUserCases2;
    }

    public void setGoldenUserCases2(String goldenUserCases2) {
        this.goldenUserCases2 = goldenUserCases2;
    }

    public String getGoldenUserCases3() {
        return goldenUserCases3;
    }

    public void setGoldenUserCases3(String goldenUserCases3) {
        this.goldenUserCases3 = goldenUserCases3;
    }

    public String getBuiltProcessDetail() {
        return builtProcessDetail;
    }

    public void setBuiltProcessDetail(String builtProcessDetail) {
        this.builtProcessDetail = builtProcessDetail;
    }

    public String getGrowthChannels() {
        return growthChannels;
    }

    public void setGrowthChannels(String growthChannels) {
        this.growthChannels = growthChannels;
    }

    public Integer getMrrExpectationNumber() {
        return mrrExpectationNumber;
    }

    public void setMrrExpectationNumber(Integer mrrExpectationNumber) {
        this.mrrExpectationNumber = mrrExpectationNumber;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public IdeaDomain getDomain() {
        return domain;
    }

    public void setDomain(IdeaDomain domain) {
        this.domain = domain;
    }

    public String getQuoteSource() {
        return quoteSource;
    }

    public void setQuoteSource(String quoteSource) {
        this.quoteSource = quoteSource;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

}
