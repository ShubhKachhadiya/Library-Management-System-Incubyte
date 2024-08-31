package com.main;
import java.util.Map;
import java.util.HashMap;
import com.exceptions.UserExistsException;
import com.exceptions.BookNotFoundException;
import com.exceptions.DuplicateBookException;
import com.exceptions.PermissionDeniedException;
import com.exceptions.BookAlreadyBorrowedException;

import static com.validator.UserValidator.validateUser;
import static com.validator.StringValidator.validateString;
import static com.validator.BookValidator.validateBookNotNull;

public class Library {

//	Instance variables
    String name;
//  isbn,book
    private final Map<String, Book> bookInventory;
//  username,user  
    private final Map<String, User> userCatalog;
//  isbn,username  
    private final Map<String, String> borrowedBooks;
//  isbn,book  
    private final Map<String, Book> borrowedBookDetails;
    
}