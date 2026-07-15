package com.kaycheung.order_service.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

@Service
@Profile("test-security")
public class TestSecurityPasswordService {

    // VULNERABLE: MD5 is broken, unsalted, and fast → rainbow-tables / brute force.
    public String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(password.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public boolean verifyPassword(String password, String storedHash)
            throws NoSuchAlgorithmException {
        return hashPassword(password).equals(storedHash);
    }
}