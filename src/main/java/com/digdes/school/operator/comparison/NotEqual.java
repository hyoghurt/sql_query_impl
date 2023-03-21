package com.digdes.school.operator.comparison;

import com.digdes.school.exceptions.TypeErrorException;

public class NotEqual<T, U> extends ComparisonOperatorBase<T, U> {

    public NotEqual() {
        super(ComparisonOperator.NOT_EQUAL);
    }

    @Override
    public boolean test(T t, U u) {
        if (t == null && u == null) {
            return false;
        }
        if (t == null || u == null) {
            return true;
        }
        if (t.getClass() != u.getClass()) {
            throw new TypeErrorException(String.format("class not equals: %s and %s", t, u));
        }
        return !t.equals(u);
    }
}

