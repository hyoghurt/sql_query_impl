package com.digdes.school.operator.comparison;

import com.digdes.school.enums.ComparisonOperator;
import com.digdes.school.exceptions.TypeErrorException;
import com.digdes.school.type.NumberType;
import com.digdes.school.type.Type;

public class LessOrEqual<T extends Type, U extends Type> extends ComparisonOperatorBase<T, U> {

    public LessOrEqual() {
        super(ComparisonOperator.LESS_OR_EQUAL);
    }

    @Override
    public boolean test(T t, U u) {
        if (t.getValue() == null || u.getValue() == null) {
            return false;
        }

        if (t instanceof NumberType && u instanceof NumberType) {
            NumberType tt = (NumberType) t;
            NumberType uu = (NumberType) u;
            return (tt.compareTo(uu) < 0 || tt.compareTo(uu) == 0);
        }
        throw new TypeErrorException(String.format("less or equal not number: %s <= %s",
                t.getValue(), u.getValue()));
    }

    @Override
    public void validateType(T t, U u) {
        if (!(t instanceof NumberType) || !(u instanceof NumberType)) {
            throw new TypeErrorException(String.format("less or equal not number: %s <= %s",
                    t.getValue(), u.getValue()));
        }
    }
}

