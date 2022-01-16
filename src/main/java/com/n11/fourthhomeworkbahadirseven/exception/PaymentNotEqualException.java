package com.n11.fourthhomeworkbahadirseven.exception;

public class PaymentNotEqualException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public PaymentNotEqualException(String errorMessage) {
        super(errorMessage);
    }
}
