package com.eletra.repositories;

import com.eletra.models.CategoryEntity;
import com.eletra.models.LineupEntity;
import com.eletra.services.CategoryService;
import com.eletra.services.LineupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
public class CategoryRepositoryTest {

    @InjectMocks
    private CategoryService categoryService;

    @Mock
    private CategoryRepository mockCategoryRepository;

    @Mock
    private LineupService mockLineupService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("return categories associated with a lineup name")
    public void searchCategoriesByLineNameTest() {
        LineupEntity mockLine = new LineupEntity("Line1", (short) 1);
        CategoryEntity mockCategory = new CategoryEntity("AresTB", (short) 1);
        when(mockCategoryRepository.findByLineId(mockLine.getId())).thenReturn(Collections.singletonList(mockCategory));
        when(mockLineupService.getLineIdByLineName(mockLine.getLineName())).thenReturn(mockLine.getId());

        List<CategoryEntity> result = categoryService.getCategoriesByLineName(mockLine.getLineName());

        assertEquals(Collections.singletonList(mockCategory), result);
        verify(mockCategoryRepository).findByLineId(mockLine.getId());
        verifyNoMoreInteractions(mockCategoryRepository);
    }

    @Test
    @DisplayName("return category ID by its name")
    public void searchCategoryIdByCategoryNameTest() {
        CategoryEntity mockCategory = new CategoryEntity("AresTB", (short) 1);
        when(mockCategoryRepository.findByCategoryName(mockCategory.getCategoryName())).thenReturn(mockCategory);

        Short result = categoryService.getCategoryIdByCategoryName(mockCategory.getCategoryName());

        assertEquals(mockCategory.getId(), result);
        verify(mockCategoryRepository).findByCategoryName(mockCategory.getCategoryName());
        verifyNoMoreInteractions(mockCategoryRepository);
    }

}
