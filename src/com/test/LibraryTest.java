package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import java.time.Year;

import org.junit.Test;

import com.exceptions.BookAlreadyBorrowedException;
import com.exceptions.BookNotFoundException;
import com.exceptions.DuplicateBookException;
import com.exceptions.PermissionDeniedException;
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
    
//  Add book test case
    @Test
    public void testShouldAllowOnlyPermittedUserToAddBook() {
        User user = new User("Shubh", User.Role.LIBRARIAN);

        Book book = new Book("1234", "TDD", "Incubyte", Year.of(2025));
        library.addBook(user, book);

        Book storedBook = library.getBookByISBN("1234");

        assertNotNull(storedBook);
        assertEquals(book, storedBook);
    }	

    @Test
    public void testShouldThrowExceptionIfUnauthorizedUserAddBook() {
        User user = new User("Shubh", User.Role.USER);

        Book book = new Book("1234", "TDD", "Incubyte", Year.of(2025));
        PermissionDeniedException exception = assertThrows(PermissionDeniedException.class, () -> library.addBook(user, book));
        assertEquals("You are not authorized to add book", exception.getMessage());
    }
    
    @Test
    public void testShouldNotAllowDuplicateBooks() {
        User librarian = new User("Shubh", User.Role.LIBRARIAN);
        Book book1 = new Book("1", "TDD1", "Incubyte", Year.of(2025));
        Book book2 = new Book("1", "TDD2", "Incubyte", Year.of(2025));

        library.addUser(librarian);
        library.addBook(librarian, book1);
        DuplicateBookException exception = assertThrows(DuplicateBookException.class, () -> library.addBook(librarian, book2));
        assertEquals("Duplicate Book Not Allowed", exception.getMessage()); 
    }
    
    
//  Borrow book test case
    @Test
    public void testShouldAllowToBorrowBookFromLibrary() {
        User librarian = new User("Shubh", User.Role.LIBRARIAN);
        User user = new User("Drashti", User.Role.USER);
        Book book = new Book("1234", "TDD", "Incubyte", Year.of(2025));

        library.addUser(librarian);
        library.addUser(user);
        library.addBook(librarian, book);

        library.borrowBook(user, "1234");

        Book borrowedBook = library.getBookByISBN("1234");
        assertNull(borrowedBook);
    }


	@Test
    public void testShouldThrowExceptionWhenBookNotFoundDuringBorrowRequest() {

        User user = new User("Shubh", User.Role.USER);

        library.addUser(user);

        BookNotFoundException exception = assertThrows(BookNotFoundException.class, () -> library.borrowBook(user, "1234"));
        assertEquals("Book not found", exception.getMessage());
    }

    @Test
    public void testShouldThrowExceptionWhenBookIsAlreadyBorrowed() {

        User librarian = new User("Shubh", User.Role.LIBRARIAN);
        User user1 = new User("Drashti", User.Role.USER);
        User user2 = new User("Rohan", User.Role.USER);
        Book book = new Book("1234", "TDD", "Incubyte", Year.of(2015));

        library.addUser(librarian);
        library.addUser(user1);
        library.addUser(user2);
        library.addBook(librarian, book);

        library.borrowBook(user1, "1234");

        BookAlreadyBorrowedException exception = assertThrows(BookAlreadyBorrowedException.class, () -> library.borrowBook(user2, "1234"));
        assertEquals("Book is already borrowed", exception.getMessage());
    }
    
    @Test
    public void testShouldReturnBorrowerNameWhoBorrowedBook() {
        User librarian = new User("Shubh", User.Role.LIBRARIAN);
        User user = new User("Drashti", User.Role.USER);
        Book book = new Book("1234", "TDD", "Incubyte", Year.of(2025));

        library.addUser(librarian);
        library.addUser(user);
        library.addBook(librarian, book);

        library.borrowBook(user, "1234");

        String borrowerName = library.getBorrowerNameByISBN("1234");

        assertEquals(user.getUserName(), borrowerName);
    }
}
