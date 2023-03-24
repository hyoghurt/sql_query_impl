package com.digdes.school.table;

import com.digdes.school.type.Type;

import java.util.List;
import java.util.Map;

public interface Table {

    List<Map<String, Object>> insert(Map<String, Type> values);

    List<Map<String, Object>> update(Map<String, Type> values, List<Object> conditions);

    List<Map<String, Object>> select(List<Object> conditions);

    List<Map<String, Object>> delete(List<Object> conditions);
}
