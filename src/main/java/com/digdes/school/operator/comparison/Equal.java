package com.digdes.school.operator.comparison;

import java.util.function.BiPredicate;

public class Equal<T, U> implements BiPredicate<T, U> {
    private final String symbol = "=";

    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean test(T t, U u) {
        if (t.getClass() != u.getClass()) {
            return false;
        }
        return t.equals(u);
    }
}

