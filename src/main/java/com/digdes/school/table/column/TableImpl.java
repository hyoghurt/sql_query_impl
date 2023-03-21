package com.digdes.school.table.column;

import com.digdes.school.table.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TableImpl {
    private final Map<String, Column<?>> fields;
    private final List<Row> rows;

    public TableImpl(Map<String, Column<?>> map) {
        this.fields = map;
        this.rows = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Table{" +
                "fields=" + fields + "\n" +
                "," + rows +
                '}';
    }
}
