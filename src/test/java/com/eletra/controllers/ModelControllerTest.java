package com.eletra.controllers;

import com.eletra.models.ModelEntity;
import com.eletra.models.CategoryEntity;
import com.eletra.services.ModelService;
import com.eletra.repositories.ModelRepository;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
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

    @AfterEach
    public void tearDown() {
        clearInvocations(mockModelRepository);
    }

    @Test
    public void getModelEntityListTest() {
        // Given
        List<ModelEntity> mockModelList = new ArrayList<>();
        mockModelList.add(new ModelEntity(category, "Model1", (short) 1));
        mockModelList.add(new ModelEntity(category, "Model2", (short) 2));
        when(mockModelRepository.findAll()).thenReturn(mockModelList);

        // When
        List<ModelEntity> result = modelController.getModelEntityList();

        // Then
        assertEquals(mockModelList, result);
        verify(mockModelRepository).findAll();
        verifyNoMoreInteractions(mockModelRepository);
    }

    @Test
    public void getModelEntityListByLineNameTest() {
        // Given
        List<ModelEntity> mockModelList = new ArrayList<>();
        mockModelList.add(new ModelEntity(category, "Model1", (short) 1));
        mockModelList.add(new ModelEntity(category, "Model2", (short) 2));
        when(mockModelService.getModelNameByCategoryName("Category1")).thenReturn(mockModelList);

        // When
        List<ModelEntity> result = modelController.getModelEntityByCategory("Category1");

        // Then
        assertEquals(mockModelList, result);
        verify(mockModelService).getModelNameByCategoryName("Category1");
        verifyNoMoreInteractions(mockModelService);
        verifyNoMoreInteractions(mockModelRepository);
    }

    @Test
    public void postModelEntityTest() {
        // Given
        ModelEntity mockModel = new ModelEntity(category, "Model1", (short) 1);
        when(mockModelRepository.save(mockModel)).thenReturn(mockModel);

        // When
        ModelEntity result = modelController.postModelEntity(mockModel);

        // Then
        assertEquals(mockModel, result);
        verify(mockModelRepository).save(mockModel);
        verifyNoMoreInteractions(mockModelRepository);
    }

    @Test
    public void deleteModelEntityWhenNotExistsTest() {
        // Given
        when(mockModelRepository.findByModelName("NonExistentModel")).thenReturn(null);

        // When
        ResponseEntity<Boolean> result = modelController.deleteModelEntity("NonExistentModel");

        // Then
        assertEquals(ResponseEntity.ok(false), result);
        verify(mockModelRepository).findByModelName("NonExistentModel");
        verifyNoMoreInteractions(mockModelRepository);
    }

    @Test
    public void deleteModelEntityTest() {
        // Given
        ModelEntity mockModel = new ModelEntity(category, "Model1", (short) 1);
        when(mockModelRepository.findByModelName("Model1")).thenReturn(mockModel);

        // When
        ResponseEntity<Boolean> result = modelController.deleteModelEntity("Model1");

        // Then
        assertEquals(ResponseEntity.ok(true), result);
        verify(mockModelRepository).findByModelName("Model1");
        verify(mockModelRepository).delete(mockModel);
        verifyNoMoreInteractions(mockModelRepository);
    }

    @Test
    public void updateModelEntityTest() {
        // Given
        ModelEntity mockModel = new ModelEntity(category, "Model1", (short) 1);
        when(mockModelRepository.save(mockModel)).thenReturn(mockModel);

        // When
        ModelEntity result = modelController.updateModelEntity(mockModel);

        // Then
        assertEquals(mockModel, result);
        verify(mockModelRepository).save(mockModel);
        verifyNoMoreInteractions(mockModelRepository);
    }
}
