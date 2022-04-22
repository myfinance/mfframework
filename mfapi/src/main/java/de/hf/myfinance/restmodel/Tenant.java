package de.hf.myfinance.restmodel;

public class Tenant extends Instrument {

    public Tenant(){
        super();
    }

    public Tenant(String businesskey, String description, boolean isactive){
        super(businesskey, description, InstrumentType.TENANT, isactive);
    }
}
