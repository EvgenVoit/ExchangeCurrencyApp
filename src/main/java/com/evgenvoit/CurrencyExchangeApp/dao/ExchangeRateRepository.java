package com.evgenvoit.CurrencyExchangeApp.dao;

import com.evgenvoit.CurrencyExchangeApp.entity.Currency;
import com.evgenvoit.CurrencyExchangeApp.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Integer> {
    List<ExchangeRate> findByBaseCurrencyAndTargetCurrency(Currency baseCurrencyCode, Currency targetCurrencyCode);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO exchange_rates (base_currency_Id, target_currency_Id, rate) VALUES (:baseCurrencyId, :targetCurrencyId, :rate)", nativeQuery = true)
    Integer addExchangeRate(Integer baseCurrencyId, Integer targetCurrencyId, BigDecimal rate);

    @Transactional
    @Modifying
    @Query("UPDATE ExchangeRate e SET e.rate = :newRate WHERE e.baseCurrency.id = :baseCurrencyId AND e.targetCurrency.id = :targetCurrencyId")
    void updateRateByCurrencies(Integer baseCurrencyId, Integer targetCurrencyId, BigDecimal newRate);


}
