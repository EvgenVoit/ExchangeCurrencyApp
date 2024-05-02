package com.evgenvoit.CurrencyExchangeApp.exceptions;

public class ExchangeRateAlreadyExists extends RuntimeException{
    private String baseCurrencyCode;
    private String targetCurrencyCode;

    public ExchangeRateAlreadyExists(String baseCurrencyCode, String targetCurrencyCode) {
        super("Exchange Rate already exists: " + baseCurrencyCode + "/" + targetCurrencyCode);
        this.baseCurrencyCode = baseCurrencyCode;
        this.targetCurrencyCode = targetCurrencyCode;
    }

    public String getBaseCurrencyCode() {
        return baseCurrencyCode;
    }

    public String getTargetCurrencyCode() {
        return targetCurrencyCode;
    }
}
