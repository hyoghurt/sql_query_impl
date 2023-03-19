package com.digdes.school.type;

public class BooleanType implements Type, Cloneable {
    private Boolean value;

    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = Boolean.parseBoolean(value);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public BooleanType clone() {
        try {
            BooleanType clone = (BooleanType) super.clone();
            clone.value = value;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
