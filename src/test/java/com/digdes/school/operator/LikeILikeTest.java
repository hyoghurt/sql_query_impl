package com.digdes.school.operator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LikeILikeTest extends OperatorBase {

    @ParameterizedTest
    @MethodSource
    void like_ilike_test(String query, List<Map<String,Object>> expected) {
        List<Map<String, Object>> actual = starter.execute(query);
        assertEquals(expected, actual);
    }

    private static Stream<Arguments> like_ilike_test() {
        String[] startLike = new String[] {
                "test",
                "tEst",
                "TEST",
                "AD",
                "ad",
                "Est",
                "est"
        };

        //START
        Stream<Arguments> stream1 = Arrays.stream(startLike)
                .map(v -> createParamStart("lastName", "like", v))
                .map(i -> Arguments.of(i[0], filter("lastName", i[1], new LikeStartFilter())));

        Stream<Arguments> stream2 = Arrays.stream(startLike)
                .map(v -> createParamStart("lastName", "ILIKE", v))
                .map(i -> Arguments.of(i[0], filter("lastName", i[1], new ILikeStartFilter())));

        Stream<Arguments> start = Stream.concat(stream1, stream2);

        //END
        Stream<Arguments> stream3 = Arrays.stream(startLike)
                .map(v -> createParamEnd("lastName", "LIKE", v))
                .map(i -> Arguments.of(i[0], filter("lastName", i[1], new LikeEndFilter())));

        Stream<Arguments> stream4 = Arrays.stream(startLike)
                .map(v -> createParamEnd("lastName", "ILIKE", v))
                .map(i -> Arguments.of(i[0], filter("lastName", i[1], new ILikeEndFilter())));

        Stream<Arguments> startEnd = Stream.concat(start, Stream.concat(stream3, stream4));

        //CONTAINS
        Stream<Arguments> stream5 = Arrays.stream(startLike)
                .map(v -> createParamContains("lastName", "LIKE", v))
                .map(i -> Arguments.of(i[0], filter("lastName", i[1], new LikeContainsFilter())));

        Stream<Arguments> stream6 = Arrays.stream(startLike)
                .map(v -> createParamContains("lastName", "ILIKE", v))
                .map(i -> Arguments.of(i[0], filter("lastName", i[1], new ILikeContainsFilter())));

        return Stream.concat(startEnd, Stream.concat(stream5, stream6));
    }

    private static String[] createParamContains(String key, String symbol, String val) {
        return new String[] {
                String.format("SELECT WHERE '%s' %s '%%%s%%'", key, symbol, val),
                val
        };
    }

    private static String[] createParamEnd(String key, String symbol, String val) {
        return new String[] {
                String.format("SELECT WHERE '%s' %s '%%%s'", key, symbol, val),
                val
        };
    }

    private static String[] createParamStart(String key, String symbol, String val) {
        return new String[] {
                String.format("SELECT WHERE '%s' %s '%s%%'", key, symbol, val),
                val
        };
    }

}