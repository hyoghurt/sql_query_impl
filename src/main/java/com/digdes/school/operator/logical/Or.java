package com.digdes.school.operator.logical;

import com.digdes.school.enums.LogicalOperator;

public class Or extends LogicalOperatorBase {
    public Or() {
        super(1, LogicalOperator.OR);
    }

    @Override
    public boolean test(Boolean a, Boolean b) {
        return a || b;
    }
}
