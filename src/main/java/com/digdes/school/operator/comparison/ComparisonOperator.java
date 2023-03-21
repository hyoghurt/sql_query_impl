package com.digdes.school.operator.comparison;

public enum ComparisonOperator {
    EQUAL("="),
    NOT_EQUAL("!="),
    GREATER(">"),
    LESS("<"),
    GREATER_OR_EQUAL(">="),
    LESS_OR_EQUAL("<="),
    LIKE("LIKE"),
    ILIKE("ILIKE"),
    ;

    private final String symbol;

    ComparisonOperator(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public ComparisonOperator getFromSymbol(String symbol) {
        ComparisonOperator[] values = ComparisonOperator.values();

        for (ComparisonOperator value : values) {
            if (value.getSymbol().equalsIgnoreCase(symbol)) {
                return value;
            }
        }

        return null;
    }
}
