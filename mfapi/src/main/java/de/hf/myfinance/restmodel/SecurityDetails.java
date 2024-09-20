package de.hf.myfinance.restmodel;

public class SecurityDetails {
    private String businesskey;
    private String description;
    private double value;
    private double referenceValue;
    private InstrumentType instrumentType;

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

    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }

    public double getReferenceValue() {
        return referenceValue;
    }
    public void setReferenceValue(double referenceValue) {
        this.referenceValue = referenceValue;
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }
    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }
}
