package com.digdes.school.operator.comparison;

import com.digdes.school.enums.ComparisonOperator;
import com.digdes.school.type.Type;

public class Equal<T extends Type, U extends Type> extends ComparisonOperatorBase<T, U> {

    public Equal() {
        super(ComparisonOperator.EQUAL);
    }

    @Override
    public boolean test(T t, U u) {
        if (t.getValue() == null || u.getValue() == null) {
            return false;
        }
        return t.equals(u);
    }
}

