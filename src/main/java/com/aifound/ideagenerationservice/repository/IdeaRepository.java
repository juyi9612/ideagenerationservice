package com.aifound.ideagenerationservice.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.aifound.ideagenerationservice.entity.IdeaEntity;
import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.Query;

// <repository_implementation>
@Repository
public interface IdeaRepository extends CosmosRepository<IdeaEntity, String> {
    @Query("SELECT * FROM Ideas p WHERE p.Category = @category")
    List<IdeaEntity> getIdeasByCategory(@Param("category") String category);
}
