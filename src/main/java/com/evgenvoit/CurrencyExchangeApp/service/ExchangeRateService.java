package com.evgenvoit.CurrencyExchangeApp.service;

import com.evgenvoit.CurrencyExchangeApp.entity.Currency;
import com.evgenvoit.CurrencyExchangeApp.entity.ExchangeRate;

import java.math.BigDecimal;
import java.util.List;

public interface ExchangeRateService {
    List<ExchangeRate> getAllRates();

    List<ExchangeRate> getRateByCode(String baseCurrencyCode, String targetCurrencyCode);

    void addExchangeRate(String baseCurrencyCode, String targetCurrencyCode, BigDecimal rate);

    void updateExchangeRate(String baseCurrencyCode, String targetCurrencyCode, BigDecimal rate);

    boolean existsByBaseCurrencyAndTargetCurrency(String baseCurrencyCode, String targetCurrencyCode);
}
