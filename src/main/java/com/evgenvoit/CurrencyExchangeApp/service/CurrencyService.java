package com.evgenvoit.CurrencyExchangeApp.service;

import com.evgenvoit.CurrencyExchangeApp.entity.Currency;

import java.util.List;

public interface CurrencyService {

    List<Currency> getAllCurrencies();

    Currency getCurrencyByCode(String code);

    void addCurrency(Currency currency);

    void deleteCurrency(String code);

    void deleteCurrencyById(Integer id);
}
