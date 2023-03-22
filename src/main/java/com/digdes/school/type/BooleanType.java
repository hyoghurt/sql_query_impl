package com.digdes.school.type;

import com.digdes.school.exceptions.SyntaxErrorException;

import java.util.Objects;

public class BooleanType implements Type, Cloneable {
    private Boolean value;

    public Boolean getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        if (value != null) {
            if (value.equals("true")) {
                this.value = true;
            } else if (value.equals("false")) {
                this.value = false;
            } else {
                throw new SyntaxErrorException("fail value to boolean: " + value);
            }
        }
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

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooleanType that = (BooleanType) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
