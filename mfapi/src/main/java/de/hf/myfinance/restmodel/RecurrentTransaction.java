package de.hf.myfinance.restmodel;


import java.time.LocalDate;

public class RecurrentTransaction {
    private String recurrentTransactionId;
    private String serviceAddress;

    private String budgetKey;
    private String trgBudgetKey;
    private String accKey;
    private String trgAccKey;
    private String insuranceKey; 
    private RecurrentFrequency recurrentFrequency;
    private double value;
    private LocalDate nextTransactionDate;
    private TransactionType transactionType;
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

    public String getInsuranceKey() {
        return this.insuranceKey;
    }

    public void setInsuranceKey(String insuranceKey) {
        this.insuranceKey = insuranceKey;
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

    public TransactionType getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}
