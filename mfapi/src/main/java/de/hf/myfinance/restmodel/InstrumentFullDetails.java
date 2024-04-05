package de.hf.myfinance.restmodel;

import java.util.List;
import java.util.Map;

/**
 * Contains the additional data to an Instrument, which is needed for detailsview of the asset-Dashboard 
 */
public class InstrumentFullDetails {
    
    private String businesskey;
    private InstrumentType instrumentType;
    private Map<String, Double> additionalValues;
    private List<Cashflow> expensesLastMonth;
    private List<Cashflow> incomeLastMonth;
    private ValueCurve valueCurve;

    public String getBusinesskey() {
        return businesskey;
    }
    public void setBusinesskey(String businesskey) {
        this.businesskey = businesskey;
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

    public List<Cashflow> getExpensesLastMonth() {
        return expensesLastMonth;
    }
    public void setExpensesLastMonth(List<Cashflow> expensesLastMonth) {
        this.expensesLastMonth = expensesLastMonth;
    }

    public List<Cashflow> getIncomeLastMonth() {
        return incomeLastMonth;
    }
    public void setIncomeLastMonth(List<Cashflow> incomeLastMonth) {
        this.incomeLastMonth = incomeLastMonth;
    }

    public ValueCurve getValueCurve() {
        return valueCurve;
    }
    public void setValueCurve(ValueCurve valueCurve) {
        this.valueCurve = valueCurve;
    }
}
 