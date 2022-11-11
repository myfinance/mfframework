package de.hf.myfinance.restmodel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Transaction {

    private String transactionId;
    private String description;
    private LocalDate transactiondate;
    private LocalDateTime lastchanged;
    private Set<Trade> trades = new HashSet<>(0);
    // map of instrumentBusinesskey and value
    private Map<String, Double> cashflows = new HashMap<>(0);
    private TransactionType transactionType;

    private String serviceAddress;

    public Transaction() {
    }

    public Transaction(String transactionId) {
        this.transactionId = transactionId;
    }

    public Transaction(String description, LocalDate transactiondate, TransactionType transactionType) {
        this.description = description;
        this.transactiondate = transactiondate;
        this.transactionType = transactionType;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public LocalDateTime getLastchanged() {
        return lastchanged;
    }

    public void setLastchanged(LocalDateTime lastchanged) {
        this.lastchanged = lastchanged;
    }

    public Set<Trade> getTrades() {
        return trades;
    }

    public void setTrades(Set<Trade> trades) {
        this.trades = trades;
    }

    public Map<String, Double> getCashflows() {
        return cashflows;
    }

    public void setCashflows(Map<String, Double> cashflows) {
        this.cashflows = cashflows;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }
}
