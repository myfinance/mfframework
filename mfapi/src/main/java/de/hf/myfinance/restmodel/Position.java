package de.hf.myfinance.restmodel;

public class Position {

    private String depotBusinessKey;
    private String securityBusinessKey;
    private double amount;

    public Position(String depotBusinessKey, String securityBusinessKey, double amount) {
        this.depotBusinessKey = depotBusinessKey;
        this.securityBusinessKey = securityBusinessKey;
        this.amount = amount;
    }

    public String getDepotBusinessKey() {
        return depotBusinessKey;
    }

    public void setDepotBusinessKey(String depotBusinessKey) {
        this.depotBusinessKey = depotBusinessKey;
    }

    public String getSecurityBusinessKey() {
        return securityBusinessKey;
    }

    public void setSecurityBusinessKey(String securityBusinessKey) {
        this.securityBusinessKey = securityBusinessKey;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}