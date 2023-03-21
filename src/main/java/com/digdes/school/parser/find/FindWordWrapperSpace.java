package com.digdes.school.parser.find;

public class FindWordWrapperSpace extends FindBase {

    @Override
    public int[] apply(String s, Integer i) {
        if (i >= s.length()) {
            return new int[] {i, i};
        }

        int start = i;
        int end = s.indexOf(SPACE, start);

        if (end == -1) {
            return new int[] {i, s.length()};
        }

        return new int[] {start, end};
    }
}
