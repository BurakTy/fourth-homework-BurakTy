package com.buraktuysuz.fourthhomework.enums;

public enum EnumDebtType {

    MAIN,
    INTRESET;

    private final int value;

    EnumDebtType() {
        this.value = ordinal();
    }

    public int getType() {
        return value;
    }

    public static EnumDebtType fromValue(int value)
            throws IllegalArgumentException {
        try {
            return EnumDebtType.values()[value];
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Unknown enum value :"+ value);
        }
    }

    @Override
    public String toString() {
        return this.name();
    }
}
