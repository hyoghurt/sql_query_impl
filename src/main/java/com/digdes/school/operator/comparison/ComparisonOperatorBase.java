package com.digdes.school.operator.comparison;

public abstract class ComparisonOperatorBase<T, U> implements ComparisonOperatorInterface<T, U> {
    private final ComparisonOperator operator;

    protected ComparisonOperatorBase(ComparisonOperator operator) {
        this.operator = operator;
    }

    @Override
    public String getSymbol() {
        return operator.getSymbol();
    }
}

