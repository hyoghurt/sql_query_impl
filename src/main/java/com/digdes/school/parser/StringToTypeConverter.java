package com.digdes.school.parser;

import com.digdes.school.exceptions.SyntaxErrorException;
import com.digdes.school.type.*;

public class StringToTypeConverter extends Constants {

    public Type convert(String value) {
        Type type = null;
        String regForString = String.format("^%c.*%c$", STRING_WRAPPER, STRING_WRAPPER);

        if (value.matches(regForString)){
            type = new StringType();
        } else if (value.equals(BOOLEAN_TRUE) || value.equals(BOOLEAN_FALSE)) {
            //TODO check boolean upper case
            type = new BooleanType();
        } else if (value.matches("^\\d+$")) {
            type = new LongType();
        } else if (value.matches("^\\d+\\.\\d+$")) {
            type = new DoubleType();
        }

        if (type == null) {
            throw new SyntaxErrorException("type not found");
        }

        type.setValue(value);
        return type;
    }
}
