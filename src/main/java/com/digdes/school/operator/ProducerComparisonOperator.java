package com.digdes.school.operator;

import com.digdes.school.exceptions.SyntaxErrorException;
import com.digdes.school.operator.comparison.*;
import com.digdes.school.type.Type;

import java.util.ArrayList;
import java.util.List;

public class ProducerComparisonOperator {

    List<ComparisonOperatorInterface<Type, Type>> list;

    public ProducerComparisonOperator() {
        list = new ArrayList<>();
        list.add(new Equal<>());
        list.add(new NotEqual<>());
        list.add(new Greater<>());
        list.add(new GreaterOrEqual<>());
        list.add(new Less<>());
        list.add(new LessOrEqual<>());
        list.add(new Like<>());
        list.add(new ILike<>());
    }

    public ComparisonOperatorInterface<Type, Type> getOperator(String value) {
        return list.stream()
                .filter(operator -> operator.getSymbol().equals(value))
                .findAny()
                .orElseThrow(() -> new SyntaxErrorException("not found comparison operator"));
    }
}
