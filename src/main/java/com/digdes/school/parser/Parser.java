package com.digdes.school.parser;

import com.digdes.school.enums.*;
import com.digdes.school.operator.logical.LogicalOperatorBase;
import com.digdes.school.operator.logical.LogicalOperatorProducer;
import com.digdes.school.table.Condition;
import com.digdes.school.exceptions.SyntaxErrorException;
import com.digdes.school.parser.find.*;
import com.digdes.school.type.Type;

import java.util.*;

public class Parser extends Constants {
    private String query;
    private Integer offset;
    private Statement statement;
    private Map<String, Type> values = null;
    private List<Object> conditions = null;
    private final FindComparisonOperator findComparisonOperator = new FindComparisonOperator();
    private final FindAssignment findAssignment = new FindAssignment();
    private final FindStatement findStatement = new FindStatement();
    private final FindString findString = new FindString();
    private final FindValue findValue = new FindValue();

    public Statement getStatement() {
        return statement;
    }

    public Map<String, Type> getValues() {
        return values;
    }

    public List<Object> getConditions() {
        return conditions;
    }

    public void parse(String query) {
        this.conditions = null;
        this.statement = null;
        this.values = null;
        this.query = query;
        this.offset = 0;

        try {
            String statement = skipSpaceDecorator(findStatement);
            this.statement = Statement.valueOf(statement.toUpperCase());

            if (offset < query.length()) {
                String clause = skipSpaceDecorator(findStatement);
                Clause clauseEnum = Clause.valueOf(clause.toUpperCase());

                if (clauseEnum == Clause.VALUES) {
                    createValues();
                } else if (clauseEnum == Clause.WHERE) {
                    createConditions();
                }
            }

            if (offset < query.length()) {
                String clause = skipSpaceDecorator(findStatement);
                Clause clauseEnum = Clause.valueOf(clause.toUpperCase());

                if (clauseEnum == Clause.VALUES) {
                    throw new SyntaxErrorException("more values");
                } else if (conditions != null) {
                    throw new SyntaxErrorException("more conditions");
                }

                createConditions();
            }

            if (offset < query.length()) {
                throw new SyntaxErrorException("more query");
            }

        } catch (IllegalArgumentException e) {
            throw new SyntaxErrorException();
        }
    }

    private void createValues() {
        values = new HashMap<>();

        while (true) {
            String[] arr = findKeyValue();
            if (values.containsKey(arr[0])) {
                throw new SyntaxErrorException("double field name: " + arr[0]);
            }
            values.put(arr[0], Converter.valueToType(arr[1], true));

            if (offset < query.length() && query.charAt(offset) == VALUES_DELIMITER) {
                ++offset;
            } else {
                break;
            }
        }
    }

    private void createConditions() {
        conditions = new ArrayList<>();
        Stack<LogicalOperatorBase> stack = new Stack<>();

        while (offset < query.length()) {
            Condition condition = findCondition();
            conditions.add(condition);

            if (offset < query.length()) {
                String symbol = skipSpaceDecorator(findStatement);
                LogicalOperatorBase operator = LogicalOperatorProducer.getOperator(symbol);

                while (!stack.isEmpty() && stack.peek().getPriority() >= operator.getPriority()) {
                    conditions.add(stack.pop());
                }
                stack.addElement(operator);
            }
        }

        while (!stack.isEmpty()) {
            conditions.add(stack.pop());
        }

        if (conditions.isEmpty()) {
            throw new SyntaxErrorException(getExceptionMsg("values not found"));
        }
    }

    private String[] findKeyValue() {
        String key = getSubstringWithoutQuote(skipSpaceDecorator(findString));
        skipSpaceDecorator(findAssignment);
        String value = skipSpaceDecorator(findValue);
        return new String[] {key.toUpperCase(), value};
    }

    private Condition findCondition() {
        String key = getSubstringWithoutQuote(skipSpaceDecorator(findString));
        String symbol = skipSpaceDecorator(findComparisonOperator);
        String value = skipSpaceDecorator(findValue);

        Condition condition = new Condition();
        condition.setKey(key.toUpperCase());
        condition.setValue(value);
        condition.setOperatorSymbol(symbol);

        return condition;
    }

    private String getSubstringWithoutQuote(String s) {
        return s.substring(1, s.length() - 1);
    }

    private String getSubstring(int[] position) {
        return this.query.substring(position[0], position[1]);
    }

    private String skipSpaceDecorator(FindInterface function) {
        offset = function.skipSpace(query, offset);
        int[] position = function.apply(query, offset);
        checkAndThrowException(position);
        offset = function.skipSpace(query, position[1]);
        return getSubstring(position);
    }

    private void checkAndThrowException(int[] position) {
        if (position[0] == position[1]) {
            throw new SyntaxErrorException(getExceptionMsg("error"));
        }
    }

    private String getExceptionMsg(String msg) {
        return String.format("%s, position %d, query - %s", msg, offset, query);
    }
}
