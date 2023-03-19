package com.digdes.school.table.column;

import com.digdes.school.exceptions.TypeErrorException;
import com.digdes.school.table.column.Column;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Row {
    private final Map<String, Column<?>> map;

    public Row(Map<String, String> values, Map<String, Column<?>> fields) {
        this.map = fields.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));

        values.forEach((key, value) -> {
            try {

                Column<?> column = map.get(key);
                column.setValue(value);
            } catch (TypeErrorException e) {
                throw new TypeErrorException(String.format("'%s'=%s , %s", key, value, e.getMessage()));
            }
        });
    }

    public Map<String, Object> getR() {
        Map<String, Object> result = new HashMap<>();

        for (Map.Entry<String, Column<?>> col : map.entrySet()) {
            result.put(col.getKey(), col.getValue().getValue());
        }


        return result;
//        return map.entrySet().stream()
//                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getValue()));
    }

    @Override
    public String toString() {
        return "" + map + '\n';
    }
}
