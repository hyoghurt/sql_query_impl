package com.digdes.school.operator;

import java.util.Map;

public interface MyFilter {
    boolean filter(Map<String, Object> map, String key, Object val);
}
