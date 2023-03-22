package com.digdes.school.parser.find;

import com.digdes.school.parser.Constants;

public abstract class Find extends Constants implements FindInterface {

    @Override
    public int skipSpace(String s, int i) {
        while (i < s.length() && s.charAt(i) == SPACE) {
            ++i;
        }
        return i;
    }
}
