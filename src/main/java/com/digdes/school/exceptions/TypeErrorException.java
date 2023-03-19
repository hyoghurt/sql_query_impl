package com.digdes.school.exceptions;

public class TypeErrorException extends RuntimeException{

    public TypeErrorException() {
    }

    public TypeErrorException(String s) {
        super(s);
    }
}
