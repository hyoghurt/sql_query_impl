package com.digdes.school.table.cell;

import com.digdes.school.operator.ProducerComparisonOperator;
import com.digdes.school.table.Condition;
import com.digdes.school.operator.logical.LogicalOperator;
import com.digdes.school.table.Table;
import com.digdes.school.table.TableFields;
import com.digdes.school.type.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TableImpl implements Table {
    private final ProducerComparisonOperator producerComparisonOperator = new ProducerComparisonOperator();
    private final TableFields tableFields;
    private final List<Row> rows = new ArrayList<>();

    public TableImpl(Map<String, Type> map) {
        tableFields = new TableFields(map);
    }

    @Override
    public List<Map<String, Object>> insert(Map<String, String> values) {
        Row row = new Row(tableFields.createNewMap(values));
        rows.add(row);

        List<Map<String, Object>> list = new ArrayList<>();
        list.add(row.getCopyRowWithoutNull());

        return list;
    }

    @Override
    public List<Map<String, Object>> select(List<Condition> conditions) {
        List<Row> rows;

        if (conditions == null) {
            rows = this.rows;
        } else {
            prepareConditions(conditions);

            rows = this.rows.stream()
                    .filter(e -> isRowSatisfyCondition(e, conditions))
                    .collect(Collectors.toList());
        }

        return rows.stream()
                .map(Row::getCopyRowWithoutNull)
                .collect(Collectors.toList());
    }

    private void prepareConditions(List<Condition> conditions) {

        //set condition operator engine
        conditions.forEach(condition -> condition
                .setOperator(producerComparisonOperator.getOperator(condition.getOperatorSymbol())));

        //validate condition type
        conditions.forEach(condition -> {
            Type conditionType = condition.getType();
            Type fieldType = tableFields.getFieldType(condition.getKey());
            condition.getOperator().validateType(conditionType, fieldType);
        });
    }

    private boolean isRowSatisfyCondition(Row row, List<Condition> conditions) {
        Condition condition = conditions.get(0);

        String key = condition.getKey();
        Type rowType = row.getByKey(tableFields.getFieldName(key));
        Type conditionType = condition.getType();

        return condition.getOperator().test(rowType, conditionType);
    }

    @Override
    public List<Map<String, Object>> update(Map<String, String> values,
                                            List<Condition> conditions,
                                            List<LogicalOperator> logicalOperators) {

//        List<Row> rows = new ArrayList<>();
//
//        // prepare
//        for (Condition condition : conditions) {
//            condition.setType(getNewCell(condition.getKey(), condition.getValue()));
//        }

//        for (Row row : this.rows) {
//            if (row.isCondition(conditions, logicalOperators)) {
//                rows.add(row);
//            }
//        }

        List<Map<String, Object>> result = new ArrayList<>();

//        for (Row row : rows) {
//            row.update(createNewEntityForRow(values));
//            result.add(row.getCopyRowWithoutNull());
//        }

        return result;
    }
}
