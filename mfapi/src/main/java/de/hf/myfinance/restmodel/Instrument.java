package de.hf.myfinance.restmodel;


public class Instrument {
    private Integer instrumentid;
    private String description;
    private String serviceAddress;

    public Instrument() {
    }

    public Instrument(Integer instrumentid, String description, String serviceAddress){
        this.instrumentid = instrumentid;
        this.description = description;
        this.serviceAddress =serviceAddress;
    }


    public Integer getInstrumentid() {
        return instrumentid;
    }

    public String getDescription() {
        return description;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

}