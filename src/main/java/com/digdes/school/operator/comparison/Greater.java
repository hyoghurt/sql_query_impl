package com.digdes.school.operator.comparison;

import com.digdes.school.exceptions.TypeErrorException;

public class Greater<T extends Number & Comparable<U>, U extends Number & Comparable<T>> extends ComparisonOperatorBase<T, U> {

    public Greater() {
        super(ComparisonOperator.GREATER);
    }

    @Override
    public boolean test(T t, U u) {
        if (t == null) {
            return false;
        }
        if (t.getClass() != u.getClass()) {
            throw new TypeErrorException(String.format("class not equals: %s and %s", t, u));
        }
        return t.compareTo(u) > 0;
    }
}

