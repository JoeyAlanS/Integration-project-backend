package com.eletra.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.eletra.models.CategoryEntity;
import com.eletra.repositories.CategoryRepository;


import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private LineupService lineupService;

    public List<CategoryEntity> getCategoriesByLineName(String lineName) {
        Short lineId = lineupService.getLineIdByLineName(lineName);
        return categoryRepository.findByLineId(lineId);
    }

    public Short getCategoryIdByCategoryName(String categoryName) {
        CategoryEntity categoryEntity = categoryRepository.findByCategoryName(categoryName);
        return categoryEntity.getId();
    }


}
