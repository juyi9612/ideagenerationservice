package com.aifound.ideagenerationservice.controller.request;

import java.util.List;

public class GetIdeasRequest {
    private List<String> domains;
    private int ideaCount;

    public GetIdeasRequest() {
    }

    public GetIdeasRequest(List<String> domains, int ideaCount) {
        this.domains = domains;
        this.ideaCount = ideaCount;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public int getIdeaCount() {
        return ideaCount;
    }

    public void setIdeaCount(int ideaCount) {
        this.ideaCount = ideaCount;
    }
    
}
