package com.digdes.school.type;

import com.digdes.school.exceptions.TypeErrorException;

import java.util.Objects;

public class LongType implements Type, Cloneable {
    private Long value;

    public Long getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        try {
            this.value = Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new TypeErrorException("string to long: " + value);
        }
    }

    @Override
    public LongType clone() {
        try {
            LongType clone = (LongType) super.clone();
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
        LongType longType = (LongType) o;
        return Objects.equals(value, longType.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
