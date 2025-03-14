package com.pinde.core.common.enums;

public enum ExeMethod {
    SQL("sql"),JOB("job"),PROGRAM("program");

    private String value;

    ExeMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
