package com.digdes.school.type;

public class DoubleType implements Type, Cloneable {
    private Double value;

    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = Double.parseDouble(value);
    }

    @Override
    public DoubleType clone() {
        try {
            DoubleType clone = (DoubleType) super.clone();
            clone.value = this.value;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
