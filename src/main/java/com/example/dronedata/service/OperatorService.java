package com.example.dronedata.service;

import com.example.dronedata.model.Operator;
import com.example.dronedata.repo.OperatorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperatorService {

    private final OperatorRepository repo;

    public OperatorService(OperatorRepository repo) {
        this.repo = repo;
    }

    public Operator register(String operatorId, String publicKeyPem) {
        Optional<Operator> existing = repo.findByOperatorId(operatorId);
        if (existing.isPresent()) return existing.get();
        return repo.save(new Operator(operatorId, publicKeyPem));
    }

    public Optional<Operator> findById(String operatorId) {
        return repo.findByOperatorId(operatorId);
    }
}
