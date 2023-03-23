package com.digdes.school.operator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberOperatorTest extends OperatorBase {

    @ParameterizedTest
    @MethodSource
    void double_test(String query, List<Map<String, Object>> expected) {
        List<Map<String, Object>> actual = starter.execute(query);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource
    void long_test(String query, List<Map<String, Object>> expected) {
        List<Map<String, Object>> actual = starter.execute(query);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> double_test() {
        Stream<Arguments> stream1 = createDoubleArguments("cost");
        Stream<Arguments> stream2 = createDoubleArguments("id");
        Stream<Arguments> stream3 = createDoubleArguments("age");

        return Stream.concat(stream1, Stream.concat(stream2, stream3));
    }

    private static Stream<Arguments> long_test() {
        Stream<Arguments> stream1 = createLongArguments("cost");
        Stream<Arguments> stream2 = createLongArguments("id");
        Stream<Arguments> stream3 = createLongArguments("age");

        return Stream.concat(stream1, Stream.concat(stream2, stream3));
    }

    private static Stream<Arguments> createDoubleArguments(String key) {
        Stream<Arguments> stream1 = getDoubleArgumentsStream(key, ">", new GreaterFilter());
        Stream<Arguments> stream2 = getDoubleArgumentsStream(key, "<", new LessFilter());
        Stream<Arguments> stream3 = getDoubleArgumentsStream(key, ">=", new GreaterOrEqualFilter());
        Stream<Arguments> stream4 = getDoubleArgumentsStream(key, "<=", new LessOrEqualFilter());

        return Stream.concat(stream1, Stream.concat(stream2, Stream.concat(stream3, stream4)));
    }

    private static Stream<Arguments> createLongArguments(String key) {
        Stream<Arguments> stream1 = getLongArgumentsStream(key, ">", new GreaterFilter());
        Stream<Arguments> stream2 = getLongArgumentsStream(key, "<", new LessFilter());
        Stream<Arguments> stream3 = getLongArgumentsStream(key, ">=", new GreaterOrEqualFilter());
        Stream<Arguments> stream4 = getLongArgumentsStream(key, "<=", new LessOrEqualFilter());

        return Stream.concat(stream1, Stream.concat(stream2, Stream.concat(stream3, stream4)));
    }

    private static Stream<Arguments> getLongArgumentsStream(String key, String symbol, MyFilter myFilter) {
        return createStreamLong()
                .map(v -> createArguments(key, symbol, v, myFilter));
    }

    private static Stream<Arguments> getDoubleArgumentsStream(String key, String symbol, MyFilter myFilter) {
        return createStreamDouble()
                .map(d -> createArguments(key, symbol, getDoublePrecision(d), myFilter));
    }

    private static Arguments createArguments(String key, String symbol, Object val, MyFilter myFilter) {
        return Arguments.of(String.format("SELECT WHERE '%s' %s %s", key, symbol, val), filter(key, val, myFilter));
    }
}