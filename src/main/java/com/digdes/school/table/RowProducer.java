package com.digdes.school.table;

import com.digdes.school.exceptions.FieldNotFoundException;
import com.digdes.school.exceptions.TypeErrorException;
import com.digdes.school.type.Type;

import java.util.Map;
import java.util.stream.Collectors;

public class RowProducer {
    private final Map<String, Type> fields;
    private final Map<String, String> upperKeyMapper;

    public RowProducer(Map<String, Type> map) {
        fields = map.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey().toUpperCase(), e -> e.getValue().clone()));

        upperKeyMapper = map.keySet().stream()
                .collect(Collectors.toMap(String::toUpperCase, e -> e));
    }

    public String getFieldName(String key) {
        String s = upperKeyMapper.get(key);
        if (s == null) {
            throw new FieldNotFoundException(String.format("field not found: %s", key));
        }
        return s;
    }

    public Type getCopyFieldType(String key) {
        Type type = fields.get(key);
        if (type == null) {
            throw new FieldNotFoundException(key);
        }
        return type.clone();
    }

    public void validateValues(Map<String, Type> values) {
        values.forEach((key, value) -> {
            Type type = fields.get(key);
            if (type == null) {
                throw new FieldNotFoundException("field not found: " + key);
            }
            if (value != null && value.getClass() != type.getClass()) {
                throw new TypeErrorException("fail values type: " + value.getValue());
            }
        });
    }

    public Map<String, Type> createNewMap(Map<String, Type> values) {
        Map<String, Type> map = getCopyFields();

        values.entrySet().stream()
                .filter(e -> e.getValue() != null)
                .forEach(e -> map.put(e.getKey(), e.getValue()));

        return map;
    }

    private Map<String, Type> getCopyFields() {
        return fields.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }
}
