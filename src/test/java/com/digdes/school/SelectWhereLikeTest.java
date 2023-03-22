package com.digdes.school;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SelectWhereLikeTest extends Base {

    @ParameterizedTest
    @MethodSource
    void select_test_where_like_start(String query, List<Map<String,Object>> expected) {
        List<Map<String, Object>> actual = starter.execute(query);
        assertEquals(expected, actual);
    }

    private static List<Map<String, Object>> filterStart(String val) {
        return list.stream()
                .filter(e -> {
                    if (e.get("lastName") != null) {
                        String s = (String) e.get("lastName");
                        return s.startsWith(val);
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    private static Stream<Arguments> select_test_where_like_start() {
        return Stream.of(
                Arguments.of(
                        "SELECT WHERE 'lastName' LIKE 'test%'",
                        filterStart("test")
                ),
                Arguments.of(
                        "SELECT WHERE 'lastName' LIKE 'k%'",
                        filterStart("k")
                ),
                Arguments.of(
                        "SELECT WHERE 'lastName' LIKE 'keks%'",
                        filterStart("keks")
                )
        );
    }

    @ParameterizedTest
    @MethodSource
    void select_test_where_like_end(String query, List<Map<String,Object>> expected) {
        List<Map<String, Object>> actual = starter.execute(query);
        assertEquals(expected, actual);
    }

    private static List<Map<String, Object>> filterEnd(String val) {
        return list.stream()
                .filter(e -> {
                    if (e.get("lastName") != null) {
                        String s = (String) e.get("lastName");
                        return s.endsWith(val);
                    }
                    return false;
                })
                .collect(Collectors.toList());
    }

    private static Stream<Arguments> select_test_where_like_end() {
        return Stream.of(
                Arguments.of(
                        "SELECT WHERE 'lastName' LIKE '%test'",
                        filterEnd("test")
                ),
                Arguments.of(
                        "SELECT WHERE 'lastName' LIKE '%k'",
                        filterEnd("k")
                ),
                Arguments.of(
                        "SELECT WHERE 'lastName' LIKE '%keks'",
                        filterEnd("keks")
                ),
                Arguments.of(
                        "SELECT WHERE 'lastName' LIKE '%4'",
                        filterEnd("4")
                )
        );
    }
}