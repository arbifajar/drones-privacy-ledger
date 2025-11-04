package com.example.dronedata.repo;

import com.example.dronedata.model.FlightLog;
import com.example.dronedata.model.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightLogRepository extends JpaRepository<FlightLog, Long> {
    List<FlightLog> findByOperatorRef(Operator operator);
}
