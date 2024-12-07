package db;

import java.security.SecureRandom;
import java.util.Base64;

public class TokenGenerator {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateToken() {
        byte[] randomBytes = new byte[32];
        secureRandom.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    public static String generateEncryptedToken(String secretKey) throws Exception {
        String rawToken = generateToken();
        return SessionManager.encryptToken(rawToken, secretKey);
    }
}

