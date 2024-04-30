package com.evgenvoit.CurrencyExchangeApp.controller;

import com.evgenvoit.CurrencyExchangeApp.entity.Currency;
import com.evgenvoit.CurrencyExchangeApp.entity.ExchangeRate;
import com.evgenvoit.CurrencyExchangeApp.service.CurrencyService;
import com.evgenvoit.CurrencyExchangeApp.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ExchangeRateService exchangeRateService;

    @GetMapping("/currencies")
    public List<Currency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    @GetMapping("/rates")
    public List<ExchangeRate> getAllRates() {
        return exchangeRateService.getAllRates();
    }

}
