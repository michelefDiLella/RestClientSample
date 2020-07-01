package com.example.demo;

import java.util.Date;
import java.util.UUID;

public class SessionTokenGenerator {
    public static SessionToken generateToken() {
        SessionToken token = new SessionToken();
        token.setCreated(new Date().getTime());
        token.setGuid(UUID.randomUUID().toString());
        token.setProductType("SampleProduct");
        token.setSubjectId(RandomString.getAlphaNumericString(16));
        token.setVATNumber(RandomString.getAlphaNumericString(11));
        return token;
    }
}
