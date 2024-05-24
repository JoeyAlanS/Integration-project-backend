package com.eletra.repositories;

import com.eletra.models.ModelEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, String> {
    ModelEntity findByModelName(String modelName);

    List<ModelEntity> findByCategoryId(Short categoryId);

    void delete(ModelEntity modelEntity);
}