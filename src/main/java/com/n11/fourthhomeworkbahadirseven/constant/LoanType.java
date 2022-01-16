package com.n11.fourthhomeworkbahadirseven.constant;

public enum LoanType {
    MAIN_LOAN ("MAIN"),
    EXPIRE_LOAN ("EXPIRE");

    private final String value;

    LoanType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
