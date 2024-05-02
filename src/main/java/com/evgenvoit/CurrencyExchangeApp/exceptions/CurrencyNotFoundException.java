package com.evgenvoit.CurrencyExchangeApp.exceptions;

public class CurrencyNotFoundException extends RuntimeException {

    private String code;

    public CurrencyNotFoundException(String code) {
        super("Currency not found: " + code);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}