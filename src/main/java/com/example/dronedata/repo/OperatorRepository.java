package com.example.dronedata.repo;

import com.example.dronedata.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperatorRepository extends JpaRepository<Operator, Long> {
    Optional<Operator> findByOperatorId(String operatorId);
}
