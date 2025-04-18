package de.hf.myfinance.restmodel;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class EndOfDayPrices {
    private String instrumentBusinesskey;
    private LocalDateTime lastUpdateTs;
    private Map<LocalDate, EndOfDayPrice> prices;

    public EndOfDayPrices() {
        prices = new HashMap<>();
    }

    public EndOfDayPrices(String instrumentBusinesskey) {
        this.instrumentBusinesskey = instrumentBusinesskey;
        prices = new HashMap<>();
    }

    public String getInstrumentBusinesskey() {
        return instrumentBusinesskey;
    }
    public void setInstrumentBusinesskey(String instrumentBusinesskey) {
        this.instrumentBusinesskey = instrumentBusinesskey;
    }

    public Map<LocalDate, EndOfDayPrice> getPrices() {
        return prices;
    }
    public void setPrices(Map<LocalDate, EndOfDayPrice> prices) {
        this.prices = prices;
    }


    public LocalDateTime getLastUpdateTs() {
        return this.lastUpdateTs;
    }

    public void setLastUpdateTs(LocalDateTime lastUpdateTs) {
        this.lastUpdateTs = lastUpdateTs;
    }

}
