package com.evgenvoit.CurrencyExchangeApp.dao;

import com.evgenvoit.CurrencyExchangeApp.entity.Currency;
import com.evgenvoit.CurrencyExchangeApp.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeRepository extends JpaRepository<ExchangeRate, Integer> {
    ExchangeRate findByBaseCurrencyCodeAndTargetCurrencyCode(String baseCurrencyCode, String targetCurrencyCode);
}
