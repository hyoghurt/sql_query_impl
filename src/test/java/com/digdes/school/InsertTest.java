package com.digdes.school;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InsertTest {
    JavaSchoolStarter starter = new JavaSchoolStarter();


    @ParameterizedTest
    @MethodSource
    void insert_test(String query, List<Map<String,Object>> expected) {
        List<Map<String, Object>> actual = starter.execute(query);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> insert_test() {
        return Stream.of(
                Arguments.of(
                        "INSERT VALUES 'lastName'=null, 'id'=null, 'age'=null, 'cost'=3.4, 'active'=null",
                        getExpected(null, null, null, 3.4, null)
                ),
                Arguments.of(
                        "INSERT VALUES 'lastName'=null, 'id'=3, 'age'=40, 'cost'=3.4, 'active'=true",
                        getExpected(null, 3L, 40L, 3.4, true)
                ),
                Arguments.of(
                        "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'cost'=3.4, 'active'=true",
                        getExpected("keks", 3L, 40L, 3.4, true)
                ),
                Arguments.of(
                        "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'cost'=3.4",
                        getExpected("keks", 3L, 40L, 3.4, null)
                ),
                Arguments.of(
                        "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'active'=true",
                        getExpected("keks", 3L, 40L, null, true)
                ),
                Arguments.of(
                        "INSERT VALUES 'lastName'='keks', 'id'=3, 'cost'=3.4, 'active'=true",
                        getExpected("keks", 3L, null, 3.4, true)
                ),
                Arguments.of(
                        "INSERT VALUES 'lastName'='keks', 'age'=40, 'cost'=3.4, 'active'=true",
                        getExpected("keks", null, 40L, 3.4, true)
                ),
                Arguments.of(
                        "INSERT VALUES 'id'=3, 'age'=40, 'cost'=3.4, 'active'=true",
                        getExpected(null, 3L, 40L, 3.4, true)
                ),


                Arguments.of(
                        "INSERT VALUES 'lastName'=null, 'id'=3, 'age'=40, 'cost'=3.4, 'active'=true",
                        getExpected(null, 3L, 40L, 3.4, true)
                ),
                Arguments.of(
                        "INSERT VALUES 'lastName'='keks', 'id'=null, 'age'=40, 'cost'=3.4, 'active'=true",
                        getExpected("keks", null, 40L, 3.4, true)
                ),
                Arguments.of(
                        "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=null, 'cost'=3.4, 'active'=true",
                        getExpected("keks", 3L, null, 3.4, true)
                ),
                Arguments.of(
                        "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'cost'=null, 'active'=true",
                        getExpected("keks", 3L, 40L, null, true)
                ),
                Arguments.of(
                        "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'cost'=3.4, 'active'=null",
                        getExpected("keks", 3L, 40L, 3.4, null)
                ),
                Arguments.of(
                        "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'cost'=3.4, 'active'=false",
                        getExpected("keks", 3L, 40L, 3.4, false)
                ),



                Arguments.of(
                        "  Insert VAlues 'lastNAME'='keks', 'iD'=3, 'aGe'=40, 'cosT'=3.4, 'ACTIVE'=true",
                        getExpected("keks", 3L, 40L, 3.4, true)
                ),
                Arguments.of(
                        "INSERT VALUES 'lastName' = 'keks'  , 'id'  = 3  ,  'age' = 40 , 'cost' =  3.4  , 'active' = true",
                        getExpected("keks", 3L, 40L, 3.4, true)
                ),


                Arguments.of(
                        "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'cost'=.4, 'active'=true",
                        getExpected("keks", 3L, 40L, 0.4, true)
                ),
                Arguments.of(
                        "INSERT VALUES 'lastName'='keks', 'id'=3, 'age'=40, 'cost'=3., 'active'=true",
                        getExpected("keks", 3L, 40L, 3.0, true)
                )
        );
    }

    private static List<Map<String, Object>> getExpected(String lastName, Long id, Long age, Double cost, Boolean active) {
        List<Map<String, Object>> list = new ArrayList<>();
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
        if (cost != null) {
            map.put("cost", cost);
        }
        if (active != null) {
            map.put("active", active);
        }
        list.add(map);
        return list;
    }
}