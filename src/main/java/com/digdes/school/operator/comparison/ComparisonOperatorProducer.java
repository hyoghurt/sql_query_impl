package com.digdes.school.operator.comparison;

import com.digdes.school.exceptions.SyntaxErrorException;
import com.digdes.school.type.Type;

import java.util.ArrayList;
import java.util.List;

public class ComparisonOperatorProducer {

    static List<ComparisonOperatorInterface<Type, Type>> list;

    static {
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

    public static ComparisonOperatorInterface<Type, Type> getOperator(String symbol) {
        return list.stream()
                .filter(operator -> operator.getSymbol().equals(symbol.toUpperCase()))
                .findAny()
                .orElseThrow(() -> new SyntaxErrorException("not found comparison operator"));
    }
}
