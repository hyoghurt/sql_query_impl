package com.digdes.school.table;

import com.digdes.school.type.Type;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TableImpl implements Table {
    private final RowProducer rowProducer;
    private final ConditionsService conditionsService;
    private List<Row> rows;

    public TableImpl(Map<String, Type> map) {
        conditionsService = new ConditionsService();
        rowProducer = new RowProducer(map);
        rows = new ArrayList<>();
    }

    @Override
    public List<Map<String, Object>> insert(Map<String, Type> values) {
        rowProducer.validateValues(values);
        Map<String, Type> map = rowProducer.createNewMap(values);

        Row row = new Row(map);
        this.rows.add(row);
        return createAnswer(Stream.of(row).collect(Collectors.toList()));
    }

    @Override
    public List<Map<String, Object>> select(List<Object> conditions) {
        List<Row> answer = this.rows;

        if (conditions != null) {
            List<Object> prepareConditions = prepareConditions(conditions);
            answer = this.rows.stream()
                    .filter(e -> conditionsService.isMatch(e, prepareConditions))
                    .collect(Collectors.toList());
        }

        return createAnswer(answer);
    }

    @Override
    public List<Map<String, Object>> update(Map<String, Type> values, List<Object> conditions) {
        List<Row> answer = this.rows;
        rowProducer.validateValues(values);
        setTypeForNullType(values);

        if (conditions != null) {
            List<Object> prepareConditions = prepareConditions(conditions);
            answer = this.rows.stream()
                    .filter(e -> conditionsService.isMatch(e, prepareConditions))
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
            List<Object> prepareConditions = prepareConditions(conditions);
            Map<Boolean, List<Row>> collect = this.rows.stream()
                    .collect(Collectors
                            .partitioningBy(e -> conditionsService.isMatch(e, prepareConditions)));

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
                        .toMap(e -> rowProducer.getFieldName(e.getKey()),
                                Map.Entry::getValue));
    }

    private void setTypeForNullType(Map<String, Type> values) {
        values.forEach((key, value) -> {
            if (value == null) {
                values.put(key, rowProducer.getCopyFieldType(key));
            }
        });
    }

    private List<Object> prepareConditions(List<Object> conditions) {

        //validate condition type if table is empty
        if (rows.isEmpty()) {
            conditions.stream()
                    .filter(obj -> obj instanceof Condition)
                    .forEach(obj -> {
                        Condition condition = (Condition) obj;
                        Type type = rowProducer.getCopyFieldType(condition.getKey());
                        condition.isMatch(type);
                    });
        }

        return conditionsService.prepareConditions(conditions);
    }
}
