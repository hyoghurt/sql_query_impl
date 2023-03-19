package com.digdes.school.parser;

public class FindValue extends FindBase {
    private final char[] END_SYMBOLS = new char[] {',', ' '};

    @Override
    public int[] apply(String s, Integer i) {
        int start = i;
        while (i < s.length() && !isSymbol(s.charAt(i), END_SYMBOLS)) {
            ++i;
        }
        int end = i;
        return new int[] {start, end};
    }
}