package com.digdes.school.table.cell;

import com.digdes.school.Condition;
import com.digdes.school.operator.logical.LogicalOperator;
import com.digdes.school.type.Type;

import java.util.HashMap;
import java.util.List;
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
        return "" + map + '\n';
    }

    public boolean isCondition(List<Condition> conditions, List<LogicalOperator> logicalOperators) {
        if (conditions.size() == 1) {
            for (Condition condition : conditions) {
                String key = condition.getKey();
                Type cell = map.get(key);
                Type conditionCell = condition.getType();
                return condition.getOperator().test(cell.getValue(), conditionCell.getValue());
            }
        }

        return false;
    }

    public void update(Map<String, Type> map) {
        this.map.putAll(map);
    }

    public boolean isCondition(List<Condition> conditions) {
        Condition condition = conditions.get(0);

        String key = condition.getKey();
        Type cell = map.get(key);
        Type conditionCell = condition.getType();

        return condition.getOperator().test(cell.getValue(), conditionCell.getValue());
    }
}
