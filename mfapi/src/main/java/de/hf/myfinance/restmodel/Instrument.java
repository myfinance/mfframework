package de.hf.myfinance.restmodel;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class Instrument {
    private InstrumentType instrumentType;
    private String description;
    private boolean isactive;
    private LocalDate maturitydate;
    private LocalDate closingdate;
    private LocalDateTime treelastchanged;
    private String businesskey;
    private String parentBusinesskey;
    private String serviceAddress;

    public Instrument() {
    }

    public Instrument(String description,
                      InstrumentType instrumentType){
        this("", description, instrumentType, true);
    }

    public Instrument(String businesskey,
                      String description,
                      InstrumentType instrumentType,
                      boolean isactive){
        this.businesskey = businesskey;
        this.description = description;
        this.instrumentType = instrumentType;
        this.isactive = isactive;
        this.treelastchanged = LocalDateTime.now();
    }

    public InstrumentType getInstrumentType() {
        return instrumentType;
    }

    public void setInstrumentType(InstrumentType instrumentType) {
        this.instrumentType = instrumentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public LocalDate getMaturitydate() {
        return maturitydate;
    }

    public void setMaturitydate(LocalDate maturitydate) {
        this.maturitydate = maturitydate;
    }

    public LocalDate getClosingdate() {
        return closingdate;
    }

    public void setClosingdate(LocalDate closingdate) {
        this.closingdate = closingdate;
    }

    public LocalDateTime getTreelastchanged() {
        return treelastchanged;
    }

    public void setTreelastchanged(LocalDateTime treelastchanged) {
        this.treelastchanged = treelastchanged;
    }

    public String getBusinesskey() {
        return businesskey;
    }

    public void setBusinesskey(String businesskey) {
        this.businesskey = businesskey;
    }

    public String getParentBusinesskey() {
        return parentBusinesskey;
    }

    public void setParentBusinesskey(String parentBusinesskey) {
        this.parentBusinesskey = parentBusinesskey;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }
}