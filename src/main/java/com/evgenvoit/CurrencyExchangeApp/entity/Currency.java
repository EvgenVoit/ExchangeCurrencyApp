package com.evgenvoit.CurrencyExchangeApp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "currency_code")
    private String currencyCode;

    @Column(name = "full_name")
    private String fullCurrencyName;

    @Column(name = "sign")
    private String currencySign;


}
