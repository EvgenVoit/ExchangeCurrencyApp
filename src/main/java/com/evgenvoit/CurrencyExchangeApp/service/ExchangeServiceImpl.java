package com.evgenvoit.CurrencyExchangeApp.service;

import com.evgenvoit.CurrencyExchangeApp.dao.CurrencyRepository;
import com.evgenvoit.CurrencyExchangeApp.dao.ExchangeRateRepository;
import com.evgenvoit.CurrencyExchangeApp.dao.ExchangeRepository;
import com.evgenvoit.CurrencyExchangeApp.entity.Currency;
import com.evgenvoit.CurrencyExchangeApp.entity.ExchangeRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExchangeServiceImpl implements ExchangeService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Override
    public BigDecimal convertAmount(String baseCurrencyCode, String targetCurrencyCode) {
//        Currency baseCurrency = currencyRepository.getCurrencyByCode(baseCurrencyCode);
//        Currency targetCurrency = currencyRepository.getCurrencyByCode(targetCurrencyCode);
//        List<ExchangeRate> exchangeRates = exchangeRateRepository.findByBaseCurrencyAndTargetCurrency(baseCurrency, targetCurrency);
//        ExchangeRate exchangeRate = exchangeRates.get(0);
//        BigDecimal rate = exchangeRate.getRate();
//        return rate.multiply(amount);

        ExchangeRate exchangeRate = exchangeRepository.findByBaseCurrencyCodeAndTargetCurrencyCode(baseCurrencyCode, targetCurrencyCode);
        if (exchangeRate == null) {
            return BigDecimal.ZERO;
        } else {
            return exchangeRate.getRate();
        }
    }
}
