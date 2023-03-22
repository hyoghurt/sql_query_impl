package com.digdes.school.table.cell;

import com.digdes.school.type.Type;

import java.util.HashMap;
import java.util.Map;

public class Row {
    private final Map<String, Type> map;

    public Row(Map<String, Type> map) {
        this.map = map;
    }

    public Map<String, Object> getCopyRowWithoutNull() {
        Map<String, Object> result = new HashMap<>();

        for (Map.Entry<String, Type> entry : map.entrySet()) {
            if (entry.getValue().getValue() != null) {
                result.put(entry.getKey(), entry.getValue().getValue());
            }
        }

        return result;
    }

    @Override
    public String toString() {
        return map.toString();
    }

    public void update(Map<String, Type> map) {
        this.map.putAll(map);
    }

    public Type getByKey(String key) {
        return map.get(key).clone();
    }
}
