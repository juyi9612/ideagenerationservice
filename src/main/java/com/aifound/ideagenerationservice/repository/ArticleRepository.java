package com.aifound.ideagenerationservice.repository;

import com.aifound.ideagenerationservice.entity.ArticleEntity;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends CosmosRepository<ArticleEntity, String> {
    @Query("SELECT top @batch * FROM p WHERE p.used = false")
    List<ArticleEntity> getUnusedArticle(@Param("batch") int batch);
}
