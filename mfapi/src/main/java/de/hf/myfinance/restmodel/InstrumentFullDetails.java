package de.hf.myfinance.restmodel;

import java.time.LocalDate;
import java.util.HashMap;
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
    private Map<String, Double> linkedValues;
    private List<Cashflow> expensesInPeriod;
    private List<Cashflow> incomeInPeriod;
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
    public void addAdditionalValue(String key, Double value) {
        if(this.additionalValues == null) {
            this.additionalValues = new HashMap<String, Double>();
        }
        this.additionalValues.put(key, value);
    }

    public List<Cashflow> getExpensesInPeriod() {
        return expensesInPeriod;
    }
    public void setExpensesInPeriod(List<Cashflow> expensesInPeriod) {
        this.expensesInPeriod = expensesInPeriod;
    }

    public List<Cashflow> getIncomeInPeriod() {
        return incomeInPeriod;
    }
    public void setIncomeInPeriod(List<Cashflow> incomeInPeriod) {
        this.incomeInPeriod = incomeInPeriod;
    }

    public Map<LocalDate, Double> getValueCurve() {
        return valueCurve;
    }
    public void setValueCurve(Map<LocalDate, Double> valueCurve) {
        this.valueCurve = valueCurve;
    }

    public Map<String, Double> getLinkedValues() {
        return linkedValues;
    }

    public void setLinkedValues(Map<String, Double> linkedValues) {
        this.linkedValues = linkedValues;
    }
    public void addLinkedValue(String key, Double value) {
        if(this.linkedValues == null) {
            this.linkedValues = new HashMap<String, Double>();
        }
        this.linkedValues.put(key, value);
    }
}
 