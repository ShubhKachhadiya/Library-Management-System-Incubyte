package com.exceptions;

public class DuplicateBookException extends RuntimeException{
	public DuplicateBookException(String message) {
        super(message);
    }
}
