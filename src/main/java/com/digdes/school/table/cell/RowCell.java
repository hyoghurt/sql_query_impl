package com.digdes.school.table.cell;

import com.digdes.school.exceptions.TypeErrorException;
import com.digdes.school.table.cell.Cell;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class RowCell {
    private final Map<String, Cell> map;

    public RowCell(Map<String, String> values, Map<String, Cell> fields) {
        this.map = fields.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));

        values.forEach((key, value) -> {
            try {
                Cell cell = map.get(key);
                cell.setValue(value);
            } catch (TypeErrorException e) {
                throw new TypeErrorException(String.format("'%s'=%s , %s", key, value, e.getMessage()));
            }
        });
    }

    public Map<String, Object> getR() {
        Map<String, Object> result = new HashMap<>();

        for (Map.Entry<String, Cell> entry : map.entrySet()) {
            result.put(entry.getKey(), entry.getValue().getValue());
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
