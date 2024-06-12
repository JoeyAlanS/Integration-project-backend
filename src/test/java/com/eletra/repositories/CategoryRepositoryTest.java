package com.eletra.repositories;

import com.eletra.models.CategoryEntity;
import com.eletra.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
public class CategoryRepositoryTest {

    @InjectMocks
    private CategoryRepository categoryRepository;

    @Mock
    private CategoryRepository mockCategoryRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindByLineId() {
        Category mockCategory = new Category("Category1", (short) 1);
        when(mockCategoryRepository.findByLineId(1)).thenReturn(Collections.singletonList(mockCategory));

        List<Category> result = categoryRepository.findByLineId(1);

        assertEquals(Collections.singletonList(mockCategory), result);
        verify(mockCategoryRepository).findByLineId(1);
        verifyNoMoreInteractions(mockCategoryRepository);
    }

    @Test
    public void testFindByCategoryName() {
        Category mockCategory = new Category("Category1", (short) 1);
        when(mockCategoryRepository.findByCategoryName("Category1")).thenReturn(mockCategory);

        Category result = categoryRepository.findByCategoryName("Category1");

        assertEquals(mockCategory, result);
        verify(mockCategoryRepository).findByCategoryName("Category1");
        verifyNoMoreInteractions(mockCategoryRepository);
    }

    @Test
    public void testDelete() {
        Category mockCategory = new Category("Category1", (short) 1);
        when(mockCategoryRepository.findByCategoryName("Category1")).thenReturn(mockCategory);

        categoryRepository.delete(mockCategory);

        verify(mockCategoryRepository).delete(mockCategory);
        verifyNoMoreInteractions(mockCategoryRepository);
    }
}
