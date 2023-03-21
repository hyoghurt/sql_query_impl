package com.digdes.school.parser.find;

import com.digdes.school.operator.comparison.ComparisonOperator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindComparisonOperator extends FindBase {
    private final ComparisonOperator[] operators = ComparisonOperator.values();

    @Override
    public int[] apply(String s, Integer i) {
        List<String> symbols = Arrays.stream(operators)
                .map(ComparisonOperator::getSymbol)
                .sorted((a,b) -> b.length() - a.length())
                .collect(Collectors.toList());

        s = s.toUpperCase();

        for (String symbol : symbols) {
            if (s.startsWith(symbol, i)) {
                return new int[] {i, i + symbol.length()};
            }
        }

        return new int[] {i, i};
    }
}
