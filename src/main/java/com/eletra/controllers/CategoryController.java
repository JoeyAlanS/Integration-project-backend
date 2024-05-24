package com.eletra.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.eletra.repositories.CategoryRepository;
import com.eletra.models.CategoryEntity;
import com.eletra.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API REST medidores")
@CrossOrigin(origins = "*")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    @ResponseBody
    @ApiOperation(value = "Return categories")
    public List<CategoryEntity> getCategoryEntityList() {
        return categoryRepository.findAll();
    }

    @GetMapping("/categories/{line-name}")
    @ResponseBody
    @ApiOperation(value = "Return category")
    public List<CategoryEntity> getCategoriesByLine(@PathVariable(value = "line-name") String lineName) {
        return categoryService.getCategoriesByLineName(lineName);
    }

    @PostMapping("/category")
    @ResponseBody
    @ApiOperation(value = "Create Category")
    public CategoryEntity postCategoryEntity(@RequestBody CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }

    @DeleteMapping("/category/{category-name}")
    @ResponseBody
    @ApiOperation(value = "Delete category")
    public ResponseEntity<Boolean> deleteCategoryEntity(@PathVariable(value = "category-name") String categoryName) {
        CategoryEntity categoryEntity = categoryRepository.findByCategoryName(categoryName);
        if (categoryEntity != null) {
            categoryRepository.delete(categoryEntity);
            boolean exists = categoryRepository.findByCategoryName(categoryName) != null;
            return ResponseEntity.ok(!exists);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
    }

    @PutMapping("/category")
    @ResponseBody
    @ApiOperation(value = "Update category")
    public CategoryEntity updateCategoryEntity(@RequestBody CategoryEntity categoryEntity) {
        return categoryRepository.save(categoryEntity);
    }
}
