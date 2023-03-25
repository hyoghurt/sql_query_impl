package com.digdes.school.table;

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
        this.rows.add(row);
        return createAnswer(Stream.of(row).collect(Collectors.toList()));
    }

    @Override
    public List<Map<String, Object>> select(List<Object> conditions) {
        List<Row> answer = this.rows;

        if (conditions != null) {
            validateConditionType(conditions);
            answer = this.rows.stream()
                    .filter(e -> LogicalOperatorService.isMatch(e, conditions))
                    .collect(Collectors.toList());
        }

        return createAnswer(answer);
    }

    @Override
    public List<Map<String, Object>> update(Map<String, Type> values, List<Object> conditions) {
        List<Row> answer = this.rows;

        if (conditions != null) {
            validateConditionType(conditions);
            answer = this.rows.stream()
                    .filter(e -> LogicalOperatorService.isMatch(e, conditions))
                    .collect(Collectors.toList());
        }

        answer.forEach(row -> row.update(values));

        return createAnswer(answer);
    }

    @Override
    public List<Map<String, Object>> delete(List<Object> conditions) {
        List<Row> answer = this.rows;
        List<Row> result = new ArrayList<>();

        if (conditions != null) {
            validateConditionType(conditions);
            Map<Boolean, List<Row>> collect = this.rows.stream()
                    .collect(Collectors
                            .partitioningBy(e -> LogicalOperatorService.isMatch(e, conditions)));

            answer = collect.get(true);
            result = collect.get(false);
        }

        this.rows = result;

        return createAnswer(answer);
    }

    private List<Map<String, Object>> createAnswer(List<Row> rows) {
        return rows.stream()
                .map(Row::getRowWithoutNull)
                .map(this::reverseFieldName)
                .collect(Collectors.toList());
    }

    private Map<String, Object> reverseFieldName(Map<String, Object> map) {
        return map.entrySet().stream()
                .collect(Collectors
                        .toMap(e -> newRowProducer.getFieldName(e.getKey()),
                                Map.Entry::getValue));
    }

    //need throw exception if table rows is empty
    private void validateConditionType(List<Object> conditions) {
        conditions.stream()
                .filter(obj -> obj instanceof Condition)
                .forEach(obj -> {
                    Condition condition = (Condition) obj;
                    Type type = newRowProducer.getCopyFieldType(condition.getKey());
                    condition.isMatch(type);
                });
    }
}
