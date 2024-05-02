package com.evgenvoit.CurrencyExchangeApp.service.ServiceImpl;

import com.evgenvoit.CurrencyExchangeApp.dao.CurrencyRepository;
import com.evgenvoit.CurrencyExchangeApp.dao.ExchangeRateRepository;
import com.evgenvoit.CurrencyExchangeApp.entity.Currency;
import com.evgenvoit.CurrencyExchangeApp.entity.ExchangeRate;
import com.evgenvoit.CurrencyExchangeApp.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ExchangeRateImpl implements ExchangeRateService {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    CurrencyRepository currencyRepository;

    @Override
    public List<ExchangeRate> getAllRates() {
        return exchangeRateRepository.findAll();
    }

    @Override
    public List<ExchangeRate> getRateByCode(String baseCurrencyCode, String targetCurrencyCode) {
        Currency baseCurrency = currencyRepository.getCurrencyByCode(baseCurrencyCode);
        Currency targetCurrency = currencyRepository.getCurrencyByCode(targetCurrencyCode);
        return exchangeRateRepository.findByBaseCurrencyAndTargetCurrency(baseCurrency, targetCurrency);

    }

    @Override
    public void addExchangeRate(String baseCurrencyCode, String targetCurrencyCode, BigDecimal rate) {
        Currency baseCurrency = currencyRepository.getCurrencyByCode(baseCurrencyCode);
        Currency targetCurrency = currencyRepository.getCurrencyByCode(targetCurrencyCode);
        Integer rowsInserted = exchangeRateRepository.addExchangeRate(baseCurrency.getId(), targetCurrency.getId(), rate);
    }

    @Override
    public void updateExchangeRate(String baseCurrencyCode, String targetCurrencyCode, BigDecimal rate) {
        Currency baseCurrency = currencyRepository.getCurrencyByCode(baseCurrencyCode);
        Currency targetCurrency = currencyRepository.getCurrencyByCode(targetCurrencyCode);
        exchangeRateRepository.updateRateByCurrencies(baseCurrency.getId(), targetCurrency.getId(), rate);
    }

    @Override
    public boolean existsByBaseCurrencyAndTargetCurrency(String baseCurrencyCode, String targetCurrencyCode) {
        return exchangeRateRepository.existsExchangeRateByBaseCurrency_CodeAndTargetCurrency_Code(baseCurrencyCode,targetCurrencyCode);
    }


}
