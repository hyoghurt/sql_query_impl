package com.digdes.school.operator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LogicalOperatorTest extends OperatorBase {

    @ParameterizedTest
    @MethodSource
    void logical_operator_test(String query, List<Map<String,Object>> expected) {
        List<Map<String, Object>> actual = starter.execute(query);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> logical_operator_test() {
        return Stream.of(
                Arguments.of(
                        "SELECT WHERE 'id' > 1 AND 'age' > -2",
                        list.stream()
                                .filter(
                                        e -> new GreaterFilter().filter(e, "id", 1L)
                                                && new GreaterFilter().filter(e, "age", -2L)
                                )
                                .collect(Collectors.toList())
                ),
                Arguments.of(
                        "SELECT WHERE 'id' > 1 OR 'age' > -2",
                        list.stream()
                                .filter(
                                        e -> new GreaterFilter().filter(e, "id", 1L)
                                                || new GreaterFilter().filter(e, "age", -2L)
                                )
                                .collect(Collectors.toList())
                ),
                Arguments.of(
                        "SELECT WHERE 'id' > 1 AND 'age' > -2 AND 'cost' > -2.1",
                        list.stream()
                                .filter(
                                        e -> new GreaterFilter().filter(e, "id", 1L)
                                                && new GreaterFilter().filter(e, "age", -2L)
                                                && new GreaterFilter().filter(e, "cost", -2.1)
                                )
                                .collect(Collectors.toList())
                ),
                Arguments.of(
                        "SELECT WHERE 'id' > 1 OR 'age' > -2 OR 'cost' > -2.1",
                        list.stream()
                                .filter(
                                        e -> new GreaterFilter().filter(e, "id", 1L)
                                                || new GreaterFilter().filter(e, "age", -2L)
                                                || new GreaterFilter().filter(e, "cost", -2.1)
                                )
                                .collect(Collectors.toList())
                ),
                Arguments.of(
                        "SELECT WHERE 'id' > 1 OR 'age' > -2 AND 'cost' > -2.1",
                        list.stream()
                                .filter(
                                        e -> new GreaterFilter().filter(e, "id", 1L)
                                                || new GreaterFilter().filter(e, "age", -2L)
                                                && new GreaterFilter().filter(e, "cost", -2.1)
                                )
                                .collect(Collectors.toList())
                ),
                Arguments.of(
                        "SELECT WHERE 'id'>1 AND 'age'>-2 OR 'cost'>-2.1",
                        list.stream()
                                .filter(
                                        e -> new GreaterFilter().filter(e, "id", 1L)
                                                && new GreaterFilter().filter(e, "age", -2L)
                                                || new GreaterFilter().filter(e, "cost", -2.1)
                                )
                                .collect(Collectors.toList())
                ),
                Arguments.of(
                        "SELECT WHERE 'id'>1 OR 'age'>-2 OR 'cost'>-2.1 OR 'active'=false",
                        list.stream()
                                .filter(
                                        e -> new GreaterFilter().filter(e, "id", 1L)
                                                || new GreaterFilter().filter(e, "age", -2L)
                                                || new GreaterFilter().filter(e, "cost", -2.1)
                                                || new EqualFilter().filter(e, "active", false)
                                )
                                .collect(Collectors.toList())
                ),
                Arguments.of(
                        "SELECT WHERE 'id'>1 AND 'age'>-2 OR 'cost'>-2.1 OR 'active'=false",
                        list.stream()
                                .filter(
                                        e -> new GreaterFilter().filter(e, "id", 1L)
                                                && new GreaterFilter().filter(e, "age", -2L)
                                                || new GreaterFilter().filter(e, "cost", -2.1)
                                                || new EqualFilter().filter(e, "active", false)
                                )
                                .collect(Collectors.toList())
                ),
                Arguments.of(
                        "SELECT WHERE 'id'>1 OR 'age'>-2 AND 'cost'>-2.1 OR 'active'=false",
                        list.stream()
                                .filter(
                                        e -> new GreaterFilter().filter(e, "id", 1L)
                                                || new GreaterFilter().filter(e, "age", -2L)
                                                && new GreaterFilter().filter(e, "cost", -2.1)
                                                || new EqualFilter().filter(e, "active", false)
                                )
                                .collect(Collectors.toList())
                ),
                Arguments.of(
                        "SELECT WHERE 'id'>1 OR 'age'>-2 OR 'cost'>-2.1 AND 'active'=false",
                        list.stream()
                                .filter(
                                        e -> new GreaterFilter().filter(e, "id", 1L)
                                                || new GreaterFilter().filter(e, "age", -2L)
                                                || new GreaterFilter().filter(e, "cost", -2.1)
                                                && new EqualFilter().filter(e, "active", false)
                                )
                                .collect(Collectors.toList())
                ),
                Arguments.of(
                        "SELECT WHERE 'id'>1 AND 'age'>-2 AND 'cost'>-2.1 OR 'active'=false",
                        list.stream()
                                .filter(
                                        e -> new GreaterFilter().filter(e, "id", 1L)
                                                && new GreaterFilter().filter(e, "age", -2L)
                                                && new GreaterFilter().filter(e, "cost", -2.1)
                                                || new EqualFilter().filter(e, "active", false)
                                )
                                .collect(Collectors.toList())
                ),
                Arguments.of(
                        "SELECT WHERE 'id'>1 AND 'age'>-2 OR 'cost'>-2.1 AND 'active'=false",
                        list.stream()
                                .filter(
                                        e -> new GreaterFilter().filter(e, "id", 1L)
                                                && new GreaterFilter().filter(e, "age", -2L)
                                                || new GreaterFilter().filter(e, "cost", -2.1)
                                                && new EqualFilter().filter(e, "active", false)
                                )
                                .collect(Collectors.toList())
                ),
                Arguments.of(
                        "SELECT WHERE 'id'>1 OR 'age'>-2 AND 'cost'>-2.1 AND 'active'=false",
                        list.stream()
                                .filter(
                                        e -> new GreaterFilter().filter(e, "id", 1L)
                                                || new GreaterFilter().filter(e, "age", -2L)
                                                && new GreaterFilter().filter(e, "cost", -2.1)
                                                && new EqualFilter().filter(e, "active", false)
                                )
                                .collect(Collectors.toList())
                )
        );
    }
}
