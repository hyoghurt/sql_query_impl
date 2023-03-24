package com.digdes.school.type;

import com.digdes.school.exceptions.SyntaxErrorException;

import java.util.Objects;

public class StringType implements Type, Cloneable {
    private String value;

    public String getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        if (value.length() < 2 || !value.startsWith("'") || !value.endsWith("'")) {
            throw new SyntaxErrorException("fail value to stringType: " + value);
        }
        this.value = value.substring(1, value.length() - 1);
    }

    @Override
    public StringType clone() {
        try {
            StringType clone = (StringType) super.clone();
            clone.value = this.value;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringType that = (StringType) o;
        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
