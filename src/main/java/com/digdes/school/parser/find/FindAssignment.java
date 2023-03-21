package com.digdes.school.parser.find;

public class FindAssignment extends FindBase {
    private final char ASSIGNMENT_SYMBOL = '=';


    @Override
    public int[] apply(String s, Integer i) {
        if (i >= s.length() || s.charAt(i) != ASSIGNMENT_SYMBOL) {
            return new int[] {i, i};
        }

        return new int[] {i, i + 1};
    }
}
