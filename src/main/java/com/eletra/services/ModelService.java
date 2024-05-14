package com.eletra.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eletra.models.ModelEntity;
import com.eletra.repositories.ModelRepository;

import java.util.List;

@Service
public class ModelService {
    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private CategoryService categoryService;

    public List<ModelEntity> getModelNameByCategoryName(String categoryName) {
        Short categoryId = categoryService.getCategoryIdByCategoryName(categoryName);
        return modelRepository.findByCategoryId(categoryId);
    }
}
