package com.digdes.school;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    void insert_test_1() {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        Map<String, Object> map = new HashMap<>();
        map.put("lastName", "keks");
        map.put("id", 3L);
        map.put("age", 40L);
        map.put("active", true);
        List<Map<String, Object>> list = getList(map);

        List<Map<String,Object>> result = starter
                .execute("INSERT VALUES 'lastName' = 'keks' , 'id'=3, 'age'=40, 'active'=true");

        assertEquals(list, result);
    }


    @Test
    void insert_test_2() {
        JavaSchoolStarter starter = new JavaSchoolStarter();
        Map<String, Object> map = new HashMap<>();
        map.put("lastName", "keks");
        map.put("id", 3L);
        List<Map<String, Object>> list = getList(map);

        List<Map<String,Object>> result = starter
                .execute("INSERT VALUES 'lastName' = 'keks' , 'id'=3");

        assertEquals(list, result);
    }

    private static List<Map<String, Object>> getList(Map<String, Object> map) {
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map);
        return list;
    }
}