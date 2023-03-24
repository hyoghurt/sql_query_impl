package com.digdes.school.operator.logical;

import com.digdes.school.enums.LogicalOperator;

public class And extends LogicalOperatorBase {
    public And() {
        super(2, LogicalOperator.AND);
    }

    @Override
    public boolean test(Boolean a, Boolean b) {
        return a && b;
    }
}
