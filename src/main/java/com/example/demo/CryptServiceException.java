package com.example.demo;

import java.security.GeneralSecurityException;

public class CryptServiceException extends Exception {
    public CryptServiceException(GeneralSecurityException e) {
        super(e);
    }
}
