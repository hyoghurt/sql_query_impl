package com.digdes.school;

import com.digdes.school.enums.Statement;
import com.digdes.school.exceptions.SyntaxErrorException;
import com.digdes.school.parser.Parser;
import com.digdes.school.table.Table;
import com.digdes.school.table.TableImpl;
import com.digdes.school.type.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaSchoolStarter {
    private final Parser parser;
    private final Table table;

    public JavaSchoolStarter() {
        table = createTable();
        parser = new Parser();
    }

    private Table createTable() {
        Map<String, Type> map = new HashMap<>();
        map.put("id", new LongType());
        map.put("lastName", new StringType());
        map.put("age", new LongType());
        map.put("cost", new DoubleType());
        map.put("active", new BooleanType());

        return new TableImpl(map);
    }

    public List<Map<String, Object>> execute(String query) {
        parser.parse(query);
        validate();

        Statement statement = parser.getStatement();
        Map<String, Type> values = parser.getValues();
        List<Object> conditions = parser.getConditions();

        switch (statement) {
            case INSERT:
                return table.insert(values);
            case SELECT:
                return table.select(conditions);
            case UPDATE:
                return table.update(values, conditions);
            case DELETE:
                return table.delete(conditions);
        }

        throw new RuntimeException();
    }

    private void validate() {
        Statement statement = parser.getStatement();
        Map<String, Type> values = parser.getValues();
        List<Object> conditions = parser.getConditions();

        switch (statement) {
            case INSERT:
                if (conditions != null) {
                    throw new SyntaxErrorException("insert with conditions or logical operators");
                }
                if (values == null) {
                    throw new SyntaxErrorException("insert without values");
                }
                break;
            case SELECT:
                if (values != null) {
                    throw new SyntaxErrorException("select with values");
                }
                break;
            case DELETE:
                if (values != null) {
                    throw new SyntaxErrorException("delete with values");
                }
                break;
        }
    }
}
