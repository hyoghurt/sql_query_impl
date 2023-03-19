package com.digdes.school.exceptions;

public class FieldNotFoundException extends RuntimeException{
    public FieldNotFoundException() {
    }

    public FieldNotFoundException(String s) {
        super(s);
    }
}
