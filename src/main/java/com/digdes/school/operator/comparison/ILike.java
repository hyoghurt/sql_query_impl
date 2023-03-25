package com.digdes.school.operator.comparison;

import com.digdes.school.enums.ComparisonOperator;
import com.digdes.school.exceptions.TypeErrorException;
import com.digdes.school.type.StringType;
import com.digdes.school.type.Type;

public class ILike<T extends Type, U extends Type> extends ComparisonOperatorBase<T, U> {

    public ILike() {
        super(ComparisonOperator.ILIKE);
    }

    @Override
    public boolean test(T t, U u) {
        if (!(t instanceof StringType) || !(u instanceof StringType)) {
            throw new TypeErrorException(String.format("not string: %s ILIKE %s", t.getValue(), u.getValue()));
        }

        if (t.getValue() == null || u.getValue() == null) {
            return false;
        }

        String tt = (String) t.getValue();
        String uu = (String) u.getValue();

        tt = tt.toUpperCase();
        uu = uu.toUpperCase();

        if (uu.length() >= 2) {
            if (uu.startsWith("%") && uu.endsWith("%")) {
                return tt.contains(uu.substring(1, uu.length() - 1));
            }
            if (uu.startsWith("%")) {
                return tt.endsWith(uu.substring(1));
            }
            if (uu.endsWith("%")) {
                return tt.startsWith(uu.substring(0, uu.length() - 1));
            }
        }

        return tt.equals(uu);
    }
}

