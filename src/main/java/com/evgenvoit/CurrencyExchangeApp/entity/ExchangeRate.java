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

    @ManyToOne
    @JoinColumn(name = "base_currency_Id", referencedColumnName = "ID")
    private Currency baseCurrency;

    @ManyToOne
    @JoinColumn(name = "target_currency_Id", referencedColumnName = "ID")
    private Currency targetCurrency;

    @Column(name = "rate")
    private BigDecimal rate;

}
