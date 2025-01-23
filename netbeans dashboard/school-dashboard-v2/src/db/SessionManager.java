package db;

import java.util.Base64;

public class SessionManager {
    private static SessionManager instance;
    private int userId;
    private String username;
    private String sessionToken;
    private long expirationTime; // Expiration timestamp

    private static final int TOKEN_VALIDITY = 15 * 60 * 1000; // 15 minutes in milliseconds

    // Private constructor for Singleton
    private SessionManager() {}

    public static synchronized SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
        this.expirationTime = System.currentTimeMillis() + TOKEN_VALIDITY; // Set expiration time
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public boolean isSessionValid() {
        return System.currentTimeMillis() < expirationTime;
    }

    public void clearSession() {
        this.userId = 0;
        this.username = null;
        this.sessionToken = null;
        this.expirationTime = 0;
    }

    // Token encryption (Optional)
    public static String encryptToken(String token, String secretKey) throws Exception {
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES");
        javax.crypto.spec.SecretKeySpec keySpec = new javax.crypto.spec.SecretKeySpec(secretKey.getBytes(), "AES");
        cipher.init(javax.crypto.Cipher.ENCRYPT_MODE, keySpec);
        return Base64.getEncoder().encodeToString(cipher.doFinal(token.getBytes()));
    }

    public static String decryptToken(String encryptedToken, String secretKey) throws Exception {
        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES");
        javax.crypto.spec.SecretKeySpec keySpec = new javax.crypto.spec.SecretKeySpec(secretKey.getBytes(), "AES");
        cipher.init(javax.crypto.Cipher.DECRYPT_MODE, keySpec);
        return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedToken)));
    }
}

