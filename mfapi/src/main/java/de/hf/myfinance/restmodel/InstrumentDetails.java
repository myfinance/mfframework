package de.hf.myfinance.restmodel;


/**
 * Contains all the data to an Instrument, which is needed for ALL Instruments to fill the asset-Dashboard 
 */
public class InstrumentDetails {
    private String businesskey;
    private String description;
    private boolean active;
    private InstrumentType instrumentType;
    private double value;
    private double referenceValue;
    private LiquidityType liquiditytype;
    private String instrumentParent;

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


    public LiquidityType getLiquiditytype() {
        return liquiditytype;
    }
    public void setLiquiditytype(LiquidityType liquiditytype) {
        this.liquiditytype = liquiditytype;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public InstrumentType getInstrumentType() {
        return this.instrumentType;
    }

    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }

    public boolean isActive() {
        return this.active;
    }

    public String getInstrumentParent() {
        return this.instrumentParent;
    }

    public void setInstrumentParent(String instrumentParent) {
        this.instrumentParent = instrumentParent;
    }

}
