package com.evgenvoit.CurrencyExchangeApp.exceptions;

public class ExchangeRateNotFoundException extends RuntimeException {
    private String baseCurrencyCode;
    private String targetCurrencyCode;

    public ExchangeRateNotFoundException(String baseCurrencyCode, String targetCurrencyCode) {
        super("Exchange Rate not found: " + baseCurrencyCode + "/" + targetCurrencyCode);
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
