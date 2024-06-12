package com.eletra.repositories;

import com.eletra.models.ModelEntity;
import com.eletra.repositories.ModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class ModelRepositoryTest {

    @InjectMocks
    private ModelRepository modelRepository;

    @Mock
    private ModelRepository mockModelRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findByModelName() {
        Model mockModel = new Model("Model1", (short) 1);
        when(mockModelRepository.findByModelName("Model1")).thenReturn(mockModel);

        Model result = modelRepository.findByModelName("Model1");

        assertEquals(mockModel, result);
        verify(mockModelRepository).findByModelName("Model1");
        verifyNoMoreInteractions(mockModelRepository);
    }

    @Test
    void findByCategoryId() {
        Model mockModel = new Model("Model1", (short) 1);
        when(mockModelRepository.findByCategoryId(1)).thenReturn(Collections.singletonList(mockModel));

        List<Model> result = modelRepository.findByCategoryId(1);

        assertEquals(Collections.singletonList(mockModel), result);
        verify(mockModelRepository).findByCategoryId(1);
        verifyNoMoreInteractions(mockModelRepository);
    }

    @Test
    void delete() {
        Model mockModel = new Model("Model1", (short) 1);
        when(mockModelRepository.findByModelName("Model1")).thenReturn(mockModel);

        modelRepository.delete(mockModel);

        verify(mockModelRepository).delete(mockModel);
        verifyNoMoreInteractions(mockModelRepository);
    }
}
