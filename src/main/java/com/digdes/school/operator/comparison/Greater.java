package com.digdes.school.operator.comparison;

import com.digdes.school.enums.ComparisonOperator;
import com.digdes.school.exceptions.TypeErrorException;
import com.digdes.school.type.NumberType;
import com.digdes.school.type.Type;

public class Greater<T extends Type, U extends Type> extends ComparisonOperatorBase<T, U> {

    public Greater() {
        super(ComparisonOperator.GREATER);
    }

    @Override
    public boolean test(T t, U u) {
        if (!(t instanceof NumberType) || !(u instanceof NumberType)) {
            throw new TypeErrorException(String.format("not number: %s > %s", t.getValue(), u.getValue()));
        }

        if (t.getValue() == null || u.getValue() == null) {
            return false;
        }

        NumberType tt = (NumberType) t;
        NumberType uu = (NumberType) u;
        return tt.compareTo(uu) > 0;
    }
}

