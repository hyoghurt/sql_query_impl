package com.digdes.school.operator.comparison;

import com.digdes.school.exceptions.TypeErrorException;
import com.digdes.school.type.NumberType;
import com.digdes.school.type.Type;

public class Greater<T extends Type, U extends Type> extends ComparisonOperatorBase<T, U> {

    public Greater() {
        super(ComparisonOperator.GREATER);
    }

    @Override
    public boolean test(T t, U u) {
        if (t instanceof NumberType && u instanceof NumberType) {
            NumberType tt = (NumberType) t;
            NumberType uu = (NumberType) u;
            if (tt.getValue() == null || uu.getValue() == null) {
                return false;
            }
            return tt.compareTo(uu) > 0;
        }
        throw new TypeErrorException(String.format("greater not numberType: %s > %s", t, u));
    }

    @Override
    public void validateType(T t, U u) {
        if (t instanceof NumberType && u instanceof NumberType) {
            return;
        }
        throw new TypeErrorException(String.format("greater not numberType: %s > %s", t, u));
    }
}

