package com.evgenvoit.CurrencyExchangeApp.service;

import com.evgenvoit.CurrencyExchangeApp.entity.ExchangeRate;

import java.util.List;

public interface ExchangeRateService {
    public List<ExchangeRate> getAllRates();
}
