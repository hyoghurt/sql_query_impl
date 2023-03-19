package com.digdes.school.type;

public class LongType implements Type, Cloneable {
    private Long value;

    public Long getValue() {
        return value;
    }

    @Override
    public void setValue(String value) {
        this.value = Long.parseLong(value);
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
}
