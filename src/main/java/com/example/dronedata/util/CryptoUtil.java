package com.example.dronedata.util;

import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class CryptoUtil {

    public static byte[] sha256(byte[] input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            return digest.digest(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static boolean verifyEcdsa(String publicKeyPem, byte[] message, String signatureBase64) {
        try {
            // Expecting PEM: -----BEGIN PUBLIC KEY----- base64 -----END PUBLIC KEY-----
            String cleaned = publicKeyPem
                    .replace("-----BEGIN PUBLIC KEY-----", "")
                    .replace("-----END PUBLIC KEY-----", "")
                    .replaceAll("\s", "");
            byte[] keyBytes = Base64.getDecoder().decode(cleaned);
            KeyFactory kf = KeyFactory.getInstance("EC");
            PublicKey pub = kf.generatePublic(new X509EncodedKeySpec(keyBytes));

            Signature ecdsa = Signature.getInstance("SHA256withECDSA");
            ecdsa.initVerify(pub);
            ecdsa.update(message);
            byte[] sig = Base64.getDecoder().decode(signatureBase64);
            return ecdsa.verify(sig);
        } catch (Exception e) {
            return false;
        }
    }
}
