package com.eletra.controllers;

import com.eletra.models.CategoryEntity;
import com.eletra.repositories.CategoryRepository;
import com.eletra.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class CategoryControllerTest {

    @InjectMocks
    private CategoryController controller;

    @Mock
    private CategoryRepository repository;

    @Mock
    private CategoryService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getCategoryEntityList() {
        List<Category> mockCategoryList = Collections.singletonList(new Category("Category1", (short) 1));
        when(repository.findAll()).thenReturn(mockCategoryList);

        List<Category> result = controller.getCategoryEntityList();

        assertEquals(mockCategoryList, result);
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void getCategoriesByLine() {
        List<Category> mockCategoryList = Collections.singletonList(new Category("Category1", (short) 1));
        when(service.getCategoriesByLineName("Line")).thenReturn(mockCategoryList);

        List<Category> result = controller.getCategoriesByLine("Line");

        assertEquals(mockCategoryList, result);
        verify(service).getCategoriesByLineName("Line");
        verifyNoMoreInteractions(service);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void postCategoryEntity() {
        Category mockCategory = new Category("Category1", (short) 1);
        when(repository.save(mockCategory)).thenReturn(mockCategory);

        Category result = controller.postCategoryEntity(mockCategory);

        assertEquals(mockCategory, result);
        verify(repository).save(mockCategory);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteCategoryEntity() {
        Category mockCategory = new Category("Category1", (short) 1);
        when(repository.findByCategoryName("Category1")).thenReturn(mockCategory);

        String result = controller.deleteCategoryEntity("Category1");

        assertEquals("Category deleted", result);
        verify(repository).findByCategoryName("Category1");
        verify(repository).delete(mockCategory);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void updateCategoryEntity() {
        Category mockCategory = new Category("Category1", (short) 1);
        when(repository.save(mockCategory)).thenReturn(mockCategory);

        String result = controller.updateCategoryEntity(mockCategory);

        assertEquals("Category updated", result);
        verify(repository).save(mockCategory);
        verifyNoMoreInteractions(repository);
    }
}
