package com.digdes.school.parser.find;

import java.util.function.BiFunction;

public interface FindInterface extends BiFunction<String, Integer, int[]> {
    int skipSpace(String s, int i);
}
