package com.eletra.repositories;

import com.eletra.models.CategoryEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
    List<CategoryEntity> findByLineId(Short lineId);

    CategoryEntity findByCategoryName(String categoryName);

    void delete(CategoryEntity categoryEntity);
}