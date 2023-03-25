package com.digdes.school.table;

import com.digdes.school.exceptions.FieldNotFoundException;
import com.digdes.school.type.Type;

import java.util.Map;
import java.util.Objects;
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

    public void update(Map<String, Type> values) {
        values.forEach((key, value) -> {
                    if (!map.containsKey(key)) {
                        throw new FieldNotFoundException(key);
                    }
                    map.put(key, value.clone());
                });
    }

    @Override
    public String toString() {
        return map.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Row row = (Row) o;
        return map.equals(row.map);
    }

    @Override
    public int hashCode() {
        return Objects.hash(map);
    }
}
