package de.hf.myfinance.restmodel;


import java.time.LocalDate;

public class RecurrentTransaction {
    private String recurrentTransactionId;
    private String serviceAddress;

    private String firstInstrumentBusinessKey;
    private String secondInstrumentBusinessKey;
    private RecurrentFrequency recurrentFrequency;
    private double value;
    private LocalDate nextTransactionDate;
    private RecurrentTransactionType recurrentTransactionType;


    public String getRecurrentTransactionId() {
        return recurrentTransactionId;
    }
    public void setRecurrentTransactionId(String recurrentTransactionId) {
        this.recurrentTransactionId = recurrentTransactionId;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }
    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public String getFirstInstrumentBusinessKey() {
        return firstInstrumentBusinessKey;
    }
    public void setFirstInstrumentBusinessKey(String firstInstrumentBusinessKey) {
        this.firstInstrumentBusinessKey = firstInstrumentBusinessKey;
    }

    public String getSecondInstrumentBusinessKey() {
        return secondInstrumentBusinessKey;
    }
    public void setSecondInstrumentBusinessKey(String secondInstrumentBusinessKey) {
        this.secondInstrumentBusinessKey = secondInstrumentBusinessKey;
    }

    public RecurrentFrequency getRecurrentFrequency() {
        return recurrentFrequency;
    }
    public void setRecurrentFrequency(RecurrentFrequency recurrentFrequency) {
        this.recurrentFrequency = recurrentFrequency;
    }

    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }

    public LocalDate getNextTransactionDate() {
        return nextTransactionDate;
    }
    public void setNextTransactionDate(LocalDate nextTransactionDate) {
        this.nextTransactionDate = nextTransactionDate;
    }

    public RecurrentTransactionType getRecurrentTransactionType() {
        return recurrentTransactionType;
    }
    public void setRecurrentTransactionType(RecurrentTransactionType recurrentTransactionType) {
        this.recurrentTransactionType = recurrentTransactionType;
    }

}
