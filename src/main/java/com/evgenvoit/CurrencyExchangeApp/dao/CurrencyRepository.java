package com.evgenvoit.CurrencyExchangeApp.dao;

import com.evgenvoit.CurrencyExchangeApp.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {

    Currency getCurrencyByCode(String code);

    @Transactional
    void deleteCurrencyByCode(String code);

    @Transactional
    void deleteCurrencyById(Integer id);

}
