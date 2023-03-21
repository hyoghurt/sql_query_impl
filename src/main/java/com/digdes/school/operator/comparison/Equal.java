package com.digdes.school.operator.comparison;

public class Equal<T, U> extends ComparisonOperatorBase<T, U> {

    public Equal() {
        super(ComparisonOperator.EQUAL);
    }

    @Override
    public boolean test(T t, U u) {
        if (t == null || u == null) {
            return false;
        }
        if (t.getClass() != u.getClass()) {
            return false;
        }
        return t.equals(u);
    }
}

