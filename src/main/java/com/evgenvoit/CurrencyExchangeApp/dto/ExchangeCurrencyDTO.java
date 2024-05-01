package com.evgenvoit.CurrencyExchangeApp.dto;

import com.evgenvoit.CurrencyExchangeApp.entity.Currency;
import com.evgenvoit.CurrencyExchangeApp.entity.ExchangeRate;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class ExchangeCurrencyDTO {

//    private Currency baseCurrencyCode;
//    private Currency targetCurrencyCode;
    private List<ExchangeRate> exchangeInformation;
//    private BigDecimal rate;
    private BigDecimal amount;
    private BigDecimal convertedAmount;
}
