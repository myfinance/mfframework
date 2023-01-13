package de.hf.myfinance.restmodel;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class InstrumentDetails {
    private String businesskey;
    private InstrumentType instrumentType;
    private String description;
    private Double value;
    private LocalDate valueDiffDate;
    private Double valueChange;
    private LiquidityType liquiditytype;
    private Map<String, Double> additionalValues;
    private List<Cashflow> expensesLastMonth;
    private List<Cashflow> incomeLastMonth;
}
