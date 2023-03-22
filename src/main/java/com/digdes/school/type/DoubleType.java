package com.digdes.school.type;

import com.digdes.school.exceptions.SyntaxErrorException;
import com.digdes.school.exceptions.TypeErrorException;

import java.util.Objects;

public class DoubleType implements NumberType, Cloneable {
    private Double value;

    public Double getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        try {
            this.value = Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new SyntaxErrorException("fail value to double: " + value);
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleType that = (DoubleType) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public int compareTo(NumberType type) {
        Object typeObject = type.getValue();
        if (typeObject instanceof Long) {
            return value.compareTo((double) (long) type.getValue());
        }
        return value.compareTo((double) type.getValue());
    }
}
