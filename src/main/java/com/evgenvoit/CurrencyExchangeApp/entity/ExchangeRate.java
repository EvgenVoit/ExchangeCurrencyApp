package com.evgenvoit.CurrencyExchangeApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "exchange_rates")
public class ExchangeRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "rate")
    private BigDecimal rate;

    @Column(name = "base_currency_Id")
    private int baseCurrencyId;

    @Column(name = "target_currency_Id")
    private int targetCurrencyId;

}
