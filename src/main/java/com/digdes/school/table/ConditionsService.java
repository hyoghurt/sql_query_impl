package com.digdes.school.table;

import com.digdes.school.operator.logical.LogicalOperatorBase;
import com.digdes.school.type.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//Reverse Polish notation
public class ConditionsService {

    public boolean isMatch(Row row, List<Object> conditions) {
        Stack<Object> stack = new Stack<>();
        boolean res;

        for (Object obj : conditions) {

            if (obj instanceof Condition) {
                stack.addElement(obj);

            } else if (obj instanceof LogicalOperatorBase) {
                LogicalOperatorBase logicalOperatorBase = (LogicalOperatorBase) obj;

                Object right = stack.pop();
                Object left = stack.pop();

                if (right instanceof Boolean && left instanceof Boolean) {
                    Boolean booleanR = (boolean) right;
                    Boolean booleanL = (boolean) left;
                    res = logicalOperatorBase.test(booleanL, booleanR);

                } else if (right instanceof Boolean) {
                    Boolean booleanR = (boolean) right;
                    Condition conditionL = (Condition) left;
                    res = logicalOperatorBase.test(isResult(row, conditionL), booleanR);

                } else if (left instanceof Boolean) {
                    Boolean booleanL = (boolean) left;
                    Condition conditionR = (Condition) right;
                    res = logicalOperatorBase.test(booleanL, isResult(row, conditionR));

                } else {
                    Condition conditionL = (Condition) left;
                    Condition conditionR = (Condition) right;
                    res = logicalOperatorBase.test(isResult(row, conditionL), isResult(row, conditionR));
                }

                stack.addElement(res);
            }
        }

        Object obj = stack.pop();

        if (obj instanceof Condition) {
            return isResult(row, (Condition) obj);
        }

        return (boolean) obj;
    }

    private boolean isResult(Row row, Condition condition) {
        Type type = row.getCopyTypeByKey(condition.getKey());
        return condition.isMatch(type);
    }

    public List<Object> prepareConditions(List<Object> conditions) {
        List<Object> result = new ArrayList<>();
        Stack<LogicalOperatorBase> operatorStack = new Stack<>();

        for (Object obj : conditions) {
            if (obj instanceof Condition) {
                result.add(obj);
            } else if (obj instanceof LogicalOperatorBase) {
                LogicalOperatorBase operator = (LogicalOperatorBase) obj;
                while (!operatorStack.isEmpty() && operatorStack.peek().getPriority() >= operator.getPriority()) {
                    result.add(operatorStack.pop());
                }
                operatorStack.addElement(operator);
            } else {
                throw new RuntimeException("prepare conditions fail object type");
            }
        }

        while (!operatorStack.isEmpty()) {
            result.add(operatorStack.pop());
        }

        return result;
    }
}
