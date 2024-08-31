package com.main;

import static com.validator.StringValidator.validateString;

public class User {
	
//	User Roles in the system
	public static enum Role {
        LIBRARIAN, // Librarian can add,borrow,return,check available books
        USER // User can borrow,return,check available books
    }
	
//	Instance Variables
    private String userName;
    private Role role;
    

//  Constructor
    public User(String userName, Role role) {
    	validateRequiredAttributes(userName,role);
        this.userName = userName;
        this.role = role;
    }

//  Methods
    
//  To check whether the user details are NULL or not    
    private void validateRequiredAttributes(String userName, Role role) {
        validateString(userName, "UserName should not be null");
        if(role == null) {
        	throw new IllegalArgumentException("User role should not be null");
        }
    }
    
//  To retrieve the userName  
    public String getUserName() {
        return userName;
    }

//  To retrieve the userRole  
    public Role getRole() {
        return role;
    }

//  To check is user is allowed to add book in library  
    public boolean isPermittedToAddBook() {
        return role == Role.LIBRARIAN;
    }

}
