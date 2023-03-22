package com.digdes.school.table;

import com.digdes.school.exceptions.FieldNotFoundException;
import com.digdes.school.type.Type;

import java.util.Map;
import java.util.stream.Collectors;

public class TableFields {
    private final Map<String, Type> fields;
    private final Map<String, String> upperKeyMapper;

    public TableFields(Map<String, Type> map) {
        this.fields = map;
        upperKeyMapper = map.keySet().stream().collect(Collectors.toMap(String::toUpperCase, e -> e));
    }

    public String getFieldName(String key) {
        String s = upperKeyMapper.get(key.toUpperCase());
        if (s == null) {
            throw new FieldNotFoundException(String.format("field not found: %s", key));
        }
        return s;
    }

    public Map<String, Type> createNewMap(Map<String, String> values) {
        Map<String, Type> map = fields.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));

        values.forEach((key, value) -> {
            Type type = map.get(getFieldName(key));
            if (!value.equals("null")) {
                type.setValue(value);
            }
        });

        return map;
    }

    public Type createNewCell(String key, String value) {
        Type cell = fields.get(getFieldName(key));
        Type clone = cell.clone();
        clone.setValue(value);
        return clone;
    }

    public Type getFieldType(String key) {
        String fieldName = getFieldName(key);
        return fields.get(fieldName);
    }
}
