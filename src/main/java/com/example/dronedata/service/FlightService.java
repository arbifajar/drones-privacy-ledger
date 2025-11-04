package com.example.dronedata.service;

import com.example.dronedata.model.FlightLog;
import com.example.dronedata.model.Operator;
import com.example.dronedata.repo.FlightLogRepository;
import com.example.dronedata.util.CryptoUtil;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.List;

@Service
public class FlightService {
    private final FlightLogRepository repo;

    public FlightService(FlightLogRepository repo) {
        this.repo = repo;
    }

    public FlightLog save(Operator op, String droneId, Instant timestamp, String logData, String signatureBase64) {
        String message = droneId + "|" + timestamp.toString() + "|" + logData;
        String dataHash = CryptoUtil.toHex(CryptoUtil.sha256(logData.getBytes(StandardCharsets.UTF_8)));
        FlightLog log = new FlightLog(op, droneId, timestamp, logData, signatureBase64, dataHash);
        return repo.save(log);
    }

    public List<FlightLog> all() { return repo.findAll(); }
}
