package com.digdes.school.exceptions;

public class SyntaxErrorException extends RuntimeException{

    public SyntaxErrorException() {
    }

    public SyntaxErrorException(String s) {
        super(s);
    }
}
