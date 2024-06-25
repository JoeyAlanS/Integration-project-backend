package com.eletra.controllers;

import com.eletra.models.CategoryEntity;
import com.eletra.repositories.CategoryRepository;
import com.eletra.services.CategoryService;
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

    @Test
    public void getCategoryEntityListTest() {
        List<CategoryEntity> mockCategoryList = new ArrayList<>();
        mockCategoryList.add(new CategoryEntity("Category1", (short) 1));
        mockCategoryList.add(new CategoryEntity("Category2", (short) 2));

        when(mockCategoryRepository.findAll()).thenReturn(mockCategoryList);

        List<CategoryEntity> result = categoryController.getCategoryEntityList();

        assertEquals(mockCategoryList, result);
        verify(mockCategoryRepository).findAll();
        verifyNoMoreInteractions(mockCategoryRepository);
    }

    @Test
    public void getCategoryEntityListByLineNameTest() {
        List<CategoryEntity> mockCategoryList = new ArrayList<>();

        when(mockCategoryService.getCategoriesByLineName("Line")).thenReturn(mockCategoryList);

        List<CategoryEntity> result = categoryController.getCategoriesByLine("Line");

        assertEquals(mockCategoryList, result);
        verify(mockCategoryService).getCategoriesByLineName("Line");
        verifyNoMoreInteractions(mockCategoryService);
        verifyNoMoreInteractions(mockCategoryRepository);
    }

    @Test
    public void postCategoryEntityTest() {
        CategoryEntity mockCategory = new CategoryEntity("Category1", (short) 1);

        when(mockCategoryRepository.save(mockCategory)).thenReturn(mockCategory);

        CategoryEntity result = categoryController.postCategoryEntity(mockCategory);

        assertEquals(mockCategory, result);
        verify(mockCategoryRepository).save(mockCategory);
        verifyNoMoreInteractions(mockCategoryRepository);
    }

    @Test
    public void deleteCategoryEntityTest() {
        CategoryEntity mockCategory = new CategoryEntity("Category1", (short) 1);

        when(mockCategoryRepository.findByCategoryName("Category1"))
                .thenReturn(mockCategory)
                .thenReturn(null);

        doNothing().when(mockCategoryRepository).delete(mockCategory);

        ResponseEntity<Boolean> result = categoryController.deleteCategoryEntity("Category1");

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(true, result.getBody());

        verify(mockCategoryRepository, times(2)).findByCategoryName("Category1");
        verify(mockCategoryRepository, times(1)).delete(mockCategory);
        verifyNoMoreInteractions(mockCategoryRepository);
    }

    @Test
    public void deleteCategoryEntityWhenNotExistsTest() {
        when(mockCategoryRepository.findByCategoryName("NonExistentCategory")).thenReturn(null);

        ResponseEntity<Boolean> result = categoryController.deleteCategoryEntity("NonExistentCategory");

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals(false, result.getBody());

        verify(mockCategoryRepository, times(1)).findByCategoryName("NonExistentCategory");
        verifyNoMoreInteractions(mockCategoryRepository);
    }


    @Test
    public void updateCategoryEntityTest() {
        CategoryEntity mockCategory = new CategoryEntity("Category1", (short) 1);

        when(mockCategoryRepository.save(mockCategory)).thenReturn(mockCategory);

        CategoryEntity result = categoryController.updateCategoryEntity(mockCategory);

        assertEquals(mockCategory, result);
        verify(mockCategoryRepository).save(mockCategory);
        verifyNoMoreInteractions(mockCategoryRepository);
    }
}
