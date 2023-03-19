package com.digdes.school.type;

public interface Type {
    void setValue(String value);
    Object getValue();
    Type clone();
}
