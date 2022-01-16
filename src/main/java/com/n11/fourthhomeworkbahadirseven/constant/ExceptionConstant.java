package com.n11.fourthhomeworkbahadirseven.constant;

public enum ExceptionConstant {
    UNEXPECTED_STATUS("Unexpected status at server!"),
    USER_NOT_FOUND("User not found for user id: %s"),
    LOAN_NOT_FOUND("Loan is not found!"),
    LOAN_NOT_FOUND_WITH_DATES("Loan info not found between the specified dates!"),
    LOAN_NOT_FOUND_BY_USER_ID("Loan info not found for user id: %s"),
    EXPIRED_LOAN_NOT_FOUND_BY_USER_ID("Expired loan info not found for user id: %s"),
    LATE_FEE_NOT_FOUND_BY_USER_ID("Late fee info not found for user id: %s"),
    PAYMENT_MUST_EQUAL("Partial payment is not allowed!"),
    PAYMENT_NOT_FOUND_BY_USER_ID("Payment info not found for user id: %s"),
    PAYMENT_NOT_FOUND_WITH_DATES("Payment info not found between the specified dates!");

    private final String value;

    ExceptionConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
