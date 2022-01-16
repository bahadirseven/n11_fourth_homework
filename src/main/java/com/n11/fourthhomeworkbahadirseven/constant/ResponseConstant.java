package com.n11.fourthhomeworkbahadirseven.constant;

public enum ResponseConstant {
    USER_DELETE_SUCCESS_MESSAGE("User deleted successfully.");
    private final String value;

    ResponseConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
