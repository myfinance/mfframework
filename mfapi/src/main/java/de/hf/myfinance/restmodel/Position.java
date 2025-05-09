package de.hf.myfinance.restmodel;

public class Position {
    private String depotId;
    private String depotDescription;
    private String securityId;
    private String securityDescription;
    private InstrumentType securityType;
    private Double amount;
    private Double value;


    public Position(String depotId, String depotDescription, String securityId, String securityDescription, InstrumentType securityType) {
        this.depotId = depotId;
        this.depotDescription = depotDescription;
        this.securityId = securityId;
        this.securityDescription = securityDescription;
        this.securityType = securityType;
    }


    public String getDepotId() {
        return this.depotId;
    }

    public void setDepotId(String depotId) {
        this.depotId = depotId;
    }

    public String getDepotDescription() {
        return this.depotDescription;
    }

    public void setDepotDescription(String depotDescription) {
        this.depotDescription = depotDescription;
    }

    public String getSecurityId() {
        return this.securityId;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }

    public String getSecurityDescription() {
        return this.securityDescription;
    }

    public void setSecurityDescription(String securityDescription) {
        this.securityDescription = securityDescription;
    }

    public InstrumentType getSecurityType() {
        return this.securityType;
    }

    public void setSecurityType(InstrumentType securityType) {
        this.securityType = securityType;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getValue() {
        return this.value;
    }

    public void setValue(Double value) {
        this.value = value;
    }


}
