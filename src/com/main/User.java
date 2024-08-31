package com.main;

public class User {
	
//	User Roles in the system
	public static enum Role {
        LIBRARIAN, // Librarian can add,borrow,return,check available books
        USER // User can borrow,return,check available books
    }
	
//	Instance Variables
    private String userName;
    private Role role;
}
