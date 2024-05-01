package com.evgenvoit.CurrencyExchangeApp.service;

import java.math.BigDecimal;

public interface ExchangeService {

    BigDecimal convertAmount(String baseCurrency, String targetCurrency);
}
