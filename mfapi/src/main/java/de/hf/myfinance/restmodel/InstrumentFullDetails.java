package de.hf.myfinance.restmodel;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Contains the additional data to an Instrument, which is needed for detailsview of the asset-Dashboard 
 */
public class InstrumentFullDetails {
    
    private String businesskey;
    private String description;
    private InstrumentType instrumentType;
    private Map<String, Double> additionalValues;
    private List<Transaction> expensesLastMonth;
    private List<Transaction> incomeLastMonth;
    private Map<LocalDate, Double> valueCurve;

    public String getBusinesskey() {
        return businesskey;
    }
    public void setBusinesskey(String businesskey) {
        this.businesskey = businesskey;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }
    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }

    public Map<String, Double> getAdditionalValues() {
        return additionalValues;
    }
    public void setAdditionalValues(Map<String, Double> additionalValues) {
        this.additionalValues = additionalValues;
    }

    public List<Transaction> getExpensesLastMonth() {
        return expensesLastMonth;
    }
    public void setExpensesLastMonth(List<Transaction> expensesLastMonth) {
        this.expensesLastMonth = expensesLastMonth;
    }

    public List<Transaction> getIncomeLastMonth() {
        return incomeLastMonth;
    }
    public void setIncomeLastMonth(List<Transaction> incomeLastMonth) {
        this.incomeLastMonth = incomeLastMonth;
    }

    public Map<LocalDate, Double> getValueCurve() {
        return valueCurve;
    }
    public void setValueCurve(Map<LocalDate, Double> valueCurve) {
        this.valueCurve = valueCurve;
    }
}
 