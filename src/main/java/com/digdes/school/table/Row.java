package com.digdes.school.table;

import com.digdes.school.type.Type;

import java.util.Map;
import java.util.stream.Collectors;

public class Row {
    private final Map<String, Type> map;

    public Row(Map<String, Type> map) {
        this.map = map;
    }

    public Map<String, Object> getCopyRowWithoutNull() {
        return map.entrySet().stream()
                .filter(e -> e.getValue().getValue() != null)
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getValue()));
    }

    @Override
    public String toString() {
        return map.toString();
    }

    public Type getTypeByKey(String key) {
        return map.get(key).clone();
    }
}
