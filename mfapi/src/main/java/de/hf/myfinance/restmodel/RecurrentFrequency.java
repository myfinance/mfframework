package de.hf.myfinance.restmodel;

public enum RecurrentFrequency {
    MONTHLY(1),
    QUATERLY(2),
    YEARLY(3),
    UNKNOWN(99);

    public static final String MONTHLY_IDSTRING = "1";
    public static final String QUATERLY_IDSTRING = "2";
    public static final String YEARLY_IDSTRING = "3";

    private final Integer value;

    RecurrentFrequency(final Integer newValue) {
        value = newValue;
    }

    public Integer getValue() { return value; }

    public static RecurrentFrequency getRecurrentFrequencyById(int recurrentfrequencyId){
        return switch (recurrentfrequencyId) {
            case 1 -> RecurrentFrequency.MONTHLY;
            case 2 -> RecurrentFrequency.QUATERLY;
            case 3 -> RecurrentFrequency.YEARLY;
            default -> RecurrentFrequency.UNKNOWN;
        };
    }
}
