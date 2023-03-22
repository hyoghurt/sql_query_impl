package com.digdes.school;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Base {

    JavaSchoolStarter starter;
    static List<Map<String, Object>> list;

    static {
        list = new ArrayList<>();
        list.add(newMap("keks1", 3L, 40L, true, null));
        list.add(newMap("keks2", 4L, 45L, false, null));
        list.add(newMap("keks3", 4L, 45L, false, null));
        list.add(newMap("keks4", 5L, 8L, true, 34.9));
        list.add(newMap("keks5", 5L, 45L, false, 3.0));
        list.add(newMap("keks6", 5L, 46L, true, 34.9));
        list.add(newMap("keks7", 6L, 4989L, false, 2.1));
        list.add(newMap("keks7", null, null, null, null));
        list.add(newMap(null, 7L, null, null, null));
        list.add(newMap(null, null, 8L, null, null));
        list.add(newMap(null, null, null, true, null));
        list.add(newMap(null, null, null, null, 2.99));
    }

    public Base() {
        starter = new JavaSchoolStarter();
        starter.execute("INSERT VALUES 'lastName' = 'keks1', 'id'=3, 'age'=40, 'active'=true");
        starter.execute("INSERT VALUES 'lastName' = 'keks2', 'id'=4, 'age'=45, 'active'=false");
        starter.execute("INSERT VALUES 'lastName' = 'keks3', 'id'=4, 'age'=45, 'active'=false");
        starter.execute("INSERT VALUES 'lastName' = 'keks4', 'id'=5, 'age'=8, 'active'=true, 'cost'=34.9");
        starter.execute("INSERT VALUES 'lastName' = 'keks5', 'id'=5, 'age'=45, 'active'=false, 'cost'=3");
        starter.execute("INSERT VALUES 'lastName' = 'keks6', 'id'=5, 'age'=46, 'active'=true, 'cost'=34.9");
        starter.execute("INSERT VALUES 'lastName' = 'keks7', 'id'=6, 'age'=4989, 'active'=false, 'cost'=2.1");
        starter.execute("INSERT VALUES 'lastName' = 'keks7'");
        starter.execute("INSERT VALUES 'id'=7");
        starter.execute("INSERT VALUES 'age'=8");
        starter.execute("INSERT VALUES 'active'=true");
        starter.execute("INSERT VALUES 'cost'=2.99");
    }
    private static Map<String, Object> newMap(String lastName, Long id, Long age, Boolean active, Double cost) {
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