package com.example.demo;

public class TokenClass {

    private String tokenName;
    private int tokenCode;
    private float tokenValue;

    public String getTokenName() {
        return tokenName;
    }

    public void setTokenName(String tokenName) {
        this.tokenName = tokenName;
    }

    public int getTokenCode() {
        return tokenCode;
    }

    public void setTokenCode(int tokenCode) {
        this.tokenCode = tokenCode;
    }

    public float getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(float tokenValue) {
        this.tokenValue = tokenValue;
    }

    @Override
    public String
    toString() {
        return "TokenClass{" +
                "tokenName='" + tokenName + '\'' +
                ", tokenCode=" + tokenCode +
                ", tokenValue=" + tokenValue +
                '}';
    }
}
