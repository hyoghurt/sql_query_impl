package com.digdes.school;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SelectWhereGreaterTest extends Base {

    @ParameterizedTest
    @MethodSource
    void select_test_where_greater(String query, List<Map<String,Object>> expected) {
        List<Map<String, Object>> actual = starter.execute(query);
        assertEquals(expected, actual);
    }

    private static List<Map<String, Object>> filter(String key, Object value) {
        return list.stream()
                .filter(e -> {
                    Object o = e.get(key);

                    if (o == null) {
                        return false;
                    }

                    if (o instanceof Long) {
                        if (value instanceof Double) {
                            return (long) o > (long) (double) value;
                        }

                        return (long) o > (long) value;
                    }

                    if (value instanceof Long) {
                        return (double) o > (double) (long) value;
                    }

                    return (double) o > (double) value;
                })
                .collect(Collectors.toList());
    }

    private static Stream<Arguments> select_test_where_greater() {
        return Stream.of(
                Arguments.of(
                        "SELECT WHERE 'id'>2.1",
                        filter("id", 2.1)
                ),
                Arguments.of(
                        "SELECT WHERE 'id'>4",
                        filter("id", 4L)
                ),
                Arguments.of(
                        "SELECT WHERE 'id'>0",
                        filter("id", 0L)
                ),
                Arguments.of(
                        "SELECT WHERE 'id'>234",
                        filter("id", 234L)
                ),

                //age
                Arguments.of(
                        "SELECT WHERE 'age'>2.1",
                        filter("age", 2.1)
                ),
                Arguments.of(
                        "SELECT WHERE 'age'>0",
                        filter("age", 0L)
                ),
                Arguments.of(
                        "SELECT WHERE 'age'>45",
                        filter("age", 45L)
                ),


                //cost
                Arguments.of(
                        "SELECT WHERE 'cost'>0",
                        filter("cost", 0L)
                ),
                Arguments.of(
                        "SELECT WHERE 'cost'>0.0",
                        filter("cost", 0.0)
                ),
                Arguments.of(
                        "SELECT WHERE 'cost'>34.9",
                        filter("cost", 34.9)
                ),
                Arguments.of(
                        "SELECT WHERE 'cost'>34.8",
                        filter("cost", 34.8)
                ),
                Arguments.of(
                        "SELECT WHERE 'cost'>2",
                        filter("cost", 2L)
                )
        );
    }
}