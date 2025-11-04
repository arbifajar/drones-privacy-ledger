import java.security.*;
import java.util.Base64;

public class keygen {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("EC");
        kpg.initialize(256);
        KeyPair kp = kpg.generateKeyPair();

        String pubPem = "-----BEGIN PUBLIC KEY-----\n" +
                Base64.getEncoder().encodeToString(kp.getPublic().getEncoded()) +
                "\n-----END PUBLIC KEY-----";
        System.out.println(pubPem);

        String message = "droneA|2025-11-04T12:00:00Z|{\"alt\":120}";
        Signature ecdsa = Signature.getInstance("SHA256withECDSA");
        ecdsa.initSign(kp.getPrivate());
        ecdsa.update(message.getBytes());
        String sig = Base64.getEncoder().encodeToString(ecdsa.sign());
        System.out.println("signatureBase64=" + sig);
    }
}
