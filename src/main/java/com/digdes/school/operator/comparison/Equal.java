package com.digdes.school.operator.comparison;

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
        if (t.getValue().getClass() != u.getValue().getClass()) {
            return false;
        }
        return t.equals(u);
    }

    @Override
    public void validateType(T t, U u) {
    }
}

