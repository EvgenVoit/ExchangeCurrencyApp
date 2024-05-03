package com.evgenvoit.CurrencyExchangeApp.controller;

import com.evgenvoit.CurrencyExchangeApp.constants.GlobalConstants;
import com.evgenvoit.CurrencyExchangeApp.dto.ExchangeCurrencyDTO;
import com.evgenvoit.CurrencyExchangeApp.dto.ExchangeRateDTO;
import com.evgenvoit.CurrencyExchangeApp.entity.Currency;
import com.evgenvoit.CurrencyExchangeApp.entity.ExchangeRate;
import com.evgenvoit.CurrencyExchangeApp.exceptions.CurrencyAlreadyExistsException;
import com.evgenvoit.CurrencyExchangeApp.exceptions.CurrencyNotFoundException;
import com.evgenvoit.CurrencyExchangeApp.exceptions.ExchangeRateAlreadyExists;
import com.evgenvoit.CurrencyExchangeApp.exceptions.ExchangeRateNotFoundException;
import com.evgenvoit.CurrencyExchangeApp.service.CurrencyService;
import com.evgenvoit.CurrencyExchangeApp.service.ExchangeRateService;
import com.evgenvoit.CurrencyExchangeApp.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    @GetMapping("/swagger") // working
    public ModelAndView redirectToSwaggerUi() {
        return new ModelAndView("redirect:/swagger-ui/index.html");
    }

    @GetMapping("/currencies") //working test
    public ResponseEntity<List<Currency>> getAllCurrencies() {
        try {
            return new ResponseEntity<>(currencyService.getAllCurrencies(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/exchangeRates") //working test
    public ResponseEntity<List<ExchangeRate>> getAllRates() {
        return new ResponseEntity<>(exchangeRateService.getAllRates(), HttpStatus.OK);
    }

    @GetMapping("/currency/{code}")  //working test
    public ResponseEntity<Currency> getCurrencyByCode(@PathVariable String code) {
        if (currencyService.getCurrencyByCode(code) == null) {
            throw new CurrencyNotFoundException(code);
        }
        return new ResponseEntity<>(currencyService.getCurrencyByCode(code), HttpStatus.OK);
    }

    @GetMapping("/exchangeRate/{baseCode}/{targetCode}") // working test
    public ResponseEntity<List<ExchangeRate>> getRateByCode(@PathVariable String baseCode, @PathVariable String targetCode) {
        if (exchangeRateService.getRateByCode(baseCode, targetCode).isEmpty()) {
            throw new ExchangeRateNotFoundException(baseCode, targetCode);
        }
        return new ResponseEntity<>(exchangeRateService.getRateByCode(baseCode, targetCode), HttpStatus.OK);
    }

    @PostMapping("/currencies") //working test
    public ResponseEntity<String> addCurrency(@RequestBody Currency currency) {
        if (currencyService.existCurrencyByCode(currency.getCode())) {
            throw new CurrencyAlreadyExistsException(currency.getCode());
        } else {
            currencyService.addCurrency(currency);
        }
        return new ResponseEntity<>(GlobalConstants.ADDED_CURRENCY + currency.getCode(), HttpStatus.OK);
    }

    @DeleteMapping("/currency/{code}") // working test
    public ResponseEntity<String> deleteCurrency(@PathVariable String code) {
        if (currencyService.getCurrencyByCode(code) == null) {
            throw new CurrencyNotFoundException(code);
        }
        currencyService.deleteCurrency(code);
        return new ResponseEntity<>(GlobalConstants.DELETED_CURRENCY + code, HttpStatus.OK);
    }

    @PostMapping("/exchangeRates") // working test
    public ResponseEntity<String> addExchangeRate(@RequestBody ExchangeRateDTO exchangeRate) {
        if (exchangeRateService.existsByBaseCurrencyAndTargetCurrency(exchangeRate.getBaseCurrencyCode(), exchangeRate.getTargetCurrencyCode())) {
            throw new ExchangeRateAlreadyExists(exchangeRate.getBaseCurrencyCode(), exchangeRate.getTargetCurrencyCode());
        } else {
            exchangeRateService.addExchangeRate(exchangeRate.getBaseCurrencyCode(), exchangeRate.getTargetCurrencyCode(), exchangeRate.getRate());
        }
        return new ResponseEntity<>(GlobalConstants.ADDED_EXCHANGE_RATE, HttpStatus.OK);

    }

    @PatchMapping("/exchangeRate/{baseCode}/{targetCode}") //working test
    public ResponseEntity<String> updateExchangeRate(@PathVariable String baseCode, @PathVariable String targetCode, @RequestBody ExchangeRate rate) {
        if (!exchangeRateService.existsByBaseCurrencyAndTargetCurrency(baseCode, targetCode)) {
            throw new ExchangeRateNotFoundException(baseCode, targetCode);
        }
        exchangeRateService.updateExchangeRate(baseCode, targetCode, rate.getRate());
        return new ResponseEntity<>(GlobalConstants.UPDATED_EXCHANGE_RATE + baseCode + "/" + targetCode, HttpStatus.OK);
    }

    @GetMapping("/exchange") // working test
    public ResponseEntity<ExchangeCurrencyDTO> makeExchange(@RequestParam String from, @RequestParam String to, @RequestParam BigDecimal amount) {
        List<ExchangeRate> exchangeRates = exchangeRateService.getRateByCode(from, to);
        if (exchangeRates.isEmpty()) {
            throw new ExchangeRateNotFoundException(from, to);
        }
        BigDecimal result = exchangeService.convertAmount(from, to, amount);
        ExchangeCurrencyDTO currencyDTO = new ExchangeCurrencyDTO(exchangeRates, amount, result);
        return new ResponseEntity<>(currencyDTO, HttpStatus.OK);

    }


}
