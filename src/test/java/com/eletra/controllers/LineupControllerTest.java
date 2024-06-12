package com.eletra.controllers;

import com.eletra.models.LineupEntity;
import com.eletra.repositories.LineupRepository;
import org.junit.jupiter.api.BeforeEach;
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
class LineupControllerTest {

    @InjectMocks
    private LineupController controller;

    @Mock
    private LineupRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getLineupEntityList() {
        List<Lineup> mockLineupList = Collections.singletonList(new Lineup("Line1", (short) 1));
        when(repository.findAll()).thenReturn(mock
