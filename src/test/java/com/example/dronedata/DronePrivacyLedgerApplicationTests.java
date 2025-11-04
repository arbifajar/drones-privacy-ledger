package com.example.dronedata;

import com.example.dronedata.model.Operator;
import com.example.dronedata.service.OperatorService;
import com.example.dronedata.service.LedgerService;
import com.example.dronedata.util.CryptoUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DronePrivacyLedgerApplicationTests {

    @Autowired
    private OperatorService operatorService;

    @Autowired
    private LedgerService ledgerService;

    @Test
    void sanity() throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        kpg.initialize(256);
        KeyPair kp = kpg.generateKeyPair();

        String pubPem = "-----BEGIN PUBLIC KEY-----\n" +
                Base64.getEncoder().encodeToString(kp.getPublic().getEncoded()) +
                "\n-----END PUBLIC KEY-----";

        Operator op = operatorService.register("op1", pubPem);
        assertNotNull(op.getId());

        String message = "droneA|2025-01-01T00:00:00Z|{\\"alt\\":100}";
        Signature ecdsa = Signature.getInstance("SHA256withECDSA");
        ecdsa.initSign(kp.getPrivate());
        ecdsa.update(message.getBytes(StandardCharsets.UTF_8));
        String sig = Base64.getEncoder().encodeToString(ecdsa.sign());

        assertTrue(CryptoUtil.verifyEcdsa(pubPem, message.getBytes(StandardCharsets.UTF_8), sig));
        assertTrue(ledgerService.verifyChain());
    }
}
