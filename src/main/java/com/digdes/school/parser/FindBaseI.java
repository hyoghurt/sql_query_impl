package com.digdes.school.parser;

import java.util.function.BiFunction;

public interface FindBaseI extends BiFunction<String, Integer, int[]> {
    int skipSpace(String s, int i);
}
