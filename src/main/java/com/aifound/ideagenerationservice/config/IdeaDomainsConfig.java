package com.aifound.ideagenerationservice.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "domains")
@EnableConfigurationProperties()
public class IdeaDomainsConfig {
    private Map<String, List<String>> domain;

    public Map<String, List<String>> getDomain() {
        return domain;
    }

    public void setDomain(Map<String, List<String>> domain) {
        this.domain = domain;
    }
}
