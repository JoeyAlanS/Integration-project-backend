package com.eletra.repositories;

import com.eletra.models.LineupEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface LineupRepository extends JpaRepository<LineupEntity, String> {
    LineupEntity findByLineName(String lineName);

    void delete(LineupEntity lineupEntity);
}