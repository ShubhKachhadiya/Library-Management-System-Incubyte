package com.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.time.Year;
import org.junit.Test;
import com.main.Book;

public class BookTest {
	
	@Test
	public void testCreateBook() {
		Book book = new Book("1234", "TDD", "Incubyte", Year.of(2025));
	}
	
    @Test
    public void testShouldThrowExceptionWhenISBNisNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Book(null, "TDD", "Incubyte", Year.of(2025)));
        assertEquals("ISBN should not be null", exception.getMessage());
    }

    @Test
    public void testShouldThrowExceptionWhenTitleIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Book("1234", null, "Incubyte", Year.of(2025)));
        assertEquals("title should not be null", exception.getMessage());
    }

    @Test
    public void testShouldThrowExceptionWhenAuthorIsEmpty() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Book("1234", "TDD", null, Year.of(2025)));
        assertEquals("author should not be null", exception.getMessage());
    }

    @Test
    public void testShouldThrowExceptionWhenPublicationYearIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Book("1234", "TDD", "Incubyte", null));
        assertEquals("publication year should not be null", exception.getMessage());
    }
}
