package com.eletra.controllers;

import com.eletra.models.LineupEntity;
import com.eletra.repositories.LineupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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

    @Test
    void getLineupEntityListTest() {
        List<LineupEntity> mockLineList = new ArrayList<>();
        mockLineList.add(new LineupEntity("Line1", (short) 1));
        mockLineList.add(new LineupEntity("Line2", (short) 2));

        when(mockLineupRepository.findAll()).thenReturn(mockLineList);

        List<LineupEntity> result = lineupController.getLineupEntityList();

        assertEquals(mockLineList, result);
        verify(mockLineupRepository).findAll();
        verifyNoMoreInteractions(mockLineupRepository);
    }

    @Test
    void getLineupEntityTest() {
        LineupEntity mockLine = new LineupEntity("Line1", (short) 1);

        when(mockLineupRepository.findByLineName("Line1")).thenReturn(mockLine);

        LineupEntity result = lineupController.getLineupEntity("Line1");

        assertEquals(mockLine, result);
        verify(mockLineupRepository).findByLineName("Line1");
        verifyNoMoreInteractions(mockLineupRepository);
    }

    @Test
    void postLineupEntityTest() {
        LineupEntity mockLine = new LineupEntity("line1", (short) 1);

        when(mockLineupRepository.save(mockLine)).thenReturn(mockLine);

        LineupEntity result = lineupController.postLineupEntity(mockLine);

        assertEquals(mockLine, result);
        verify(mockLineupRepository).save(mockLine);
        verifyNoMoreInteractions(mockLineupRepository);
    }

    @Test
    void deleteLineupEntityTest() {
        LineupEntity mockLine = new LineupEntity("Line1", (short) 1);

        when(mockLineupRepository.findByLineName("Line1")).thenReturn(mockLine).thenReturn(null);
        doNothing().when(mockLineupRepository).delete(mockLine);

        ResponseEntity<Boolean> result = lineupController.deleteLineupEntity("Line1");

        assertEquals(ResponseEntity.ok(true), result);
        verify(mockLineupRepository, times(2)).findByLineName("Line1");
        verify(mockLineupRepository).delete(mockLine);
        verifyNoMoreInteractions(mockLineupRepository);
    }


    @Test
    void putLineupEntityTest() {
        LineupEntity mockLine = new LineupEntity("Line1", (short) 1);

        when(mockLineupRepository.save(mockLine)).thenReturn(mockLine);

        LineupEntity result = lineupController.updateLineupyEntity(mockLine);

        assertEquals(mockLine, result);
        verify(mockLineupRepository).save(mockLine);
        verifyNoMoreInteractions(mockLineupRepository);
    }
}
