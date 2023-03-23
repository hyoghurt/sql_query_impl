package com.digdes.school.table;

import com.digdes.school.exceptions.FieldNotFoundException;
import com.digdes.school.exceptions.TypeErrorException;
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

    public Type getFieldType(String key) {
        String fieldName = getFieldName(key);
        return fields.get(fieldName);
    }

    public Map<String, Type> createNewMap(Map<String, Type> values) {
        Map<String, Type> map = getCopyFields();

        values.forEach((key, value) -> {
            String fieldName = getFieldName(key);
            if (!map.containsKey(fieldName)) {
                throw new FieldNotFoundException("field not found: " + key);
            }
            if (value != null) {
                Type type = map.get(fieldName);
                if (type.getClass() != value.getClass()) {
                    throw new TypeErrorException("fail values type: " + value.getValue());
                }
                map.put(fieldName, value);
            }
        });

        return map;
    }

    private Map<String, Type> getCopyFields() {
        return fields.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }
}
