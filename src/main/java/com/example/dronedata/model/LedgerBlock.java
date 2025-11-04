package com.example.dronedata.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "ledger_blocks")
public class LedgerBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long height; // block height (genesis = 0)
    private Instant timestamp;
    private String previousHashHex;
    private String dataHashHex; // combined hash of included records
    private String blockHashHex;

    @Lob
    private String flights; // CSV of FlightLog IDs for simplicity

    public LedgerBlock() {}

    public LedgerBlock(long height, Instant timestamp, String previousHashHex, String dataHashHex, String blockHashHex, String flights) {
        this.height = height;
        this.timestamp = timestamp;
        this.previousHashHex = previousHashHex;
        this.dataHashHex = dataHashHex;
        this.blockHashHex = blockHashHex;
        this.flights = flights;
    }

    public Long getId() { return id; }
    public long getHeight() { return height; }
    public Instant getTimestamp() { return timestamp; }
    public String getPreviousHashHex() { return previousHashHex; }
    public String getDataHashHex() { return dataHashHex; }
    public String getBlockHashHex() { return blockHashHex; }
    public String getFlights() { return flights; }

    public void setId(Long id) { this.id = id; }
    public void setHeight(long height) { this.height = height; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
    public void setPreviousHashHex(String previousHashHex) { this.previousHashHex = previousHashHex; }
    public void setDataHashHex(String dataHashHex) { this.dataHashHex = dataHashHex; }
    public void setBlockHashHex(String blockHashHex) { this.blockHashHex = blockHashHex; }
    public void setFlights(String flights) { this.flights = flights; }
}
