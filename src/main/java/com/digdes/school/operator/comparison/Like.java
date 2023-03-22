package com.digdes.school.operator.comparison;

import com.digdes.school.exceptions.SyntaxErrorException;
import com.digdes.school.exceptions.TypeErrorException;
import com.digdes.school.type.NumberType;
import com.digdes.school.type.StringType;
import com.digdes.school.type.Type;

public class Like<T extends Type, U extends Type> extends ComparisonOperatorBase<T, U> {

    public Like() {
        super(ComparisonOperator.LIKE);
    }

    @Override
    public boolean test(T t, U u) {
        if (t instanceof StringType && u instanceof StringType) {
            String tt = (String) t.getValue();
            String uu = (String) u.getValue();

            if (tt == null || uu == null) {
                return false;
            }

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
                return true;
            }
            throw new SyntaxErrorException(String.format("like not support only %% : %s like %s", tt, uu));

        }
        throw new TypeErrorException(String.format("like not stringType: %s > %s", t, u));
    }

    @Override
    public void validateType(T t, U u) {
        if (t instanceof StringType && u instanceof StringType) {
            return;
        }
        throw new TypeErrorException(String.format("like not stringType: %s like %s", t, u));
    }
}

