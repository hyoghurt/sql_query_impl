package com.digdes.school.operator.logical;

import com.digdes.school.enums.LogicalOperator;

import java.util.function.BiPredicate;

public abstract class LogicalOperatorBase implements BiPredicate<Boolean, Boolean> {
    private final int priority;
    private final LogicalOperator operator;

    protected LogicalOperatorBase(int priority, LogicalOperator operator) {
        this.priority = priority;
        this.operator = operator;
    }

    public int getPriority() {
        return priority;
    }

    public LogicalOperator getOperator() {
        return operator;
    }
}
