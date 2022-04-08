package de.hf.myfinance.restmodel;

import java.time.LocalDate;

public class ValuePerDate {

    protected final double value;
    protected final LocalDate date;

    public ValuePerDate(String valuePerDateString) {
        String[] values = valuePerDateString.split(",");
        this.value = Double.parseDouble(values[0]);
        this.date = LocalDate.parse(values[1]);
    }

    public double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

}