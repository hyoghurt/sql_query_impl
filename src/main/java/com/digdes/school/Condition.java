package com.digdes.school;

import com.digdes.school.type.Type;

import java.util.function.BiPredicate;

public class Condition {
    private String key;
    private String value;
    private Type type;
    private BiPredicate operator;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public BiPredicate getOperator() {
        return operator;
    }

    public void setOperator(BiPredicate operator) {
        this.operator = operator;
    }
}
