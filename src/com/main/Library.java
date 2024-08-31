package com.main;
import java.util.Map;
import java.util.HashMap;
import com.exceptions.UserExistsException;

import static com.validator.UserValidator.validateUser;
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

}