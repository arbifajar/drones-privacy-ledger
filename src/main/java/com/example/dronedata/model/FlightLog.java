package com.example.dronedata.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

@Entity
@Table(name = "flight_logs")
public class FlightLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Operator operatorRef;

    @NotBlank
    private String droneId;

    @NotNull
    private Instant timestamp;

    @Lob
    @NotBlank
    private String logData; // JSON payload

    @Lob
    @NotBlank
    private String signatureBase64; // ECDSA signature over (droneId|timestamp|logData)

    // hash of logData for block calculation
    @NotBlank
    private String dataHashHex;

    public FlightLog() {}

    public FlightLog(Operator op, String droneId, Instant timestamp, String logData, String signatureBase64, String dataHashHex) {
        this.operatorRef = op;
        this.droneId = droneId;
        this.timestamp = timestamp;
        this.logData = logData;
        this.signatureBase64 = signatureBase64;
        this.dataHashHex = dataHashHex;
    }

    public Long getId() { return id; }
    public Operator getOperatorRef() { return operatorRef; }
    public String getDroneId() { return droneId; }
    public Instant getTimestamp() { return timestamp; }
    public String getLogData() { return logData; }
    public String getSignatureBase64() { return signatureBase64; }
    public String getDataHashHex() { return dataHashHex; }

    public void setId(Long id) { this.id = id; }
    public void setOperatorRef(Operator operatorRef) { this.operatorRef = operatorRef; }
    public void setDroneId(String droneId) { this.droneId = droneId; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
    public void setLogData(String logData) { this.logData = logData; }
    public void setSignatureBase64(String signatureBase64) { this.signatureBase64 = signatureBase64; }
    public void setDataHashHex(String dataHashHex) { this.dataHashHex = dataHashHex; }
}
