package de.hf.myfinance.restmodel;

import java.time.LocalDate;

public class Cashflow {
    private String description;
    private LocalDate transactiondate;
    private String instrumentBusinesskey;
    private double value;
    private String serviceAddress;

    public Cashflow() {
    }

    public Cashflow(String description, LocalDate transactiondate, String instrumentBusinesskey, double value) {
        this.description = description;
        this.transactiondate = transactiondate;
        this.instrumentBusinesskey = instrumentBusinesskey;
        this.value = value;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTransactiondate() {
        return transactiondate;
    }
    public void setTransactiondate(LocalDate transactiondate) {
        this.transactiondate = transactiondate;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }
    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public String getInstrumentBusinesskey() {
        return instrumentBusinesskey;
    }
    public void setInstrumentBusinesskey(String instrumentBusinesskey) {
        this.instrumentBusinesskey = instrumentBusinesskey;
    }

    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }
}

