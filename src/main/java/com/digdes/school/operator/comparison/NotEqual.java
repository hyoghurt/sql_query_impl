package com.digdes.school.operator.comparison;

import com.digdes.school.enums.ComparisonOperator;
import com.digdes.school.type.Type;

public class NotEqual<T extends Type, U extends Type> extends ComparisonOperatorBase<T, U> {

    public NotEqual() {
        super(ComparisonOperator.NOT_EQUAL);
    }

    @Override
    public boolean test(T t, U u) {
        if (t.getValue() == null || u.getValue() == null) {
            return false;
        }
        return !t.equals(u);
    }
}

