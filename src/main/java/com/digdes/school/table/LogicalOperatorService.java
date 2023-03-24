package com.digdes.school.table;

import com.digdes.school.enums.LogicalOperator;
import com.digdes.school.type.Type;

import java.util.List;

public class LogicalOperatorService {
    public static boolean isMatch(Row row, List<Condition> conditions, List<LogicalOperator> operators) {

        boolean result = isResult(row, conditions.get(0));

        for (int i = 0; operators != null && i < operators.size(); i++) {
            LogicalOperator operator = operators.get(i);
            if (operator == LogicalOperator.AND && result) {
                result = isResult(row, conditions.get(i + 1));
            } else if (operator == LogicalOperator.OR && !result) {
                result = isResult(row, conditions.get(i + 1));
            }
        }

        return result;
    }

    private static boolean isResult(Row row, Condition condition) {
        Type type = row.getCopyTypeByKey(condition.getKey());
        return condition.isSatisfy(type);
    }
}
