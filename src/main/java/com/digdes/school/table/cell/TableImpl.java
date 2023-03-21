package com.digdes.school.table.cell;

import com.digdes.school.Condition;
import com.digdes.school.exceptions.FieldNotFoundException;
import com.digdes.school.operator.logical.LogicalOperator;
import com.digdes.school.table.Table;
import com.digdes.school.type.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TableImpl implements Table {
    private final Map<String, Type> fields;
    private final Map<String, String> upperKeyMapper;
    private final List<Row> rows = new ArrayList<>();

    public TableImpl(Map<String, Type> map) {
        this.fields = map;
        upperKeyMapper = map.keySet().stream().collect(Collectors.toMap(String::toUpperCase, e -> e));
    }

    private Map<String, Type> createNewEntityForRow(Map<String, String> values) {
        Map<String, Type> row = fields.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));

        values.forEach((key, value) -> {
            Type type = row.get(getFieldKey(key));
            type.setValue(value);
        });

        return row;
    }

    private String getFieldKey(String key) {
        String s = upperKeyMapper.get(key.toUpperCase());
        if (s == null) {
            throw new FieldNotFoundException(String.format("field not found: %s", key));
        }
        return s;
    }

    private Type getNewCell(String key, String value) {
        Type cell = fields.get(getFieldKey(key));
        Type clone = cell.clone();
        clone.setValue(value);
        return clone;
    }

    @Override
    public List<Map<String, Object>> insert(Map<String, String> values) {
        Map<String, Type> map = createNewEntityForRow(values);

        Row row = new Row(map);
        rows.add(row);

        List<Map<String, Object>> list = new ArrayList<>();
        list.add(row.getCopyRowWithoutNull());

        return list;
    }


    @Override
    public List<Map<String, Object>> update(Map<String, String> values,
                                            List<Condition> conditions,
                                            List<LogicalOperator> logicalOperators) {

        List<Row> rows = new ArrayList<>();

        // prepare
        for (Condition condition : conditions) {
            condition.setType(getNewCell(condition.getKey(), condition.getValue()));
        }

        for (Row row : this.rows) {
            if (row.isCondition(conditions, logicalOperators)) {
                rows.add(row);
            }
        }

        List<Map<String, Object>> result = new ArrayList<>();

        for (Row row : rows) {
            row.update(createNewEntityForRow(values));
            result.add(row.getCopyRowWithoutNull());
        }

        return result;
    }

    @Override
    public List<Map<String, Object>> select(List<Condition> conditions) {
        if (conditions == null) {
            return this.rows.stream()
                    .map(Row::getCopyRowWithoutNull)
                    .collect(Collectors.toList());
        }

        List<Row> rows = new ArrayList<>();

        // prepare
        for (Condition condition : conditions) {
            condition.setType(getNewCell(condition.getKey(), condition.getValue()));
        }

        for (Row row : this.rows) {
            if (row.isCondition(conditions)) {
                rows.add(row);
            }
        }

        List<Map<String, Object>> result = new ArrayList<>();

        for (Row row : rows) {
            result.add(row.getCopyRowWithoutNull());
        }

        return result;
    }

    @Override
    public String toString() {
        return "Table{" +
                "fields=" + fields + "\n" +
                "," + rows +
                '}';
    }
}
