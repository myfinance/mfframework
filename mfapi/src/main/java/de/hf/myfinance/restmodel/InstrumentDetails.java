package de.hf.myfinance.restmodel;


/**
 * Contains all the data to an Instrument, which is needed for ALL Instruments to fill the asset-Dashboard 
 */
public class InstrumentDetails {
    private String businesskey;
    private String description;
    private double value;
    private double valueChange;
    private LiquidityType liquiditytype;

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

    public double getValueChange() {
        return valueChange;
    }
    public void setValueChange(double valueChange) {
        this.valueChange = valueChange;
    }

    public LiquidityType getLiquiditytype() {
        return liquiditytype;
    }
    public void setLiquiditytype(LiquidityType liquiditytype) {
        this.liquiditytype = liquiditytype;
    }
}
