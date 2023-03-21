package com.digdes.school.parser.find;

public class FindString extends FindBase {
    private final Character STRING_WRAPPER = '\'';

    @Override
    public int[] apply(String s, Integer i) {
        int start = i;
        if (i >= s.length() || s.charAt(i) != STRING_WRAPPER) {
            return new int[]{i, i};
        }

        int end = s.indexOf(STRING_WRAPPER, start + 1);
        if (end == -1) {
            return new int[]{i, i};
        }

        return new int[]{start, end + 1};
    }
}
