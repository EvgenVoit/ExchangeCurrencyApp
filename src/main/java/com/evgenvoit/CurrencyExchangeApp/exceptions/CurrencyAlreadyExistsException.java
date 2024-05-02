package com.evgenvoit.CurrencyExchangeApp.exceptions;

public class CurrencyAlreadyExistsException extends RuntimeException{
    private String code;

    public CurrencyAlreadyExistsException(String code) {
        super("Currency already exists: " + code);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
