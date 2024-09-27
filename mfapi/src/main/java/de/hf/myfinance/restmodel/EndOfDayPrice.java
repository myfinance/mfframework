package de.hf.myfinance.restmodel;

public class EndOfDayPrice {
    private double value;
    private String currencyKey;
    private String instrumentBusinesskey;

    public EndOfDayPrice(double value, String currencyKey) {
        this.value = value;
        this.currencyKey = currencyKey;
    }

    public double getValue() {
        return value;
    }
    public void setValue(double value) {
        this.value = value;
    }

    public String getCurrencyKey() {
        return currencyKey;
    }
    public void setCurrencyKey(String currencyKey) {
        this.currencyKey = currencyKey;
    }

    public String getInstrumentBusinesskey() {
        return instrumentBusinesskey;
    }
    public void setInstrumentBusinesskey(String instrumentBusinesskey) {
        this.instrumentBusinesskey = instrumentBusinesskey;
    }
}
