package com.eletra.repositories;

import com.eletra.models.CategoryEntity;
import com.eletra.models.ModelEntity;
import com.eletra.services.CategoryService;
import com.eletra.services.ModelService;
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
class ModelRepositoryTest {

    @InjectMocks
    private ModelService modelService;

    @Mock
    private CategoryService mockCategoryService;

    @Mock
    private ModelRepository mockModelRepository;

    private CategoryEntity mockCategory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockCategory = new CategoryEntity("Ares TB", (short) 0);
    }

    @Test
    @DisplayName("Should find models by category name")
    public void searchModelNameByCategoryNameTest() {
        ModelEntity mockModel = new ModelEntity(mockCategory, "Ares 7021", (short) 1);

        when(mockCategoryService.getCategoryIdByCategoryName(mockCategory.getCategoryName())).thenReturn(mockCategory.getId());
        when(mockModelRepository.findByCategoryId(mockCategory.getId())).thenReturn(Collections.singletonList(mockModel));

        List<ModelEntity> result = modelService.getModelNameByCategoryName(mockCategory.getCategoryName());

        assertEquals(Collections.singletonList(mockModel), result);
        verify(mockModelRepository).findByCategoryId(mockCategory.getId());
        verifyNoMoreInteractions(mockModelRepository);
    }
}
