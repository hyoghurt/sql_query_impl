package com.digdes.school.operator.logical;

import com.digdes.school.enums.LogicalOperator;
import com.digdes.school.exceptions.SyntaxErrorException;

import java.util.ArrayList;
import java.util.List;

public class LogicalOperatorProducer {

    static List<LogicalOperatorBase> list;

    static {
        list = new ArrayList<>();
        list.add(new Or());
        list.add(new And());
    }

    public static LogicalOperatorBase getOperator(String symbol) {
        LogicalOperator lo = LogicalOperator.valueOf(symbol.toUpperCase());

        return list.stream()
                .filter(operator -> operator.getOperator() == lo)
                .findAny()
                .orElseThrow(() -> new SyntaxErrorException("not found logical operator"));
    }
}
