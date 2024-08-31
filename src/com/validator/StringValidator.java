package com.validator;

public class StringValidator {
	public static void validateString(String value, String message) {
        if(value == null){
            throw new IllegalArgumentException(message);
        }
    }
}
