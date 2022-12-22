package de.hf.myfinance.restmodel;

import java.time.LocalDate;
import java.util.Map;

public class ValueCurve {
    private Map<LocalDate, Double> valueCurve;
    private String serviceAddress;
    private String instrumentBusinesskey;
    private String parentBusinesskey;

    public ValueCurve(){

    }

    public ValueCurve(String instrumentBusinesskey){
        this.instrumentBusinesskey = instrumentBusinesskey;
    }

    public Map<LocalDate, Double> getValueCurve() {
        return valueCurve;
    }
    public void setValueCurve(Map<LocalDate, Double> valueCurve) {
        this.valueCurve = valueCurve;
    }

    public String getInstrumentBusinesskey() {
        return instrumentBusinesskey;
    }
    public void setInstrumentBusinesskey(String instrumentBusinesskey) {
        this.instrumentBusinesskey = instrumentBusinesskey;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }
    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public String getParentBusinesskey() {
        return parentBusinesskey;
    }
    public void setParentBusinesskey(String parentBusinesskey) {
        this.parentBusinesskey = parentBusinesskey;
    }
}
