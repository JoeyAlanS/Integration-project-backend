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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ModelControllerTest {

    @InjectMocks
    private ModelController controller;

    @Mock
    private ModelRepository repository;

    @Mock
    private ModelService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getModelEntityList() {
        List<ModelEntity> mockModelList = new ArrayList<>();
        mockModelList.add(new ModelEntity("Model1", (short) 1));
        mockModelList.add(new ModelEntity("Model2", (short) 2));

        when(repository.findAll()).thenReturn(mockModelList);

        List<ModelEntity> result = controller.getModelEntityList();

        assertEquals(mockModelList, result);
        verify(repository).findAll();
        verifyNoMoreInteractions(repository);
    }

    @Test
    void getModelEntityByCategory() {
        List<ModelEntity> mockModelList = new ArrayList<>();
        mockModelList.add(new ModelEntity("Model1", (short) 1));
        mockModelList.add(new ModelEntity("Model2", (short) 2));
        CategoryEntity category = new CategoryEntity("Category1", (short) 1);

        when(service.getModelNameByCategoryName("Category1")).thenReturn(mockModelList);

        List<ModelEntity> result = controller.getModelEntity("Category1");

        assertEquals(mockModelList, result);
        verify(service).getModelNameByCategoryName("Category1");
        verifyNoMoreInteractions(service);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void postModelEntity() {
        ModelEntity mockModel = new ModelEntity("Model1", (short) 1);

        when(repository.save(mockModel)).thenReturn(mockModel);

        ModelEntity result = controller.postModelEntity(mockModel);

        assertEquals(mockModel, result);
        verify(repository).save(mockModel);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void deleteModelEntity() {
        ModelEntity mockModel = new ModelEntity("Model1", (short) 1);

        when(repository.findByModelName("Model1")).thenReturn(mockModel);

        String result = controller.deleteModelEntity("Model1");

        assertEquals("Model deleted", result);
        verify(repository).findByModelName("Model1");
        verify(repository).delete(mockModel);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void updateModelEntity() {
        ModelEntity mockModel = new ModelEntity("Model1", (short) 1);

        when(repository.save(mockModel)).thenReturn(mockModel);

        String result = controller.updateModelEntity(mockModel);

        assertEquals("Model updated", result);
        verify(repository).save(mockModel);
        verifyNoMoreInteractions(repository);
    }
}
