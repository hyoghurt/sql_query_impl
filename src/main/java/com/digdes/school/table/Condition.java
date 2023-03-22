package com.digdes.school.table;

import com.digdes.school.operator.comparison.ComparisonOperatorInterface;
import com.digdes.school.type.Type;

public class Condition {
    private String key;
    private String value;
    private Type type;
    private String operatorSymbol;
    private ComparisonOperatorInterface<Type, Type> operator;

    public String getOperatorSymbol() {
        return operatorSymbol;
    }

    public void setOperatorSymbol(String operatorSymbol) {
        this.operatorSymbol = operatorSymbol;
    }

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

    public ComparisonOperatorInterface<Type, Type> getOperator() {
        return operator;
    }

    public void setOperator(ComparisonOperatorInterface<Type, Type> operator) {
        this.operator = operator;
    }
}
