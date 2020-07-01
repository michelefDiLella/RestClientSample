package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

@Service
public class CryptServiceImpl implements CryptService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CryptService.class);
    private static final Charset ENCODING = StandardCharsets.UTF_8;
    private static final String DEFAULT_KEY = "YIVUKVUYFUIT";

    @Value("${sessionTokenPassword}")
    private String cipherKey;

    @Override
    public String encrypt(String message) throws CryptServiceException {
        String encodedCipherText = null;
        try {
            if (cipherKey == null) cipherKey = DEFAULT_KEY;

            final MessageDigest md = MessageDigest.getInstance("md5");
            final byte[] digestOfPassword = md.digest(cipherKey.getBytes(ENCODING));
            final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            for (int j = 0, k = 16; j < 8; ) {
                keyBytes[k++] = keyBytes[j++];
            }

            final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
            final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);

            final byte[] plainTextBytes = message.getBytes(ENCODING);
            final byte[] cipherText = cipher.doFinal(plainTextBytes);
            encodedCipherText = Base64.getEncoder().encodeToString(cipherText);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new CryptServiceException(e);
        }

        return encodedCipherText;
    }

    @Override
    public String decrypt(String message) throws CryptServiceException {
        byte[] plainText;
        try {
            if (cipherKey == null) cipherKey = DEFAULT_KEY;

            final MessageDigest md = MessageDigest.getInstance("md5");
            final byte[] digestOfPassword = md.digest(cipherKey.getBytes(ENCODING));
            final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
            for (int j = 0, k = 16; j < 8;) {
                keyBytes[k++] = keyBytes[j++];
            }

            final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
            final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
            final Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
            decipher.init(Cipher.DECRYPT_MODE, key, iv);

            final byte[] byteMessage = Base64.getDecoder().decode(message);
            plainText = decipher.doFinal(byteMessage);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new CryptServiceException(e);
        }

        return new String(plainText, ENCODING);
    }
}
