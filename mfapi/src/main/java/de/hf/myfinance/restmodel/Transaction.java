package de.hf.myfinance.restmodel;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Transaction {

    private String transactionId;
    private String description;
    private LocalDate transactiondate;
    private LocalDateTime lastchanged;
    private String serviceAddress;
    private TransactionType transactionType;

    private double value;
    private String budgetKey;
    private String trgBudgetKey;
    private String accKey;
    private String trgAccKey;

    private String depotBusinessKey;
    private String securityBusinessKey;
    private Double amount;

    private String insuranceKey;    

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


    public double getValue() {
        return this.value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getBudgetKey() {
        return this.budgetKey;
    }

    public void setBudgetKey(String budgetKey) {
        this.budgetKey = budgetKey;
    }

    public String getTrgBudgetKey() {
        return this.trgBudgetKey;
    }

    public void setTrgBudgetKey(String trgBudgetKey) {
        this.trgBudgetKey = trgBudgetKey;
    }

    public String getAccKey() {
        return this.accKey;
    }

    public void setAccKey(String accKey) {
        this.accKey = accKey;
    }

    public String getTrgAccKey() {
        return this.trgAccKey;
    }

    public void setTrgAccKey(String trgAccKey) {
        this.trgAccKey = trgAccKey;
    }

    public String getDepotBusinessKey() {
        return this.depotBusinessKey;
    }

    public void setDepotBusinessKey(String depotBusinessKey) {
        this.depotBusinessKey = depotBusinessKey;
    }

    public String getSecurityBusinessKey() {
        return this.securityBusinessKey;
    }

    public void setSecurityBusinessKey(String securityBusinessKey) {
        this.securityBusinessKey = securityBusinessKey;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getInsuranceKey() {
        return this.insuranceKey;
    }

    public void setInsuranceKey(String insuranceKey) {
        this.insuranceKey = insuranceKey;
    }

}
