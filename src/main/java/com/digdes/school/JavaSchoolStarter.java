package com.digdes.school;

import com.digdes.school.parser.Parser;
import com.digdes.school.table.*;
import com.digdes.school.table.cell.Cell;
import com.digdes.school.table.cell.TableCellImpl;
import com.digdes.school.type.BooleanType;
import com.digdes.school.type.DoubleType;
import com.digdes.school.type.LongType;
import com.digdes.school.type.StringType;

import java.util.*;

public class JavaSchoolStarter {
    private final List<Map<String, Object>> list = new ArrayList<>();
    private final Parser parser = new Parser();
    private final Table table;

    public JavaSchoolStarter() {
//        Map<String, Column<?>> map = new HashMap<>();
//
//        map.put("id", new Column<Long>(Long.class));
//        map.put("lastName", new Column<String>(String.class));
//        map.put("age", new Column<Long>(Long.class));
//        map.put("cost", new Column<Double>(Double.class));
//        map.put("active", new Column<Boolean>(Boolean.class));
//
//        this.table = new Table(map);

        Map<String, Cell> map = new HashMap<>();

        map.put("id", new Cell(new LongType()));
        map.put("lastName", new Cell(new StringType()));
        map.put("age", new Cell(new LongType()));
        map.put("cost", new Cell(new DoubleType()));
        map.put("active", new Cell(new BooleanType()));

        this.table = new TableCellImpl(map);
    }

    public List<Map<String, Object>> execute(String query) {
        parser.parse(query);
        Map<String, String> values = parser.getValues();

        System.out.println(values);

        table.insert(values);

        System.out.println(table);
        //TODO validate

        return null;
    }
}
