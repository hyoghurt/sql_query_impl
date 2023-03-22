package com.digdes.school.table;

import com.digdes.school.operator.logical.LogicalOperator;

import java.util.List;
import java.util.Map;

public interface Table {

    List<Map<String, Object>> insert(Map<String, String> values);

    List<Map<String, Object>> update(Map<String, String> values,
                                     List<Condition> conditions,
                                     List<LogicalOperator> logicalOperators);

    List<Map<String, Object>> select(List<Condition> conditions);
}
