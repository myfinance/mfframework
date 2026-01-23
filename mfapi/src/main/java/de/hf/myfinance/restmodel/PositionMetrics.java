package de.hf.myfinance.restmodel;

import java.util.Map;

public class PositionMetrics {

    String businesskey;
    String description;
    InstrumentType instrumentType;
    String sector;
    String country;
    private String portfolio;

    SecurityLifecyclePhase securityLifecyclePhase;
    String metricScore;
    String moatScore;
    String riskScore;
    String growthScore;
    //green yellow or red depending on opportunityScoreValue
    String opportunityScore;

    private Map<Integer, Double> cagrPerYear;
    // unlike cagr it is not scaled per year, but only to the last cashflow or current date
    private Map<Integer, Double> yieldPerYear;
    private Double totalCagr;

    private Double amount;
    private Double value;
    

    public String getBusinesskey() {
        return this.businesskey;
    }

    public void setBusinesskey(String businesskey) {
        this.businesskey = businesskey;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public InstrumentType getInstrumentType() {
        return this.instrumentType;
    }

    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getSector() {
        return this.sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPortfolio() {
        return this.portfolio;
    }

    public void setPortfolio(String portfolio) {
        this.portfolio = portfolio;
    }

    public SecurityLifecyclePhase getSecurityLifecyclePhase() {
        return this.securityLifecyclePhase;
    }

    public void setSecurityLifecyclePhase(SecurityLifecyclePhase securityLifecyclePhase) {
        this.securityLifecyclePhase = securityLifecyclePhase;
    }

    public String getMetricScore() {
        return this.metricScore;
    }

    public void setMetricScore(String metricScore) {
        this.metricScore = metricScore;
    }

    public String getMoatScore() {
        return this.moatScore;
    }

    public void setMoatScore(String moatScore) {
        this.moatScore = moatScore;
    }

    public String getRiskScore() {
        return this.riskScore;
    }

    public void setRiskScore(String riskScore) {
        this.riskScore = riskScore;
    }

    public String getGrowthScore() {
        return this.growthScore;
    }

    public void setGrowthScore(String growthScore) {
        this.growthScore = growthScore;
    }

    public String getOpportunityScore() {
        return this.opportunityScore;
    }

    public void setOpportunityScore(String opportunityScore) {
        this.opportunityScore = opportunityScore;
    }

    public Map<Integer,Double> getCagrPerYear() {
        return this.cagrPerYear;
    }

    public void setCagrPerYear(Map<Integer,Double> cagrPerYear) {
        this.cagrPerYear = cagrPerYear;
    }

    public Map<Integer,Double> getYieldPerYear() {
        return this.yieldPerYear;
    }

    public void setYieldPerYear(Map<Integer,Double> yieldPerYear) {
        this.yieldPerYear = yieldPerYear;
    }

    public Double getTotalCagr() {
        return this.totalCagr;
    }

    public void setTotalCagr(Double totalCagr) {
        this.totalCagr = totalCagr;
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
