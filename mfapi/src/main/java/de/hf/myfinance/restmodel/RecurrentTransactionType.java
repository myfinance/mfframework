package de.hf.myfinance.restmodel;

public enum RecurrentTransactionType {
    INCOME(1),
    EXPENSE(2),
    TRANSFER(3),
    BUDGETTRANSFER(4),
    UNKNOWN(99);

    public static final String INCOME_IDSTRING = "1";
    public static final String EXPENSES_IDSTRING = "2";
    public static final String TRANSFER_IDSTRING = "3";
    public static final String BUDGETTRANSFER_IDSTRING = "4";

    private final Integer value;

    RecurrentTransactionType(final Integer newValue) {
        value = newValue;
    }

    public Integer getValue() { return value; }

    public static RecurrentTransactionType getRecurrentTransactionTypeById(int recurrentTransactionTypeId){
        return switch (recurrentTransactionTypeId) {
            case 1 -> RecurrentTransactionType.INCOME;
            case 2 -> RecurrentTransactionType.EXPENSE;
            case 3 -> RecurrentTransactionType.TRANSFER;
            case 4 -> RecurrentTransactionType.BUDGETTRANSFER;
            default -> RecurrentTransactionType.UNKNOWN;
        };
    }
}
