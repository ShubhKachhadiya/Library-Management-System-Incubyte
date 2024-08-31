package com.main;
import java.util.Map;
import java.util.HashMap;

import com.exceptions.BookNotFoundException;
import com.exceptions.DuplicateBookException;
import com.exceptions.PermissionDeniedException;
import com.exceptions.UserExistsException;

import static com.validator.UserValidator.validateUser;
import static com.validator.BookValidator.validateBookNotNull;
import static com.validator.StringValidator.validateString;

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
    
//  Constructor  
    public Library(String name) {
        validateString(name, "Library Name Should not be null");
        
        this.name = name;
        this.bookInventory = new HashMap<String, Book>();
        this.userCatalog = new HashMap<String, User>();
        this.borrowedBooks = new HashMap<String, String>();
        this.borrowedBookDetails = new HashMap<String, Book>();
    }

//  To add user in system  
    public void addUser(User user) throws UserExistsException {
        validateUser(user, "User should not be null");
        if(userCatalog.containsKey(user.getUserName())){
            throw new UserExistsException("User already exists in catalog");
        }
        userCatalog.put(user.getUserName(), user);
    }

//  To retrieve User object from userName  
    public User getUserByName(String userName) {
        return userCatalog.get(userName);
    }
    
//  To add book in system by librarian  
    public void addBook(User user, Book book) throws BookNotFoundException, PermissionDeniedException {
        validateUser(user, "User should not be null");
        validateBookNotNull(book,"Book not found");
        if(user.isPermittedToAddBook()){
        	if(!bookInventory.containsKey(book.getIsbn()))
            {
        		bookInventory.put(book.getIsbn(), book);
            }
        	else {
        		throw new DuplicateBookException("Duplicate Book Not Allowed");
        	}
        } else {
            throw new PermissionDeniedException("You are not authorized to add book");
        }
    }
    
    
    
    
//  To retrieve book object from inventory using isbn  
    public Book getBookByISBN(String isbn) {
        return bookInventory.get(isbn);
    }
}