package de.hf.myfinance.restmodel;

import java.util.List;
import java.util.Map;

public class PortfolioMetrics {

    private String portfolio;
    private Map<Integer, Double> cagrPerYear;
    private Double totalCagr;
    private Boolean isSingleSecurity;
    //just to understand from which cashflows the metrics were calculated
    private List<Cashflow> cashflows;

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

    public Boolean getIsSingleSecurity() {
        return this.isSingleSecurity;
    }

    public void setIsSingleSecurity(Boolean isSingleSecurity) {
        this.isSingleSecurity = isSingleSecurity;
    }

    public List<Cashflow> getCashflows() {
        return this.cashflows;
    }

    public void setCashflows(List<Cashflow> cashflows) {
        this.cashflows = cashflows;
    }
     
}
