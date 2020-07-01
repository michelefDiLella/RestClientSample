package com.example.demo;

public interface CryptService {
    String encrypt(String message) throws Exception;

    String decrypt(String message) throws Exception;
}
