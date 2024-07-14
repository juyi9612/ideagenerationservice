package com.aifound.ideagenerationservice.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class IdeaDto {
    private String ideaName;

    private String ideaDescription;

    private String targetCustomerSegmentation;

    private String painpoint;

    private String valueProposition1;

    private String valueProposition2;

    private String goldenUserCases1;

    private String goldenUserCases2;

    private String goldenUserCases3;

    private String builtProcessDetail;

    private String growthChannels;

    private Integer mrrExpectationNumber;

    private String tags;

    private String quoteSource;
    
    private String quote;

    @JsonCreator
    public IdeaDto(
        @JsonProperty("BuiltProcessDetail") String builtProcessDetail,
        @JsonProperty("GoldenUserCases1") String goldenUserCases1,
        @JsonProperty("GoldenUserCases2") String goldenUserCases2,
        @JsonProperty("GoldenUserCases3") String goldenUserCases3,
        @JsonProperty("GrowthChannels") String growthChannels,
        @JsonProperty("IdeaDescription") String ideaDescription,
        @JsonProperty("IdeaName") String ideaName,
        @JsonProperty("MrrExpectationNumber") Integer mrrExpectationNumber,
        @JsonProperty("Painpoint") String painpoint,
        @JsonProperty("Quote") String quote,
        @JsonProperty("QuoteSource") String quoteSource,
        @JsonProperty("Tags") String tags,
        @JsonProperty("TargetCustomerSegmentation") String targetCustomerSegmentation,
        @JsonProperty("ValueProposition1") String valueProposition1,
        @JsonProperty("ValueProposition2") String valueProposition2) {
        this.builtProcessDetail = builtProcessDetail;
        this.goldenUserCases1 = goldenUserCases1;
        this.goldenUserCases2 = goldenUserCases2;
        this.goldenUserCases3 = goldenUserCases3;
        this.growthChannels = growthChannels;
        this.ideaDescription = ideaDescription;
        this.ideaName = ideaName;
        this.mrrExpectationNumber = mrrExpectationNumber;
        this.painpoint = painpoint;
        this.quote = quote;
        this.quoteSource = quoteSource;
        this.tags = tags;
        this.targetCustomerSegmentation = targetCustomerSegmentation;
        this.valueProposition1 = valueProposition1;
        this.valueProposition2 = valueProposition2;
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
        return targetCustomerSegmentation;
    }

    public void setCustomerSegmentation(String customerSegmentation) {
        this.targetCustomerSegmentation = customerSegmentation;
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

    public String getTargetCustomerSegmentation() {
        return targetCustomerSegmentation;
    }

    public void setTargetCustomerSegmentation(String targetCustomerSegmentation) {
        this.targetCustomerSegmentation = targetCustomerSegmentation;
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
