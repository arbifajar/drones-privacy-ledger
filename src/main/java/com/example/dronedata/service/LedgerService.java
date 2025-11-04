package com.example.dronedata.service;

import com.example.dronedata.model.FlightLog;
import com.example.dronedata.model.LedgerBlock;
import com.example.dronedata.repo.LedgerBlockRepository;
import com.example.dronedata.util.CryptoUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LedgerService {

    private final LedgerBlockRepository blockRepo;

    public LedgerService(LedgerBlockRepository blockRepo) {
        this.blockRepo = blockRepo;
        ensureGenesis();
    }

    @Transactional
    public synchronized LedgerBlock appendBlock(List<FlightLog> logs) {
        LedgerBlock tip = blockRepo.findTopByOrderByHeightDesc().orElseThrow();
        long newHeight = tip.getHeight() + 1L;
        String prevHash = tip.getBlockHashHex();

        String combined = logs.stream().map(FlightLog::getDataHashHex).collect(Collectors.joining("|"));
        String dataHash = CryptoUtil.toHex(CryptoUtil.sha256(combined.getBytes()));

        String header = newHeight + "|" + prevHash + "|" + dataHash + "|" + Instant.now().toString();
        String blockHash = CryptoUtil.toHex(CryptoUtil.sha256(header.getBytes()));
        String flightsCsv = logs.stream().map(l -> l.getId().toString()).collect(Collectors.joining(","));

        LedgerBlock block = new LedgerBlock(newHeight, Instant.now(), prevHash, dataHash, blockHash, flightsCsv);
        return blockRepo.save(block);
    }

    public boolean verifyChain() {
        Optional<LedgerBlock> topOpt = blockRepo.findTopByOrderByHeightDesc();
        if (topOpt.isEmpty()) return false;
        long max = topOpt.get().getHeight();
        String prevHash = "GENESIS";
        for (long i = 0; i <= max; i++) {
            LedgerBlock b = blockRepo.findByHeight(i).orElse(null);
            if (b == null) return false;
            if (i == 0) {
                if (!"GENESIS".equals(b.getPreviousHashHex())) return false;
                prevHash = b.getBlockHashHex();
            } else {
                if (!prevHash.equals(b.getPreviousHashHex())) return false;
                String header = b.getHeight() + "|" + b.getPreviousHashHex() + "|" + b.getDataHashHex() + "|" + b.getTimestamp().toString();
                String recomputed = CryptoUtil.toHex(CryptoUtil.sha256(header.getBytes()));
                if (!recomputed.equals(b.getBlockHashHex())) return false;
                prevHash = b.getBlockHashHex();
            }
        }
        return true;
    }

    private void ensureGenesis() {
        if (blockRepo.findTopByOrderByHeightDesc().isEmpty()) {
            LedgerBlock genesis = new LedgerBlock(
                    0L,
                    Instant.parse("2020-01-01T00:00:00Z"),
                    "GENESIS",
                    CryptoUtil.toHex(CryptoUtil.sha256("GENESIS".getBytes())),
                    CryptoUtil.toHex(CryptoUtil.sha256("GENESIS|GENESIS".getBytes())),
                    ""
            );
            blockRepo.save(genesis);
        }
    }
}
