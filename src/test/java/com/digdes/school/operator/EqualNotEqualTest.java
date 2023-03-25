package com.digdes.school.operator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EqualNotEqualTest extends OperatorBase {

    @ParameterizedTest
    @MethodSource
    void equal_no_equal_test(String query, List<Map<String,Object>> expected) {
        List<Map<String, Object>> actual = starter.execute(query);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> equal_no_equal_test() {
        return Stream.concat(createStreamArguments("lastName"),
                Stream.concat(createStreamArguments("id"),
                        Stream.concat(createStreamArguments("age"),
                                Stream.concat(createStreamArguments("cost"),
                                        createStreamArguments("active")
                                )
                        )
                )
        );
    }

    private static Stream<Arguments> createStreamArguments(String key) {
        Object[] tests = new Object[] {
                "",
                "TEST",
                "test",
                "es",
                "e",
                "4",
                4L,
                0L,
                -1L,
                false,
                "true",
                1.5,
                0.0,
                -1.0
        };

        Stream<Arguments> equalStream = Arrays.stream(tests)
                .map(v -> createArguments(key, v, "=", new EqualFilter()));

        Stream<Arguments> notEqualStream = Arrays.stream(tests)
                .map(v -> createArguments(key, v, "!=", new NotEqualFilter()));

        return Stream.concat(equalStream, notEqualStream);
    }

    private static Arguments createArguments(String key, Object val, String symbol, MyFilter myFilter) {
        String valQuery = String.valueOf(val);
        if (val instanceof String) {
            valQuery = "'" + val + "'";
        }
        return Arguments.of(String.format("SELECT WHERE '%s' %s %s", key, symbol, valQuery), filter(key, val, myFilter));
    }
}