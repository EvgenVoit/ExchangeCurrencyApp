package com.evgenvoit.CurrencyExchangeApp.service;

import com.evgenvoit.CurrencyExchangeApp.dao.CurrencyRepository;
import com.evgenvoit.CurrencyExchangeApp.entity.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }
}
