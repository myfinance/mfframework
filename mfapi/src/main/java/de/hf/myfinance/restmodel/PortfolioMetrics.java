package de.hf.myfinance.restmodel;

import java.util.Map;

public class PortfolioMetrics {
  private String portfolio;

    private Integer version;

    private Map<Integer, Double> cagrPerYear;
    private Double totalCagr;

    private Double positionCurve;

    public PortfolioMetrics(){}

    public PortfolioMetrics(String portfolio) {
        this.portfolio = portfolio;
    }
    public PortfolioMetrics(String portfolio, String securityBusinessKey, Double totalCagr, Map<Integer, Double> cagrPerYear) {
        this.portfolio = portfolio;
        this.cagrPerYear = cagrPerYear;
        this.totalCagr = totalCagr;
     }
  

    public String getPortfolio() {
        return this.portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Map<Integer,Double> getCagrPerYear() {
        return this.cagrPerYear;
    }

    public void setCagrPerYear(Map<Integer,Double> cagrPerYear) {
        this.cagrPerYear = cagrPerYear;
    }

    public Double getTotalCagr() {
        return this.totalCagr;
    }

    public void setTotalCagr(Double totalCagr) {
        this.totalCagr = totalCagr;
    }

    public Double getPositionCurve() {
        return this.positionCurve;
    }

    public void setPositionCurve(Double positionCurve) {
        this.positionCurve = positionCurve;
    }
     
}
