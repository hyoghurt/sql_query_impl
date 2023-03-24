package com.digdes.school.table;

import com.digdes.school.enums.LogicalOperator;
import com.digdes.school.type.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TableImpl implements Table {
    private final NewRowProducer newRowProducer;
    private List<Row> rows = new ArrayList<>();

    public TableImpl(Map<String, Type> map) {
        newRowProducer = new NewRowProducer(map);
    }

    @Override
    public List<Map<String, Object>> insert(Map<String, Type> values) {
        Row row = new Row(newRowProducer.createNewMap(values));
        rows.add(row);

        return Stream.of(row.getRowWithoutNull())
                .map(this::reverseFieldName)
                .collect(Collectors.toList());
    }

    public Map<String, Object> reverseFieldName(Map<String, Object> map) {
        return map.entrySet().stream()
                        .collect(Collectors
                                .toMap(e -> newRowProducer.getFieldName(e.getKey()),
                                        Map.Entry::getValue));
    }

    @Override
    public List<Map<String, Object>> select(List<Condition> conditions, List<LogicalOperator> operators) {
        List<Row> answer = this.rows;

        if (conditions != null) {
            validateConditionType(conditions);
            answer = this.rows.stream()
                    .filter(e -> LogicalOperatorService.isMatch(e, conditions, operators))
                    .collect(Collectors.toList());
        }

        return answer.stream()
                .map(Row::getRowWithoutNull)
                .map(this::reverseFieldName)
                .collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> update(Map<String, Type> values,
                                            List<Condition> conditions,
                                            List<LogicalOperator> operators) {
        List<Row> answer = this.rows;

        if (conditions != null) {
            validateConditionType(conditions);
            answer = this.rows.stream()
                    .filter(e -> LogicalOperatorService.isMatch(e, conditions, operators))
                    .collect(Collectors.toList());
        }

        answer.forEach(row -> row.update(values));

        return answer.stream()
                .map(Row::getRowWithoutNull)
                .map(this::reverseFieldName)
                .collect(Collectors.toList());
    }

    @Override
    public List<Map<String, Object>> delete(List<Condition> conditions, List<LogicalOperator> operators) {
        List<Row> answer = this.rows;
        List<Row> result = new ArrayList<>();

        if (conditions != null) {
            validateConditionType(conditions);

            Map<Boolean, List<Row>> collect = this.rows.stream()
                    .collect(Collectors
                            .partitioningBy(e -> LogicalOperatorService.isMatch(e, conditions, operators)));

            answer = collect.get(true);
            result = collect.get(false);
        }

        this.rows = result;

        return answer.stream()
                .map(Row::getRowWithoutNull)
                .map(this::reverseFieldName)
                .collect(Collectors.toList());
    }

    private void validateConditionType(List<Condition> conditions) {
        conditions.forEach(condition -> {
            Type type = newRowProducer.getCopyFieldType(condition.getKey());
            condition.validateType(type);
        });
    }
}
