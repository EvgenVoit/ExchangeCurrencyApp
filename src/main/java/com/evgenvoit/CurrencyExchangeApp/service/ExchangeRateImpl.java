package com.evgenvoit.CurrencyExchangeApp.service;

import com.evgenvoit.CurrencyExchangeApp.dao.ExchangeRateRepository;
import com.evgenvoit.CurrencyExchangeApp.entity.ExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExchangeRateImpl implements ExchangeRateService{

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Override
    public List<ExchangeRate> getAllRates() {
        return exchangeRateRepository.findAll();
    }
}
