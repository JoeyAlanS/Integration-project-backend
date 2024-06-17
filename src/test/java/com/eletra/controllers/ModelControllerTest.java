package com.eletra.controllers;

import com.eletra.models.ModelEntity;
import com.eletra.models.CategoryEntity;
import com.eletra.services.ModelService;
import com.eletra.repositories.ModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ModelControllerTest {

    @InjectMocks
    private ModelController modelController;

    @Mock
    private ModelRepository mockModelRepository;

    @Mock
    private ModelService mockModelService;

    private CategoryEntity category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        category = new CategoryEntity("Category1", (short) 1);
    }

    @Test
    void getModelEntityListTest() {
        List<ModelEntity> mockModelList = new ArrayList<>();
        mockModelList.add(new ModelEntity(category, "Model1", (short) 1));
        mockModelList.add(new ModelEntity(category, "Model2", (short) 2));

        when(mockModelRepository.findAll()).thenReturn(mockModelList);

        List<ModelEntity> result = modelController.getModelEntityList();

        assertEquals(mockModelList, result);
        verify(mockModelRepository).findAll();
        verifyNoMoreInteractions(mockModelRepository);
    }

    @Test
    void getModelEntityListByLineNameTest() {
        List<ModelEntity> mockModelList = new ArrayList<>();
        mockModelList.add(new ModelEntity(category, "Model1", (short) 1));
        mockModelList.add(new ModelEntity(category, "Model2", (short) 2));

        when(mockModelService.getModelNameByCategoryName("Category1")).thenReturn(mockModelList);

        List<ModelEntity> result = modelController.getModelEntityByCategory("Category1");

        assertEquals(mockModelList, result);
        verify(mockModelService).getModelNameByCategoryName("Category1");
        verifyNoMoreInteractions(mockModelService);
        verifyNoMoreInteractions(mockModelRepository);
    }

    @Test
    void postModelEntityTest() {
        ModelEntity mockModel = new ModelEntity(category, "Model1", (short) 1);

        when(mockModelRepository.save(mockModel)).thenReturn(mockModel);

        ModelEntity result = modelController.postModelEntity(mockModel);

        assertEquals(mockModel, result);
        verify(mockModelRepository).save(mockModel);
        verifyNoMoreInteractions(mockModelRepository);
    }

    @Test
    void deleteCategoryEntityTest() {
        ModelEntity mockModel = new ModelEntity(category, "Model1", (short) 1);

        when(mockModelRepository.findByModelName("Model1")).thenReturn(mockModel);

        ResponseEntity<Boolean> result = modelController.deleteModelEntity("Model1");

        assertEquals(ResponseEntity.ok(true), result);
        verify(mockModelRepository).findByModelName("Model1");
        verify(mockModelRepository).delete(mockModel);
        verifyNoMoreInteractions(mockModelRepository);
    }

    @Test
    void updateModelEntityTest() {
        ModelEntity mockModel = new ModelEntity(category, "Model1", (short) 1);

        when(mockModelRepository.save(mockModel)).thenReturn(mockModel);

        ModelEntity result = modelController.updateModelEntity(mockModel);

        assertEquals(mockModel, result);
        verify(mockModelRepository).save(mockModel);
        verifyNoMoreInteractions(mockModelRepository);
    }
}
