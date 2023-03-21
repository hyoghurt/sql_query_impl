package com.digdes.school.parser.find;

import java.util.function.BiFunction;

public interface FindBaseInterface extends BiFunction<String, Integer, int[]> {
    int skipSpace(String s, int i);
}
