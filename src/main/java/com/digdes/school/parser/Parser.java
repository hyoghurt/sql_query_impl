package com.digdes.school.parser;

import com.digdes.school.enums.Clause;
import com.digdes.school.enums.Statement;
import com.digdes.school.exceptions.SyntaxErrorException;

import java.util.HashMap;
import java.util.Map;

public class Parser {
    private Map<String, String> values;
    private String query;
    private final FindValue findValue = new FindValue();
    private final FindString findString = new FindString();
    private final FindStatement findStatement = new FindStatement();
    private final FindAssignment findAssignment = new FindAssignment();


    public Map<String, String> getValues() {
        return values;
    }

    private void checkAndThrowException(int[] position, String msg) {
        if (position[0] == position[1]) {
            throw new SyntaxErrorException(msg);
        }
    }

    public void parse(String query) {
        this.query = query;

        int i = 0;

        int[] statementPosition = skipSpaceStartDecorator(i, findStatement);
        checkAndThrowException(statementPosition, "not found statement");

        String statement = query.substring(statementPosition[0], statementPosition[1]);
        i = statementPosition[1];

        if (statement.equalsIgnoreCase(Statement.INSERT.name())) {

            int[] clausePosition = skipSpaceStartDecorator(i, findStatement);
            checkAndThrowException(clausePosition, "not found VALUES for INSERT");

            String clause = query.substring(clausePosition[0], clausePosition[1]);

            if (!clause.equalsIgnoreCase(Clause.VALUES.name())) {
                throw new SyntaxErrorException("not found VALUES for INSERT");
            }

            i = createValues(clausePosition[1]);
        }

        //values ... where ...
    }

    private int createValues(int i) {
        this.values = new HashMap<>();

        i = findAndPutValue(i);
        i = findStatement.skipSpace(query, i);

        while (i < query.length() && query.charAt(i) == ',') {
            ++i;
            i = findAndPutValue(i);
            i = findStatement.skipSpace(query, i);
        }

        i = findStatement.skipSpace(query, i);
        //TODO check map is empty ?

        return i;
    }

    private int findAndPutValue(int i) {
        int[] keyPosition = skipSpaceStartDecorator(i, findString);
        checkAndThrowException(keyPosition, "not found field name");
        String key = query.substring(keyPosition[0], keyPosition[1]);

        i = keyPosition[1];

        int[] assignmentPosition = skipSpaceStartDecorator(i, findAssignment);
        checkAndThrowException(assignmentPosition, "not found assignment symbol, position " + assignmentPosition[0]);

        i = assignmentPosition[1];

        int[] valuePosition = skipSpaceStartDecorator(i, findValue);
        checkAndThrowException(keyPosition, "not found field value for " + key);
        String value = query.substring(valuePosition[0], valuePosition[1]);

        i = valuePosition[1];

        values.put(key.substring(1, key.length() - 1), value);

        return i;
    }

    private int[] skipSpaceStartDecorator(int i, FindBaseI function) {
        i = function.skipSpace(query, i);
        return function.apply(query, i);
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
