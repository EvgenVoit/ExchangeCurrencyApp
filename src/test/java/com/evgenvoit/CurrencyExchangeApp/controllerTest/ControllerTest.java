package com.evgenvoit.CurrencyExchangeApp.controllerTest;

import com.evgenvoit.CurrencyExchangeApp.controller.Controller;
import com.evgenvoit.CurrencyExchangeApp.entity.Currency;
import com.evgenvoit.CurrencyExchangeApp.entity.ExchangeRate;
import com.evgenvoit.CurrencyExchangeApp.service.CurrencyService;
import com.evgenvoit.CurrencyExchangeApp.service.ExchangeRateService;
import com.evgenvoit.CurrencyExchangeApp.service.ExchangeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(Controller.class)
public class ControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CurrencyService currencyService;

    @MockBean
    private ExchangeRateService exchangeRateService;

    @MockBean
    private ExchangeService exchangeService;

    @Test
    public void testGetCurrencyByCode() throws Exception {
        Currency mockCurrency = new Currency();
        mockCurrency.setCode("USD");

        when(currencyService.getCurrencyByCode(anyString())).thenReturn(mockCurrency);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/v1/currency/{code}", "USD")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("USD"));
    }

    @Test
    public void deleteCurrencyByCode() throws Exception {
        String code = "USD";
        Currency currency = new Currency();
        currency.setCode(code);

        when(currencyService.getCurrencyByCode(code)).thenReturn(currency);

        mockMvc.perform(delete("/api/v1/currency/{code}", code))
                .andExpect(status().isOk())
                .andExpect(content().string("Currency was deleted - " + code));

    }

    @Test
    public void getAllCurrencies() throws Exception {
        List<Currency> currencies = new ArrayList<>();
        currencies.add(new Currency(1, "USD", "US Dollar", "US"));
        currencies.add(new Currency(2, "EUR", "Euro", "EU"));

        when(currencyService.getAllCurrencies()).thenReturn(currencies);

        mockMvc.perform(get("/api/v1/currencies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].code").value("USD"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("US Dollar"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].code").value("EUR"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Euro"));

        verify(currencyService, times(1)).getAllCurrencies();
    }

    @Test
    public void getAllRates() throws Exception {
        List<ExchangeRate> rates = Arrays.asList(
                new ExchangeRate(1, new Currency(1, "USD", "US Dollar", "US"), new Currency(2, "EUR", "Euro", "EU"), new BigDecimal("0.93")),
                new ExchangeRate(2, new Currency(2, "EUR", "Euro", "EU"), new Currency(1, "USD", "US Dollar", "US"), new BigDecimal("1.07"))
        );

        when(exchangeRateService.getAllRates()).thenReturn(rates);

        mockMvc.perform(get("/api/v1/exchangeRates"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].baseCurrency.code").value("USD"))
                .andExpect(jsonPath("$[0].targetCurrency.code").value("EUR"))
                .andExpect(jsonPath("$[0].rate").value("0.93"))
                .andExpect(jsonPath("$[1].baseCurrency.code").value("EUR"))
                .andExpect(jsonPath("$[1].targetCurrency.code").value("USD"))
                .andExpect(jsonPath("$[1].rate").value("1.07"));

        verify(exchangeRateService, times(1)).getAllRates();
    }

    @Test
    public void testGetRateByCode() throws Exception {
        List<ExchangeRate> rates = Arrays.asList(
                new ExchangeRate(1, new Currency(1, "USD", "US Dollar", "US"), new Currency(2, "EUR", "Euro", "EU"), new BigDecimal("0.93")),
                new ExchangeRate(2, new Currency(2, "EUR", "Euro", "EU"), new Currency(1, "USD", "US Dollar", "US"), new BigDecimal("1.07"))
        );

        when(exchangeRateService.getRateByCode("USD", "EUR")).thenReturn(rates);

        mockMvc.perform(get("/api/v1/exchangeRate/USD/EUR"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].baseCurrency.code").value("USD"))
                .andExpect(jsonPath("$[0].targetCurrency.code").value("EUR"))
                .andExpect(jsonPath("$[0].rate").value("0.93"))
                .andExpect(jsonPath("$[1].baseCurrency.code").value("EUR"))
                .andExpect(jsonPath("$[1].targetCurrency.code").value("USD"))
                .andExpect(jsonPath("$[1].rate").value("1.07"));


        verify(exchangeRateService, times(2)).getRateByCode("USD", "EUR");
    }

}
