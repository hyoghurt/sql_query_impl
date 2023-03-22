package com.digdes.school;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SelectWhereEqualsTest extends Base {

    @ParameterizedTest
    @MethodSource
    void select_test_where_equals(String query, List<Map<String,Object>> expected) {
        List<Map<String, Object>> actual = starter.execute(query);
        assertEquals(expected, actual);
    }

    private static List<Map<String, Object>> filter(String key, Object val) {
        return list.stream()
                .filter(e -> e.get(key) != null
                        && e.get(key).getClass() == val.getClass()
                        && e.get(key).equals(val))
                .collect(Collectors.toList());
    }

    private static Stream<Arguments> select_test_where_equals() {
        return Stream.of(

                //lastName
                Arguments.of(
                        "SELECT WHERE 'lastNAME'=''",
                        filter("lastName", "")
                ),
                Arguments.of(
                        "SELECT WHERE 'lastNAME'='ababababa'",
                        filter("lastName", "ababababa")
                ),
                Arguments.of(
                        "SELECT WHERE 'lastNAME'='ke'",
                        filter("lastName", "ke")
                ),
                Arguments.of(
                        "SELECT WHERE 'lastNAME'='keks7'",
                        filter("lastName", "keks7")
                ),
                Arguments.of(
                        "SELECT WHERE 'lastNAME'=4",
                        filter("lastName", 4L)
                ),
                Arguments.of(
                        "SELECT WHERE 'LASTNAME'=true",
                        filter("lastName", true)
                ),
                Arguments.of(
                        "SELECT WHERE 'lastName'=4.5",
                        filter("lastName", 4.5)
                ),


                //id
                Arguments.of(
                        "SELECT WHERE 'id'=4",
                        filter("id", 4L)
                ),
                Arguments.of(
                        "SELECT WHERE 'id'=349",
                        filter("id", 349L)
                ),
                Arguments.of(
                        "SELECT WHERE 'id'='lol'",
                        filter("id", "lol")
                ),
                Arguments.of(
                        "SELECT WHERE 'id'=23.2",
                        filter("id", 23.2)
                ),
                Arguments.of(
                        "SELECT WHERE 'id'=false",
                        filter("id", false)
                ),


                //cost
                Arguments.of(
                        "SELECT WHERE 'cost'=34.9",
                        filter("cost", 34.9)
                ),
                Arguments.of(
                        "SELECT WHERE 'cost'=2",
                        filter("cost", 2)
                ),
                Arguments.of(
                        "SELECT WHERE 'cost'=true",
                        filter("cost", true)
                ),
                Arguments.of(
                        "SELECT WHERE 'cost'='test'",
                        filter("cost", "test")
                ),


                //active
                Arguments.of(
                        "SELECT WHERE 'acTIve'=false",
                        filter("active", false)
                ),
                Arguments.of(
                        "SELECT WHERE 'acTIve'=true",
                        filter("active", true)
                ),
                Arguments.of(
                        "SELECT WHERE 'acTIve'='true'",
                        filter("active", "true")
                ),
                Arguments.of(
                        "SELECT WHERE 'acTIve'=2",
                        filter("active", 2L)
                ),
                Arguments.of(
                        "SELECT WHERE 'acTIve'=34.9",
                        filter("active", 34.9)
                ),


                //age
                Arguments.of(
                        "SELECT WHERE 'age'=45",
                        filter("age", 45L)
                ),
                Arguments.of(
                        "SELECT WHERE 'age'=34.9",
                        filter("age", 34.9)
                ),
                Arguments.of(
                        "SELECT WHERE 'age'=true",
                        filter("age", true)
                ),
                Arguments.of(
                        "SELECT WHERE 'age'='45'",
                        filter("age", "45")
                )
        );
    }
}