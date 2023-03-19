package com.digdes.school.table.cell;

import com.digdes.school.type.Type;

public class Cell implements Cloneable {
    private Type type;

    public Cell(Type type) {
        this.type = type;
    }

    public Object getValue() {
        return type.getValue();
    }

    public void setValue(String value) {
        type.setValue(value);
    }

    @Override
    public Cell clone() {
        try {
            Cell clone = (Cell) super.clone();
            clone.type = type.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    @Override
    public String toString() {
        return type.toString();
    }
}
