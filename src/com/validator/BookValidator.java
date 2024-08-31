package com.validator;

import com.exceptions.BookNotFoundException;
import com.main.Book;

public class BookValidator {
	 public static void validateBookNotNull(Book book, String message) throws BookNotFoundException {
	        if (book == null) {
	            throw new BookNotFoundException(message);
	        }
	 }
}
