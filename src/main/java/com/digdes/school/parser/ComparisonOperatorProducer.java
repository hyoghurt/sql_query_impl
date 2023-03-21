package com.digdes.school.parser;

import com.digdes.school.exceptions.SyntaxErrorException;
import com.digdes.school.operator.comparison.ComparisonOperatorInterface;
import com.digdes.school.operator.comparison.Equal;

import java.util.ArrayList;
import java.util.List;

public class ComparisonOperatorProducer {

    List<ComparisonOperatorInterface<?, ?>> list;

    public ComparisonOperatorProducer() {
        list = new ArrayList<>();
        list.add(new Equal<>());
    }

    ComparisonOperatorInterface<?, ?> getOperator(String value) {

        for (ComparisonOperatorInterface<?, ?> operator : list) {
            if (operator.getSymbol().equals(value)) {
                return operator;
            }
        }

        throw new SyntaxErrorException("not found comparison operator");
    }
}
