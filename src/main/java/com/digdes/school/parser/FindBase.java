package com.digdes.school.parser;

public abstract class FindBase implements FindBaseI {
    final Character SPACE = ' ';

    @Override
    public int skipSpace(String s, int i) {
        while (i < s.length() && s.charAt(i) == SPACE) {
            ++i;
        }
        return i;
    }

    public boolean isSymbol(char c, char[] symbols) {
        for (char symbol : symbols) {
            if (symbol == c) {
                return true;
            }
        }
        return false;
    }
}
