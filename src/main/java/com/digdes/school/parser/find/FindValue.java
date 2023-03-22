package com.digdes.school.parser.find;

public class FindValue extends Find {

    @Override
    public int[] apply(String s, Integer i) {
        int start = i;
        while (i < s.length() && (s.charAt(i) != DELIMITER && s.charAt(i) != SPACE)) {
            ++i;
        }
        int end = i;
        return new int[] {start, end};
    }
}
