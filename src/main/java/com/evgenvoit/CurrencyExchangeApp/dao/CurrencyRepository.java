package com.evgenvoit.CurrencyExchangeApp.dao;

import com.evgenvoit.CurrencyExchangeApp.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
}
