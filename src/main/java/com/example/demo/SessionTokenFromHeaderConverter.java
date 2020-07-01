package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

    @Component
    public class SessionTokenFromHeaderConverter implements Converter<String, SessionToken> {

        private final static Logger LOGGER = LoggerFactory.getLogger(SessionTokenFromHeaderConverter.class);

        @Autowired
        private CryptServiceImpl cryptService;

        @Override
        public SessionToken convert(final String encryptedToken) {
            SessionToken token = null;
            String decryptedTokenJson = null;
            String result = null;
            try {
                decryptedTokenJson = cryptService.decrypt(encryptedToken);
                ObjectMapper om = new ObjectMapper();
                token = om.readValue(decryptedTokenJson, SessionToken.class);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error("Error while converting session token header '" + token + "'");
            }
            return token;
        }
    }
