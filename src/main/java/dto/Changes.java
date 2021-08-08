package dto;

import java.math.BigDecimal;

public enum Changes {
    CENT(new BigDecimal("0.01")),
    NICKEL(new BigDecimal("0.05")),
    DIME(new BigDecimal("0.10")),
    QUARTER(new BigDecimal("0.25")),
    DOLLAR(new BigDecimal("1"));

    private BigDecimal value;

    Changes(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public static Changes[] getAllCoins(){
        Changes[] changeArray = {Changes.DOLLAR,
                Changes.QUARTER, Changes.DIME,
                Changes.NICKEL, Changes.CENT};
        return changeArray;
    }
}
