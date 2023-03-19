package com.digdes.school.table.column;

import com.digdes.school.table.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TableImpl implements Table {
    private final Map<String, Column<?>> fields;
    private final List<Row> rows;

    public TableImpl(Map<String, Column<?>> map) {
        this.fields = map;
        this.rows = new ArrayList<>();
    }

    @Override
    public Map<String, Object> insert(Map<String, String> map1) {
        Row row = new Row(map1, fields);
        rows.add(row);
        return row.getR();
    }

    public Map<String, Object> update(Map<String, String> map1) {
        Row row = new Row(map1, fields);
        rows.add(row);
        return row.getR();
    }

    public Map<String, Object> delete(Map<String, String> map1) {
        Row row = new Row(map1, fields);
        rows.add(row);
        return row.getR();
    }

    public Map<String, Object> select(Map<String, String> map1) {
        Row row = new Row(map1, fields);
        rows.add(row);
        return row.getR();
    }

    @Override
    public String toString() {
        return "Table{" +
                "fields=" + fields + "\n" +
                "," + rows +
                '}';
    }
}
