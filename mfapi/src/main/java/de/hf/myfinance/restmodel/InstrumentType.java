package de.hf.myfinance.restmodel;

public enum InstrumentType {
    GIRO(1),
    MONEYATCALL(2),
    TIMEDEPOSIT(3),
    BUILDINGSAVINGACCOUNT(4),
    BUDGET(5),
    TENANT(6),
    ACCOUNTPORTFOLIO(8),
    ARTIFICALPORTFOLIO(9),
    BUDGETGROUP(10),
    DEPOT(11),
    BUILDINGSAVING(12),
    CURRENCY(13),
    EQUITY(14),
    FONDS(15),
    ETF(16),
    INDEX(17),
    BOND(18),
    LIFEINSURANCE(19),
    DEPRECATIONOBJECT(20),
    REALESTATE(21),
    LOAN(22),
    BUDGETPORTFOLIO(23),
    UNKNOWN(99);

    public static final String GIRO_IDSTRING = "1";
    public static final String MONEYATCALL_IDSTRING = "2";
    public static final String TIMEDEPOSIT_IDSTRING = "3";
    public static final String BUILDINGSAVINGACCOUNT_IDSTRING = "4";
    public static final String BUDGET_IDSTRING = "5";
    public static final String TENANT_IDSTRING = "6";
    public static final String ACCOUNTPORTFOLIO_IDSTRING = "8";
    public static final String ARTIFICIALPORTFOLIO_IDSTRING = "9";
    public static final String BUDGETGROUP_IDSTRING = "10";
    public static final String DEPOT_IDSTRING = "11";
    public static final String BUILDINGSAVING_IDSTRING = "12";
    public static final String CURRENCY_IDSTRING = "13";
    public static final String EQUITY_IDSTRING = "14";
    public static final String FONDS_IDSTRING = "15";
    public static final String ETF_IDSTRING = "16";
    public static final String INDEX_IDSTRING = "17";
    public static final String BOND_IDSTRING = "18";
    public static final String LIFEINSURANCE_IDSTRING = "19";
    public static final String DEPRECATIONOBJECT_IDSTRING = "20";
    public static final String REALESTATE_IDSTRING = "21";
    public static final String LOAN_IDSTRING = "22";
    public static final String BUDGETPORTFOLIO_IDSTRING = "23";

    private final Integer value;

    InstrumentType(final Integer newValue) {
        value = newValue;
    }

    public Integer getValue() { return value; }

    public InstrumentTypeGroup getTypeGroup(){
        return switch (value) {
            case 6 -> InstrumentTypeGroup.TENANT;
            case 8 -> InstrumentTypeGroup.PORTFOLIO;
            case 9 -> InstrumentTypeGroup.PORTFOLIO;
            case 10 -> InstrumentTypeGroup.PORTFOLIO;
            case 11 -> InstrumentTypeGroup.DEPOT;
            case 12 -> InstrumentTypeGroup.PORTFOLIO;
            case 13 -> InstrumentTypeGroup.SECURITY;
            case 14 -> InstrumentTypeGroup.SECURITY;
            case 15 -> InstrumentTypeGroup.SECURITY;
            case 16 -> InstrumentTypeGroup.SECURITY;
            case 17 -> InstrumentTypeGroup.SECURITY;
            case 18 -> InstrumentTypeGroup.SECURITY;
            case 19 -> InstrumentTypeGroup.LIVEINSURANCE;
            case 20 -> InstrumentTypeGroup.DEPRECATIONOBJECT;
            case 21 -> InstrumentTypeGroup.REALESTATE;
            case 22 -> InstrumentTypeGroup.LOAN;
            case 23 -> InstrumentTypeGroup.PORTFOLIO;
            default -> InstrumentTypeGroup.CASHACCOUNT;
        };
    }

    public static InstrumentType getInstrumentTypeById(int instrumenttypeId){
        return switch (instrumenttypeId) {
            case 1 -> InstrumentType.GIRO;
            case 2 -> InstrumentType.MONEYATCALL;
            case 3 -> InstrumentType.TIMEDEPOSIT;
            case 4 -> InstrumentType.BUILDINGSAVINGACCOUNT;
            case 5 -> InstrumentType.BUDGET;
            case 6 -> InstrumentType.TENANT;
            case 8 -> InstrumentType.ACCOUNTPORTFOLIO;
            case 9 -> InstrumentType.ARTIFICALPORTFOLIO;
            case 10 -> InstrumentType.BUDGETGROUP;
            case 11 -> InstrumentType.DEPOT;
            case 12 -> InstrumentType.BUILDINGSAVING;
            case 13 -> InstrumentType.CURRENCY;
            case 14 -> InstrumentType.EQUITY;
            case 15 -> InstrumentType.FONDS;
            case 16 -> InstrumentType.ETF;
            case 17 -> InstrumentType.INDEX;
            case 18 -> InstrumentType.BOND;
            case 19 -> InstrumentType.LIFEINSURANCE;
            case 20 -> InstrumentType.DEPRECATIONOBJECT;
            case 21 -> InstrumentType.REALESTATE;
            case 22 -> InstrumentType.LOAN;
            case 23 -> InstrumentType.BUDGETPORTFOLIO;
            default -> InstrumentType.UNKNOWN;
        };
    }

     public LiquidityType getLiquidityType(){
         return switch (value) {
             case 1, 2 -> LiquidityType.LIQUIDE;
             case 3, 12, 19, 22 -> LiquidityType.CALCULATED;
             case 4, 11, 20 -> LiquidityType.MIDTERM;
             case 21 -> LiquidityType.LONGTERM;
             default -> LiquidityType.UNKNOWN;
         };
    }
}


