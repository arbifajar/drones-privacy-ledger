package com.example.dronedata.repo;

import com.example.dronedata.model.LedgerBlock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LedgerBlockRepository extends JpaRepository<LedgerBlock, Long> {
    Optional<LedgerBlock> findTopByOrderByHeightDesc();
    Optional<LedgerBlock> findByHeight(long height);
}
