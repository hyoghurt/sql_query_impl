package com.digdes.school.table.cell;

import com.digdes.school.table.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TableCellImpl implements Table {
    private final Map<String, Cell> fields;
    private final List<RowCell> rows;

    public TableCellImpl(Map<String, Cell> map) {
        this.fields = map;
        this.rows = new ArrayList<>();
    }

    @Override
    public Map<String, Object> insert(Map<String, String> map1) {
        RowCell row = new RowCell(map1, fields);
        rows.add(row);
        return row.getR();
    }

//    public Map<String, Object> update(Map<String, String> map1) {
//        Row row = new Row(map1, fields);
//        rows.add(row);
//        return row.getR();
//    }
//
//    public Map<String, Object> delete(Map<String, String> map1) {
//        Row row = new Row(map1, fields);
//        rows.add(row);
//        return row.getR();
//    }
//
//    public Map<String, Object> select(Map<String, String> map1) {
//        Row row = new Row(map1, fields);
//        rows.add(row);
//        return row.getR();
//    }

    @Override
    public String toString() {
        return "Table{" +
                "fields=" + fields + "\n" +
                "," + rows +
                '}';
    }
}
