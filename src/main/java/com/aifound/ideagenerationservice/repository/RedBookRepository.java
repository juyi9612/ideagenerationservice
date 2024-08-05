package com.aifound.ideagenerationservice.repository;

import com.aifound.ideagenerationservice.entity.RedBookEntity;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RedBookRepository extends CosmosRepository<RedBookEntity, String> {
    @Query("SELECT top 1 FROM RedBooks p WHERE p.isUsed = false")
    RedBookEntity getUnusedRedBook();
}
