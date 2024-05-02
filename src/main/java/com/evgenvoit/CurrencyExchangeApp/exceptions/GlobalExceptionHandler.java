package com.evgenvoit.CurrencyExchangeApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CurrencyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleCurrencyNotFoundException(CurrencyNotFoundException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Валюта не найдена: " + ex.getCode());
    }

    @ExceptionHandler(CurrencyAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    @ResponseBody
    public ErrorResponse handleCurrencyAlreadyExistsException(CurrencyAlreadyExistsException ex) {
        return new ErrorResponse(HttpStatus.ALREADY_REPORTED.value(), "Валюта уже существует: " + ex.getCode());
    }

    @ExceptionHandler(ExchangeRateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleExchangeRateNotFoundException(ExchangeRateNotFoundException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Exchange Rate не найден: " + ex.getBaseCurrencyCode() + "/" + ex.getTargetCurrencyCode());
    }

    @ExceptionHandler(ExchangeRateAlreadyExists.class)
    @ResponseStatus(HttpStatus.ALREADY_REPORTED)
    @ResponseBody
    public ErrorResponse handleExchangeRateAlreadyExistsException(ExchangeRateAlreadyExists ex) {
        return new ErrorResponse(HttpStatus.ALREADY_REPORTED.value(), "Exchange Rate уже существует: " + ex.getBaseCurrencyCode() + "/" + ex.getTargetCurrencyCode());
    }
}
