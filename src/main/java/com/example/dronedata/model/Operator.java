package com.example.dronedata.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "operators", uniqueConstraints = @UniqueConstraint(columnNames = "operatorId"))
public class Operator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String operatorId; // human-readable ID

    @Lob
    @NotBlank
    private String publicKeyPem; // PEM-encoded public key (ECDSA)

    public Operator() {}

    public Operator(String operatorId, String publicKeyPem) {
        this.operatorId = operatorId;
        this.publicKeyPem = publicKeyPem;
    }

    public Long getId() { return id; }
    public String getOperatorId() { return operatorId; }
    public String getPublicKeyPem() { return publicKeyPem; }
    public void setId(Long id) { this.id = id; }
    public void setOperatorId(String operatorId) { this.operatorId = operatorId; }
    public void setPublicKeyPem(String publicKeyPem) { this.publicKeyPem = publicKeyPem; }
}
