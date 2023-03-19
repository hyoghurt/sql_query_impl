package com.digdes.school.table.column;

import com.digdes.school.exceptions.TypeErrorException;

public class Column<T> implements Cloneable {
    private final Class<T> claz;
    private T value;

    public Column(Class<T> claz) {
        this.claz = claz;
    }

    public T getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

//    public void setClaz(Class<T> claz) {
//        this.claz = claz;
//    }

    public void setValue(T value) {
        this.value = value;
    }

    public void setValue(String value) {
        if (claz == Long.class) {
            try {
                Long l = Long.parseLong(value);
                this.value = (T) l;
            } catch (NumberFormatException e) {
                throw new TypeErrorException("[Long]");
            }
        } else if (claz == Double.class) {
            Double d = Double.parseDouble(value);
            this.value = (T) d;
        } else {
            this.value = (T) value;
        }
//        Integer.parseInt(value);
//        Boolean.parseBoolean(value);
//        if (this.value.getClass() == Long.class) {
//            this.value = Long.parseLong(value);
//        }

//        if (claz == Long.class) {
//            System.out.println("k");
//        }
//
//        this.value = value;
    }

    @Override
    public Column<T> clone() {
        try {
            Column<T> clone = (Column<T>) super.clone();
            clone.setValue(this.value);
//            clone.setClaz(this.claz);
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

//    public Column<T> getInstant() {
//        Column<T> column = new Column<>();
//        column.setValue(value);
//
//        return column;
//    }
}
