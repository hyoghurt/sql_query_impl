package com.digdes.school;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SelectTest {

    JavaSchoolStarter starter;
    List<Map<String, Object>> list;

    @BeforeEach
    void init() {
        starter = new JavaSchoolStarter();
        starter.execute("INSERT VALUES 'lastName' = 'keks1', 'id'=3, 'age'=40, 'active'=true");
        starter.execute("INSERT VALUES 'lastName' = 'keks2', 'id'=4, 'age'=45, 'active'=false");
        starter.execute("INSERT VALUES 'lastName' = 'keks3', 'id'=4, 'age'=45, 'active'=false");
        starter.execute("INSERT VALUES 'lastName' = 'keks4', 'id'=5, 'age'=8, 'active'=true");
        starter.execute("INSERT VALUES 'lastName' = 'keks5', 'id'=5, 'age'=45, 'active'=false");
        starter.execute("INSERT VALUES 'lastName' = 'keks6', 'id'=5, 'age'=46, 'active'=true, 'cost'=34.9");
        starter.execute("INSERT VALUES 'lastName' = 'keks7', 'id'=6, 'age'=4989, 'active'=false, 'cost'=2.1");

        list = new ArrayList<>();
        list.add(newMap("keks1", 3L, 40L, true, null));
        list.add(newMap("keks2", 4L, 45L, false, null));
        list.add(newMap("keks3", 4L, 45L, false, null));
        list.add(newMap("keks4", 5L, 8L, true, null));
        list.add(newMap("keks5", 5L, 45L, false, null));
        list.add(newMap("keks6", 5L, 46L, true, 34.9));
        list.add(newMap("keks7", 6L, 4989L, false, 2.1));
    }


    @Test
    void select_test_all() {
        List<Map<String, Object>> result = starter.execute("SELECT");
        assertEquals(result.size(), 7);
    }

    @Test
    void select_test_where_id() {
        List<Map<String, Object>> expected = list.stream()
                .filter(e -> e.get("id").equals(3L))
                .collect(Collectors.toList());

        List<Map<String, Object>> actual = starter.execute("SELECT WHERE 'id'=3");

        assertEquals(expected, actual);
    }

    @Test
    void select_test_where_age() {
        List<Map<String, Object>> expected = list.stream()
                .filter(e -> e.get("age").equals(45L))
                .collect(Collectors.toList());

        List<Map<String, Object>> actual = starter.execute("SELECT WHERE 'age'=45");
        assertEquals(expected, actual);
    }

    @Test
    void select_test_where_active() {
        List<Map<String, Object>> expected = list.stream()
                .filter(e -> e.get("active").equals(false))
                .collect(Collectors.toList());

        List<Map<String, Object>> actual = starter.execute("SELECT WHERE 'active'=false");
        assertEquals(expected, actual);
    }

    @Test
    void select_test_where_active_upper() {
        List<Map<String, Object>> expected = list.stream()
                .filter(e -> e.get("active").equals(false))
                .collect(Collectors.toList());

        List<Map<String, Object>> actual = starter.execute("SELECT WHERE 'acTIve'=false");
        assertEquals(expected, actual);
    }

    @Test
    void select_test_where_cost() {
        List<Map<String, Object>> expected = list.stream()
                .filter(e -> e.get("cost") != null && e.get("cost").equals(34.9))
                .collect(Collectors.toList());

        List<Map<String, Object>> actual = starter.execute("SELECT WHERE 'cost'=34.9");
        assertEquals(expected, actual);
    }

    @Test
    void select_test_where_id_not_found() {
        List<Map<String, Object>> expected = list.stream()
                .filter(e -> e.get("id").equals(349L))
                .collect(Collectors.toList());

        List<Map<String, Object>> actual = starter.execute("SELECT WHERE 'id'=349");
        assertEquals(expected, actual);
    }

    private static List<Map<String, Object>> getList(Map<String, Object> map) {
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(map);
        return list;
    }

    private Map<String, Object> newMap(String lastName, Long id, Long age, Boolean active, Double cost) {
        Map<String, Object> map = new HashMap<>();

        if (lastName != null) {
            map.put("lastName", lastName);
        }
        if (id != null) {
            map.put("id", id);
        }
        if (age != null) {
            map.put("age", age);
        }
        if (active != null) {
            map.put("active", active);
        }
        if (cost != null) {
            map.put("cost", cost);
        }

        return map;
    }
}