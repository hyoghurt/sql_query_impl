package com.digdes.school.table;

import com.digdes.school.exceptions.SyntaxErrorException;
import com.digdes.school.parser.Constants;
import com.digdes.school.type.*;

public class Converter extends Constants {

    public Type stringToType(String value, Boolean checkIsNull) {
        Type type = null;
        String regForString = String.format("^%c.*%c$", STRING_WRAPPER, STRING_WRAPPER);

        if (checkIsNull && value.equals("null")) {
            return null;
        }

        if (value.matches(regForString)){
            type = new StringType();
        } else if (value.equals(BOOLEAN_TRUE) || value.equals(BOOLEAN_FALSE)) {
            type = new BooleanType();
        } else if (value.matches("^-?\\d+$")) {
            type = new LongType();
        } else if (value.matches("^-?\\d*\\.\\d*$")) {
            if (value.equals("-.")) {
                throw new SyntaxErrorException("type not found");
            }
            type = new DoubleType();
        }

        if (type == null) {
            throw new SyntaxErrorException("type not found");
        }

        type.setValue(value);
        return type;
    }
}
