package com.eletra.controllers;

import com.eletra.models.CategoryEntity;
import com.eletra.repositories.CategoryRepository;
import com.eletra.services.CategoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class CategoryControllerTest {

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private CategoryRepository mockCategoryRepository;

    @Mock
    private CategoryService mockCategoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    public void tearDown() {
        clearInvocations(mockCategoryRepository, mockCategoryService);
    }

    @Test
    public void getCategoryEntityListTest() {
        // Given
        List<CategoryEntity> mockCategoryList = new ArrayList<>();
        mockCategoryList.add(new CategoryEntity("Category1", (short) 1));
        mockCategoryList.add(new CategoryEntity("Category2", (short) 2));
        when(mockCategoryRepository.findAll()).thenReturn(mockCategoryList);

        // When
        List<CategoryEntity> result = categoryController.getCategoryEntityList();

        // Then
        assertEquals(mockCategoryList, result);
        verify(mockCategoryRepository).findAll();
        verifyNoMoreInteractions(mockCategoryRepository);
    }

    @Test
    public void getCategoryEntityListByLineNameTest() {
        // Given
        List<CategoryEntity> mockCategoryList = new ArrayList<>();
        when(mockCategoryService.getCategoriesByLineName("Line")).thenReturn(mockCategoryList);

        // When
        List<CategoryEntity> result = categoryController.getCategoriesByLine("Line");

        // Then
        assertEquals(mockCategoryList, result);
        verify(mockCategoryService).getCategoriesByLineName("Line");
        verifyNoMoreInteractions(mockCategoryService);
        verifyNoMoreInteractions(mockCategoryRepository);
    }

    @Test
    public void postCategoryEntityTest() {
        // Given
        CategoryEntity mockCategory = new CategoryEntity("Category1", (short) 1);
        when(mockCategoryRepository.save(mockCategory)).thenReturn(mockCategory);

        // When
        CategoryEntity result = categoryController.postCategoryEntity(mockCategory);

        // Then
        assertEquals(mockCategory, result);
        verify(mockCategoryRepository).save(mockCategory);
        verifyNoMoreInteractions(mockCategoryRepository);
    }

    @Test
    public void deleteCategoryEntityTest() {
        // Given
        CategoryEntity mockCategory = new CategoryEntity("Category1", (short) 1);
        when(mockCategoryRepository.findByCategoryName("Category1")).thenReturn(mockCategory);
        doNothing().when(mockCategoryRepository).delete(mockCategory);

        // When
        ResponseEntity<Boolean> result = categoryController.deleteCategoryEntity("Category1");

        // Then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(true, result.getBody());
        verify(mockCategoryRepository).findByCategoryName("Category1");
        verify(mockCategoryRepository).delete(mockCategory);
        verifyNoMoreInteractions(mockCategoryRepository);
    }

    @Test
    public void deleteCategoryEntityWhenNotExistsTest() {
        // Given
        when(mockCategoryRepository.findByCategoryName("NonExistentCategory")).thenReturn(null);

        // When
        ResponseEntity<Boolean> result = categoryController.deleteCategoryEntity("NonExistentCategory");

        // Then
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(false, result.getBody());
        verify(mockCategoryRepository, times(1)).findByCategoryName("NonExistentCategory");
        verifyNoMoreInteractions(mockCategoryRepository);
    }

    @Test
    public void updateCategoryEntityTest() {
        // Given
        CategoryEntity mockCategory = new CategoryEntity("Category1", (short) 1);
        when(mockCategoryRepository.save(mockCategory)).thenReturn(mockCategory);

        // When
        CategoryEntity result = categoryController.updateCategoryEntity(mockCategory);

        // Then
        assertEquals(mockCategory, result);
        verify(mockCategoryRepository).save(mockCategory);
        verifyNoMoreInteractions(mockCategoryRepository);
    }
}
