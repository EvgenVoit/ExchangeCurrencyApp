package com.evgenvoit.CurrencyExchangeApp.controller;

import com.evgenvoit.CurrencyExchangeApp.dto.ExchangeRateDTO;
import com.evgenvoit.CurrencyExchangeApp.entity.Currency;
import com.evgenvoit.CurrencyExchangeApp.entity.ExchangeRate;
import com.evgenvoit.CurrencyExchangeApp.service.CurrencyService;
import com.evgenvoit.CurrencyExchangeApp.service.ExchangeRateService;
import com.evgenvoit.CurrencyExchangeApp.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class Controller {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping("/swagger")
    public ModelAndView redirectToSwaggerUi() {
        return new ModelAndView( "redirect:/swagger-ui/index.html");
    }

    @GetMapping("/currencies") //working
    public List<Currency> getAllCurrencies() {
        return currencyService.getAllCurrencies();
    }

    @GetMapping("/exchangeRates") //working
    public List<ExchangeRate> getAllRates() {
        return exchangeRateService.getAllRates();
    }

    @GetMapping("/currency/{code}")  //working
    public Currency getCurrencyByCode(@PathVariable String code) {
        return currencyService.getCurrencyByCode(code);
    }

    @GetMapping("/exchangeRate/{baseCode}/{targetCode}") // working
    public List<ExchangeRate> getRateByCode(@PathVariable String baseCode, @PathVariable String targetCode) {
        return exchangeRateService.getRateByCode(baseCode, targetCode);
    }

    @PostMapping("/currencies") //working
    public void addCurrency(@RequestBody Currency currency) {
        currencyService.addCurrency(currency);
    }

    @DeleteMapping("/currency/{code}") // working
    public void deleteCurrency(@PathVariable String code) {
        currencyService.deleteCurrency(code);
    }

    @PostMapping("/exchangeRates") // working
    public void addExchangeRate(@RequestBody ExchangeRateDTO exchangeRate) {
        exchangeRateService.addExchangeRate(exchangeRate.getBaseCurrencyCode(), exchangeRate.getTargetCurrencyCode(), exchangeRate.getRate());

    }

    @PatchMapping("/exchangeRate/{baseCode}/{targetCode}") //working
    public void updateExchangeRate(@PathVariable String baseCode, @PathVariable String targetCode, @RequestBody ExchangeRate rate) {
        exchangeRateService.updateExchangeRate(baseCode, targetCode, rate.getRate());
    }

    @GetMapping("/exchange/{baseCode}/{targetCode}/{amount}") // not working
    public String makeExchange(@PathVariable String baseCode, @PathVariable String targetCode, @PathVariable BigDecimal amount) {
        BigDecimal result = exchangeService.convertAmount(targetCode, baseCode);
        return "RESULT - " + result.multiply(amount);
    }


}
