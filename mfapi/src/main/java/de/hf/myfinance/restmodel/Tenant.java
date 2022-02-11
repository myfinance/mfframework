package de.hf.myfinance.restmodel;

import java.time.LocalDateTime;

public class Tenant extends Instrument {

    public Tenant(){
        super();
    }

    public Tenant(String businesskey, String description, boolean isactive, LocalDateTime treelastchanged, String serviceAddress){
        super(businesskey, description, InstrumentType.TENANT, isactive, treelastchanged, serviceAddress);
    }
}
