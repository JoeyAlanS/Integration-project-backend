package com.eletra.controllers;

import com.eletra.models.LineupEntity;
import com.eletra.repositories.LineupRepository;
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
class LineupControllerTest {

    @InjectMocks
    private LineupController lineupController;

    @Mock
    private LineupRepository mockLineupRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @AfterEach
    public void tearDown() {
        clearInvocations(mockLineupRepository);
    }

    @Test
    public void getLineupEntityListTest() {
        // Given
        List<LineupEntity> mockLineList = new ArrayList<>();
        mockLineList.add(new LineupEntity("Line1", (short) 1));
        mockLineList.add(new LineupEntity("Line2", (short) 2));
        when(mockLineupRepository.findAll()).thenReturn(mockLineList);

        // When
        List<LineupEntity> result = lineupController.getLineupEntityList();

        // Then
        assertEquals(mockLineList, result);
        verify(mockLineupRepository).findAll();
        verifyNoMoreInteractions(mockLineupRepository);
    }

    @Test
    public void getLineupEntityTest() {
        // Given
        LineupEntity mockLine = new LineupEntity("Line1", (short) 1);
        when(mockLineupRepository.findByLineName("Line1")).thenReturn(mockLine);

        // When
        LineupEntity result = lineupController.getLineupEntity("Line1");

        // Then
        assertEquals(mockLine, result);
        verify(mockLineupRepository).findByLineName("Line1");
        verifyNoMoreInteractions(mockLineupRepository);
    }

    @Test
    public void postLineupEntityTest() {
        // Given
        LineupEntity mockLine = new LineupEntity("line1", (short) 1);
        when(mockLineupRepository.save(mockLine)).thenReturn(mockLine);

        // When
        LineupEntity result = lineupController.postLineupEntity(mockLine);

        // Then
        assertEquals(mockLine, result);
        verify(mockLineupRepository).save(mockLine);
        verifyNoMoreInteractions(mockLineupRepository);
    }

    @Test
    public void deleteLineupEntityWhenNotExistsTest() {
        // Given
        when(mockLineupRepository.findByLineName("NonExistentLine")).thenReturn(null);

        // When
        ResponseEntity<Boolean> result = lineupController.deleteLineupEntity("NonExistentLine");

        // Then
        assertEquals(ResponseEntity.status(HttpStatus.NOT_FOUND).body(false), result);
        verify(mockLineupRepository).findByLineName("NonExistentLine");
        verifyNoMoreInteractions(mockLineupRepository);
    }

    @Test
    public void deleteLineupEntityTest() {
        // Given
        LineupEntity mockLine = new LineupEntity("Line1", (short) 1);
        when(mockLineupRepository.findByLineName("Line1")).thenReturn(mockLine);
        doNothing().when(mockLineupRepository).delete(mockLine);

        // When
        ResponseEntity<Boolean> result = lineupController.deleteLineupEntity("Line1");

        // Then
        assertEquals(ResponseEntity.ok(true), result);
        verify(mockLineupRepository).findByLineName("Line1");
        verify(mockLineupRepository).delete(mockLine);
        verifyNoMoreInteractions(mockLineupRepository);
    }

    @Test
    public void putLineupEntityTest() {
        // Given
        LineupEntity mockLine = new LineupEntity("Line1", (short) 1);
        when(mockLineupRepository.save(mockLine)).thenReturn(mockLine);

        // When
        LineupEntity result = lineupController.updateLineupyEntity(mockLine);

        // Then
        assertEquals(mockLine, result);
        verify(mockLineupRepository).save(mockLine);
        verifyNoMoreInteractions(mockLineupRepository);
    }
}
