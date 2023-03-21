package com.digdes.school.parser;

import com.digdes.school.Condition;
import com.digdes.school.enums.Clause;
import com.digdes.school.enums.Statement;
import com.digdes.school.exceptions.SyntaxErrorException;
import com.digdes.school.operator.comparison.ComparisonOperatorInterface;
import com.digdes.school.parser.find.*;

import java.util.*;

public class Parser {
    private Map<String, String> values = null;
    private List<Condition> conditions = null;
    private String query;
    private Integer offset;
    private Statement statement;
    private final FindComparisonOperator findComparisonOperator = new FindComparisonOperator();
    private final FindWordWrapperSpace findWordWrapperSpace = new FindWordWrapperSpace();
    private final FindAssignment findAssignment = new FindAssignment();
    private final FindString findString = new FindString();
    private final FindValue findValue = new FindValue();
    private final ComparisonOperatorProducer comparisonOperatorProducer = new ComparisonOperatorProducer();

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
        this.conditions = null;
        this.statement = null;
        this.values = null;
        this.query = query;
        this.offset = 0;

        try {
            //FIND STATEMENT
            String statement = skipSpaceDecorator(findWordWrapperSpace);
            this.statement = Statement.valueOf(statement.toUpperCase());

            if (offset < query.length()) {

                //FIND CLAUSE VALUES or WHERE
                String clause = skipSpaceDecorator(findWordWrapperSpace);
                Clause clauseEnum = Clause.valueOf(clause.toUpperCase());

                if (clauseEnum == Clause.VALUES) {
                    createValues();
                } else if (clauseEnum == Clause.WHERE) {
                    createConditions();
                }
            }

            if (offset < query.length()) {

                //FIND CLAUSE WHERE
                String clause = skipSpaceDecorator(findWordWrapperSpace);
                Clause clauseEnum = Clause.valueOf(clause.toUpperCase());

                if (clauseEnum == Clause.VALUES) {
                    throw new SyntaxErrorException();
                }

                if (conditions != null) {
                    throw new SyntaxErrorException();
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

    private void createConditions() {
        conditions = new ArrayList<>();

        String key = null;
        String value = null;
        String operatorSymbol = null;
        ComparisonOperatorInterface<?,?> operator = null;

        key = getSubstringWithoutQuote(skipSpaceDecorator(findString));
        operatorSymbol = skipSpaceDecorator(findComparisonOperator);
        operator = comparisonOperatorProducer.getOperator(operatorSymbol);
        value = skipSpaceDecorator(findValue);

        Condition condition = new Condition();
        condition.setKey(key);
        condition.setValue(value);
        condition.setOperator(operator);

        conditions.add(condition);
    }


    private void createValues() {
        final char DELIMITER = ',';

        Map.Entry<String, String> entry;
        values = new HashMap<>();

        entry = findKeyValue();
        values.put(entry.getKey(), entry.getValue());

        while (offset < query.length() && query.charAt(offset) == DELIMITER) {
            ++offset;

            entry = findKeyValue();
            values.put(entry.getKey(), entry.getValue());
        }
    }

    private Map.Entry<String, String> findKeyValue() {
        String key = getSubstringWithoutQuote(skipSpaceDecorator(findString));
        String assignment = skipSpaceDecorator(findAssignment);
        String value = skipSpaceDecorator(findValue);

        return new AbstractMap.SimpleEntry<>(key, value);
    }

    private String getSubstringWithoutQuote(String s) {
        return s.substring(1, s.length() - 1);
    }

    private String getSubstring(int[] position) {
        return this.query.substring(position[0], position[1]);
    }

    private String skipSpaceDecorator(FindBaseInterface function) {
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

//    private int[] findString(String s, int i) {
//        i = skipSpace(s, i);
//        int start = i;
//        if (i >= s.length() || s.charAt(i) != STRING_WRAPPER) {
//            return new int[] {i, i};
//        }
//
//        int end = s.indexOf(STRING_WRAPPER, start + 1);
//        if (end == -1) {
//            return new int[] {i, i};
//        }
//
//        return new int[] {start, end + 1};
//    }
//
//    private int[] findValue(String s, int i) {
//        char[] END_SYMBOLS = new char[] {',', ' '};
//
//        int start = i;
//        while (i < s.length() && !isSymbol(s.charAt(i), END_SYMBOLS)) {
//            ++i;
//        }
//        int end = i;
//        return new int[] {start, end};
//    }
//
//    private int[] findWord(String s, int i) {
//        i = skipSpace(s, i);
//
//        if (i >= s.length()) {
//            return new int[] {i, i};
//        }
//
//        int start = i;
//        int end = s.indexOf(SPACE, start);
//
//        if (end == -1) {
//            return new int[] {i, i};
//        }
//
//        return new int[] {start, end};
//    }

/*
    public List<String> parse(String query) {
        List<String> result = new ArrayList<>();

        int i = 0;

        while (i < query.length()) {

            int start = i;

            if(query.charAt(i) == STRING_WRAPPER) {
                int end = query.indexOf(STRING_WRAPPER, i + 1);
                if (end == -1) {
                    throw new SyntaxErrorException("not found end string wrapper");
                }
                result.add(query.substring(start, end));
                i = end + 1;
            } else if (isSymbol(query.charAt(i))) {
                while (isSymbol(query.charAt(i))) {
                    ++i;
                }
                result.add(query.substring(start, i - 1));
            } else if (query.charAt(i) != SPACE) {
                while (i < query.length() && !isSymbol(query.charAt(i)) && query.charAt(i) != SPACE) {
                    ++i;
                }
                result.add(query.substring(start, i - 1));
            } else {
                ++i;
            }
        }

        return result;
    }
*/
}
