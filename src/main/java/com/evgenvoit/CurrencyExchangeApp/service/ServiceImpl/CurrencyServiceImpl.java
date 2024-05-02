package com.evgenvoit.CurrencyExchangeApp.service.ServiceImpl;

import com.evgenvoit.CurrencyExchangeApp.dao.CurrencyRepository;
import com.evgenvoit.CurrencyExchangeApp.entity.Currency;
import com.evgenvoit.CurrencyExchangeApp.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }
    @Override
    public Currency getCurrencyByCode(String code) {
        return currencyRepository.getCurrencyByCode(code);
    }
    @Override
    public void addCurrency(Currency currency) {
        currencyRepository.save(currency);
    }

    @Override
    public void deleteCurrency(String code) {
        currencyRepository.deleteCurrencyByCode(code);
    }
    @Override
    public void deleteCurrencyById(Integer id) {
        currencyRepository.deleteCurrencyById(id);
    }

    @Override
    public boolean existCurrencyByCode(String code) {
        return currencyRepository.existsByCode(code);
    }


}
