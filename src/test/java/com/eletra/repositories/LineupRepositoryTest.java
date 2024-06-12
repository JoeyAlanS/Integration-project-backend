package com.eletra.repositories;

import com.eletra.models.LineupEntity;
import com.eletra.repositories.LineupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class LineupRepositoryTest {

    @InjectMocks
    private LineupRepository lineupRepository;

    @Mock
    private LineupRepository mockLineupRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findByLineName() {
        Lineup mockLineup = new Lineup("Line1", (short) 1);
        when(mockLineupRepository.findByLineName("Line1")).thenReturn(mockLineup);

        Lineup result = lineupRepository.findByLineName("Line1");

        assertEquals(mockLineup, result);
        verify(mockLineupRepository).findByLineName("Line1");
        verifyNoMoreInteractions(mockLineupRepository);
    }

    @Test
    void delete() {
        Lineup mockLineup = new Lineup("Line1", (short) 1);
        when(mockLineupRepository.findByLineName("Line1")).thenReturn(mockLineup);

        lineupRepository.delete(mockLineup);

        verify(mockLineupRepository).delete(mockLineup);
        verifyNoMoreInteractions(mockLineupRepository);
    }
}
