package com.digdes.school.table;

import com.digdes.school.enums.LogicalOperator;
import com.digdes.school.type.Type;

import java.util.List;
import java.util.Map;

public interface Table {

    List<Map<String, Object>> insert(Map<String, Type> values);

    List<Map<String, Object>> update(Map<String, Type> values,
                                     List<Condition> conditions,
                                     List<LogicalOperator> logicalOperators);

    List<Map<String, Object>> select(List<Condition> conditions,
                                     List<LogicalOperator> logicalOperators);

    List<Map<String, Object>> delete(List<Condition> conditions,
                                     List<LogicalOperator> logicalOperators);
}
