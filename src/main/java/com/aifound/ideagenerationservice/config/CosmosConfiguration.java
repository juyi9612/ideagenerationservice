package com.aifound.ideagenerationservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.azure.cosmos.CosmosClientBuilder;
import com.azure.spring.data.cosmos.config.AbstractCosmosConfiguration;
import com.azure.spring.data.cosmos.config.CosmosConfig;
import com.azure.spring.data.cosmos.repository.config.EnableCosmosRepositories;

@Configuration
@EnableCosmosRepositories
public class CosmosConfiguration extends AbstractCosmosConfiguration {
    @Value("${cosmosdb.uri}")
    private String uri;

    @Value("${cosmosdb.key}")
    private String key;

    @Value("${cosmosdb.databasename}")
    private String databasename;

    // <create_client>
    @Bean
    public CosmosClientBuilder getCosmosClientBuilder() {
        return new CosmosClientBuilder()
            .endpoint(uri)
            .key(key);
    }
    // </create_client>

    @Override
    public CosmosConfig cosmosConfig() {
        return CosmosConfig.builder()
            .enableQueryMetrics(true)
            .build();
    }

    // <get_database>
    @Override
    protected String getDatabaseName() {
        return databasename;
    }
    // </get_database>
}