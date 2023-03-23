package com.digdes.school.operator.comparison;

import java.util.function.BiPredicate;

public interface ComparisonOperatorInterface<T, U> extends BiPredicate<T, U> {
    String getSymbol();
    void validateType(T t, U u);
}
