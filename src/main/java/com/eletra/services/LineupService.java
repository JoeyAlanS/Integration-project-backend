package com.eletra.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eletra.models.LineupEntity;
import com.eletra.repositories.LineupRepository;


@Service
public class LineupService {

    @Autowired
    private LineupRepository lineupRepository;

    public Short getLineIdByLineName(String lineName) {
        LineupEntity lineupEntity = lineupRepository.findByLineName(lineName);
        return lineupEntity.getId();
    }

}