package com.evgenvoit.CurrencyExchangeApp.service;

import com.evgenvoit.CurrencyExchangeApp.entity.ExchangeRate;

import java.math.BigDecimal;

public interface ExchangeService {

    BigDecimal convertAmount(String baseCurrency, String targetCurrency, BigDecimal amount);
}
