package com.digdes.school.table;

import com.digdes.school.operator.ProducerComparisonOperator;
import com.digdes.school.operator.logical.LogicalOperator;
import com.digdes.school.type.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TableImpl implements Table {
    private final ProducerComparisonOperator producerComparisonOperator = new ProducerComparisonOperator();
    private final Converter converter = new Converter();
    private final TableFields tableFields;
    private final List<Row> rows = new ArrayList<>();

    public TableImpl(Map<String, Type> map) {
        tableFields = new TableFields(map);
    }

    @Override
    public List<Map<String, Object>> insert(Map<String, String> values) {
        Map<String, Type> valuesType = convertStringToType(values);
        Row row = new Row(tableFields.createNewMap(valuesType));
        rows.add(row);

        return Stream.of(row.getCopyRowWithoutNull())
                .collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> select(List<Condition> conditions) {
        List<Row> rows = this.rows;

        if (conditions != null) {
            prepareConditions(conditions);
            rows = this.rows.stream()
                    .filter(e -> isRowSatisfyCondition(e, conditions))
                    .collect(Collectors.toList());
        }

        return rows.stream()
                .map(Row::getCopyRowWithoutNull)
                .collect(Collectors.toList());
    }

    private Map<String, Type> convertStringToType(Map<String, String> values) {
        return values.entrySet().stream()
                .collect(
                        HashMap::new,
                        (m,v) -> m.put(v.getKey(), converter.stringToType(v.getValue(), true)),
                        HashMap::putAll);
    }

    private void prepareConditions(List<Condition> conditions) {

        //set type
        conditions.forEach(condition -> {
            Type type = converter.stringToType(condition.getValue(), false);
            condition.setType(type);
        });

        //set operator engine
        conditions.forEach(condition -> condition
                .setOperator(producerComparisonOperator.getOperator(condition.getOperatorSymbol())));

        //validate type
        conditions.forEach(condition -> {
            Type conditionType = condition.getType();
            Type fieldType = tableFields.getFieldType(condition.getKey());
            condition.getOperator().validateType(conditionType, fieldType);
        });
    }

    private boolean isRowSatisfyCondition(Row row, List<Condition> conditions) {
        Condition condition = conditions.get(0);

        String key = condition.getKey();
        Type rowType = row.getTypeByKey(tableFields.getFieldName(key));
        Type conditionType = condition.getType();

        return condition.getOperator().test(rowType, conditionType);
    }

    @Override
    public List<Map<String, Object>> update(Map<String, String> values,
                                            List<Condition> conditions,
                                            List<LogicalOperator> logicalOperators) {

        return null;
    }
}
