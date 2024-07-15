package com.eletra.services;

import com.eletra.models.LineupEntity;
import com.eletra.repositories.LineupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
public class LineupServiceTest {

    @InjectMocks
    private LineupService lineupService;

    @Mock
    private LineupRepository mockLineupRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("return lineup for given line name")
    public void findByLineName() {
        //Given
        LineupEntity mockLineup = new LineupEntity("Ares", (short) 1);
        when(mockLineupRepository.findByLineName(mockLineup.getLineName())).thenReturn(mockLineup);

        //When
        Short result = lineupService.getLineIdByLineName(mockLineup.getLineName());

        //Then
        assertEquals(mockLineup.getId(), result);
        verify(mockLineupRepository).findByLineName(mockLineup.getLineName());
        verifyNoMoreInteractions(mockLineupRepository);
    }

}
