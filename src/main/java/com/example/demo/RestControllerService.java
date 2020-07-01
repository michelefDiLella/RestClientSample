package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/sample")
@SuppressWarnings("rawtypes")
public class RestControllerService {

    private final static Logger LOGGER = LoggerFactory.getLogger(RestControllerService.class);

    @Autowired
    private CryptServiceImpl cryptService;

    @PostMapping("/getstring")
    public String getString(@RequestBody String inputString) {
        LOGGER.info("## getString method - inputString: " + inputString);
        return inputString;
    }

    @PostMapping("/encryptstring")
    public String encryptString(@RequestBody String inputString) {
        LOGGER.info("## encryptString method - inputString: " + inputString);
        String result = null;
        try {
            result = cryptService.encrypt(inputString);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while encrypting input string '" + inputString + '"';
        }
        LOGGER.info("## encryptString method - encrypted string: " + result);
        return result;
    }

    @PostMapping("/decryptstring")
    public String decryptString(@RequestBody String inputString) {
        LOGGER.info("## decryptstring method - inputString: " + inputString);
        String result = null;
        try {
            result = cryptService.decrypt(inputString);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while decrypting input string '" + inputString + '"';
        }
        LOGGER.info("## decryptstring method - decrypted string: " + result);
        return result;
    }

    @GetMapping("/gettoken")
    public String decryptString() {
        LOGGER.info("## gettoken method");
        SessionToken token = SessionTokenGenerator.generateToken();
        ObjectMapper om = new ObjectMapper();
        String result = null;
        String jsonToken = null;
        try {
            jsonToken = om.writeValueAsString(token);
            result = cryptService.encrypt(jsonToken);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while generating token";
        }
        LOGGER.info("## gettoken method - generated token: " + result);
        return result;
    }

    @PostMapping("/showtoken")
    public String showToken(@RequestBody String token) {
        LOGGER.info("## showtoken method - token: " + token);
        String decryptedTokenJson = null;
        String result = null;
        SessionToken decryptedToken = null;
        try {
            decryptedTokenJson = cryptService.decrypt(token);
            ObjectMapper om = new ObjectMapper();
            decryptedToken = om.readValue(decryptedTokenJson, SessionToken.class);
            result = decryptedToken.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while decrypting input token '" + token + '"';
        }
        LOGGER.info("## showtoken method - decrypted token object: " + result);
        return result;
    }

    @GetMapping("/showheadertoken")
    public String showHeaderToken(@RequestHeader("X-Onboarding-Session-Token") SessionToken token) {
        LOGGER.info("## showHeaderToken method");
        return token.toString();
    }

    @PostMapping("/showtoken")
    public String elaboraMotoreUno(@RequestBody String token) {
        LOGGER.info("## showtoken method - token: " + token);
        String decryptedTokenJson = null;
        String result = null;
        SessionToken decryptedToken = null;
        try {
            decryptedTokenJson = cryptService.decrypt(token);
            ObjectMapper om = new ObjectMapper();
            decryptedToken = om.readValue(decryptedTokenJson, SessionToken.class);
            result = decryptedToken.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error while decrypting input token '" + token + '"';
        }
        LOGGER.info("## showtoken method - decrypted token object: " + result);
        return result;
    }
}
