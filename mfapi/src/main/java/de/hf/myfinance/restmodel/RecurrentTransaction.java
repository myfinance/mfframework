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
    private TransactionType recurrentTransactionType;
    private String description;


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

    public TransactionType getRecurrentTransactionType() {
        return recurrentTransactionType;
    }
    public void setRecurrentTransactionType(TransactionType recurrentTransactionType) {
        this.recurrentTransactionType = recurrentTransactionType;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
