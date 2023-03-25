package com.digdes.school.table;

import com.digdes.school.operator.comparison.ComparisonOperatorInterface;
import com.digdes.school.operator.comparison.ComparisonOperatorProducer;
import com.digdes.school.parser.Converter;
import com.digdes.school.type.Type;

public class Condition {
    private String key;
    private String value;
    private Type type;
    private ComparisonOperatorInterface<Type, Type> operator;

    public boolean isMatch(Type type) {
        return operator.test(type, this.type);
    }

    public void setOperatorSymbol(String symbol) {
        this.operator = ComparisonOperatorProducer.getOperator(symbol);
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
        this.type = Converter.valueToType(value, false);
    }

    public Type getType() {
        return type;
    }

    public ComparisonOperatorInterface<Type, Type> getOperator() {
        return operator;
    }
}
