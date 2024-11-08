package de.hf.myfinance.restmodel;

import java.time.LocalDate;
import java.util.Map;

public class Position {

    private String depotBusinessKey;
    private String securityBusinessKey;
    private Map<LocalDate, Double> positionCurve;

    public Position(String depotBusinessKey, String securityBusinessKey, Map<LocalDate, Double> positionCurve) {
        this.depotBusinessKey = depotBusinessKey;
        this.securityBusinessKey = securityBusinessKey;
        this.positionCurve = positionCurve;
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

     public Map<LocalDate, Double> getPositionCurve() {
        return this.positionCurve;
     }
  
     public void setPositionCurve(Map<LocalDate, Double> positionCurve) {
        this.positionCurve = positionCurve;
     }

     public Double getPosition(LocalDate date) {
        return positionCurve.get(date);
     }
}