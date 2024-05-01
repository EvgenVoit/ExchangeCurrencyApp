package com.evgenvoit.CurrencyExchangeApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
public class ExchangeRateDTO {

    private String baseCurrencyCode;
    private String targetCurrencyCode;
    private BigDecimal rate;
}
