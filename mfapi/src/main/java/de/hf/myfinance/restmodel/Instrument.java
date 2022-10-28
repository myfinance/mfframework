package de.hf.myfinance.restmodel;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Instrument {
    private InstrumentType instrumentType;
    private String description;
    private boolean isactive;
    private LocalDateTime treelastchanged;
    private String businesskey;
    private String parentBusinesskey;
    private String serviceAddress;
    private String tenantBusinesskey;

    private Map<AdditionalMaps, Map<String, String>> additionalMaps = new HashMap<>();
    private Map<AdditionalProperties, String> additionalProperties = new HashMap<>();
    private Map<AdditionalLists, List<String>> additionalLists = new HashMap<>();

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

    public Instrument(String businesskey,
                      String description,
                      InstrumentType instrumentType,
                      boolean isactive,
                      LocalDateTime treelastchanged){
        this.businesskey = businesskey;
        this.description = description;
        this.instrumentType = instrumentType;
        this.isactive = isactive;
        this.treelastchanged = treelastchanged;
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

    public Map<AdditionalMaps, Map<String, String>> getAdditionalMaps() {
        return additionalMaps;
    }
    public void setAdditionalMaps(Map<AdditionalMaps, Map<String, String>> additionalMaps) {
        this.additionalMaps = additionalMaps;
    }

    public Map<AdditionalProperties, String> getAdditionalProperties() {
        return additionalProperties;
    }
    public void setAdditionalProperties(Map<AdditionalProperties, String> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    public Map<AdditionalLists, List<String>> getAdditionalLists() {
        return additionalLists;
    }
    public void setAdditionalLists(Map<AdditionalLists, List<String>> additionalLists) {
        this.additionalLists = additionalLists;
    }

    public String getTenantBusinesskey() {
        return tenantBusinesskey;
    }
    public void setTenantBusinesskey(String tenantBusinesskey) {
        this.tenantBusinesskey = tenantBusinesskey;
    }

}