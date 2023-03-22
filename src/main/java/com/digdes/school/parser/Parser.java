package com.digdes.school.parser;

import com.digdes.school.table.Condition;
import com.digdes.school.enums.Clause;
import com.digdes.school.enums.Statement;
import com.digdes.school.exceptions.SyntaxErrorException;
import com.digdes.school.operator.logical.LogicalOperator;
import com.digdes.school.parser.find.*;

import java.util.*;

public class Parser extends Constants {
    private List<LogicalOperator> logicalOperators = null;
    private List<Condition> conditions = null;
    private Map<String, String> values = null;
    private String query;
    private Integer offset;
    private Statement statement;
    private final FindComparisonOperator findComparisonOperator = new FindComparisonOperator();
    private final FindAssignment findAssignment = new FindAssignment();
    private final FindStatement findStatement = new FindStatement();
    private final FindString findString = new FindString();
    private final FindValue findValue = new FindValue();

    public Statement getStatement() {
        return statement;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public List<Condition> getConditions() {
        return conditions;
    }

    public void parse(String query) {
        this.logicalOperators = null;
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
            Map.Entry<String, String> entry = findKeyValue();

            if (values.containsKey(entry.getKey())) {
                throw new SyntaxErrorException("double field name: " + entry.getKey());
            }
            values.put(entry.getKey(), entry.getValue());

            if (offset < query.length() && query.charAt(offset) == DELIMITER) {
                ++offset;
            } else {
                break;
            }
        }
    }

    private void createConditions() {
        conditions = new ArrayList<>();
        logicalOperators = new ArrayList<>();

        while (true) {
            conditions.add(findCondition());

            if (offset < query.length()) {
                String logicalOperator = skipSpaceDecorator(findStatement);
                LogicalOperator lo = LogicalOperator.valueOf(logicalOperator.toUpperCase());
                logicalOperators.add(lo);
            } else {
                break;
            }
        }
    }

    private Map.Entry<String, String> findKeyValue() {
        String key = getSubstringWithoutQuote(skipSpaceDecorator(findString));
        skipSpaceDecorator(findAssignment);
        String value = skipSpaceDecorator(findValue);

        return new AbstractMap.SimpleEntry<>(key, value);
    }

    private Condition findCondition() {
        String key = getSubstringWithoutQuote(skipSpaceDecorator(findString));
        String operatorSymbol = skipSpaceDecorator(findComparisonOperator);
        String value = skipSpaceDecorator(findValue);

        Condition condition = new Condition();
        condition.setKey(key);
        condition.setValue(value);
        condition.setOperatorSymbol(operatorSymbol);

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
        checkAndThrowException(position, "not found");
        offset = function.skipSpace(query, position[1]);
        return getSubstring(position);
    }

    private void checkAndThrowException(int[] position, String msg) {
        if (position[0] == position[1]) {
            throw new SyntaxErrorException(String.format("%s, position %d, query - %s", msg, offset, query));
        }
    }
}
