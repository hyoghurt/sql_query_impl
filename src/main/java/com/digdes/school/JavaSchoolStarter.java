package com.digdes.school;

import com.digdes.school.enums.Statement;
import com.digdes.school.exceptions.SyntaxErrorException;
import com.digdes.school.parser.Parser;
import com.digdes.school.table.Condition;
import com.digdes.school.table.Table;
import com.digdes.school.table.TableImpl;
import com.digdes.school.type.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaSchoolStarter {
    private final Parser parser = new Parser();
    private final Table table;

    public JavaSchoolStarter() {
        table = createTable();
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

        Statement statement = parser.getStatement();
        Map<String, String> values = parser.getValues();
        List<Condition> conditions = parser.getConditions();

        if (statement == Statement.INSERT) {
            if (values == null || values.isEmpty()) {
                throw new SyntaxErrorException("not values");
            }
            if (conditions != null) {
                throw new SyntaxErrorException("insert with conditions");
            }
            return table.insert(values);
        } else if (statement == Statement.SELECT) {
            if (values != null) {
                throw new SyntaxErrorException("select with values");
            }
            return table.select(conditions);
        }

        return null;
    }
}
