package com.digdes.school.table;

import com.digdes.school.exceptions.FieldNotFoundException;
import com.digdes.school.type.Type;

import java.util.Map;
import java.util.stream.Collectors;

public class Row {
    private final Map<String, Type> map;

    public Row(Map<String, Type> map) {
        this.map = map;
    }

    public Map<String, Object> getRowWithoutNull() {
        return map.entrySet().stream()
                .filter(e -> e.getValue().getValue() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getValue()));
    }

    public Type getCopyTypeByKey(String key) {
        return map.get(key).clone();
    }

    @Override
    public String toString() {
        return map.toString();
    }

    public void update(Map<String, Type> values) {
        values.forEach((key, value) -> {
                    Type type = map.get(key);
                    if (type == null) {
                        throw new FieldNotFoundException(key);
                    }
                    map.put(key, value);
                });
    }
}
