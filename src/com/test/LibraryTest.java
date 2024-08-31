package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

import java.time.Year;

import org.junit.Test;

import com.exceptions.UserExistsException;
import com.main.*;

public class LibraryTest {

//	Library test cases
    @Test
	public void testCreateUser() {
    	Library library = new Library("Placement");
	}

    Library library = new Library("Placement");
    @Test
    public void testLibraryNameShouldNotbeNull() {
        assertThrows(IllegalArgumentException.class, () -> new Library(null));
    }

//  User not null test case
    @Test
    public void testShouldThrowExceptionIfUserIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> library.addUser(null));
        assertEquals("User should not be null", exception.getMessage());
    }
    
//  Add user test case   
    @Test
    public void testShouldAddUserToLibrary() {
        User librarian = new User("Shubh", User.Role.LIBRARIAN);

        library.addUser(librarian);

        User user = library.getUserByName("Shubh");
        assertEquals(librarian, user);
    }

    @Test
    public void testShouldNotAllowDuplicateUsers() {
        User primaryLibrarian = new User("Shubh", User.Role.LIBRARIAN);
        User secondaryLibrarian = new User("Shubh", User.Role.LIBRARIAN);

        library.addUser(primaryLibrarian);
        UserExistsException exception = assertThrows(UserExistsException.class, () -> library.addUser(secondaryLibrarian));
        assertEquals("User already exists in catalog", exception.getMessage());
    }
    
    @Test
    public void testShouldFetchUserByUsername() {
        User primaryLibrarian = new User("Shubh", User.Role.LIBRARIAN);

        library.addUser(primaryLibrarian);
        User fetchedUser = library.getUserByName("Shubh");
        assertEquals(primaryLibrarian, fetchedUser);
    }
}
