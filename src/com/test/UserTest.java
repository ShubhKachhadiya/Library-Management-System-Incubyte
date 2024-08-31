package com.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

import com.main.User;

public class UserTest {
	
	@Test
	public void testCreateUser() {
		User user = new User("Shubh",User.Role.LIBRARIAN);
	}
	
	@Test
    public void testShouldThrowExceptionWhenUserNameisNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new User(null,User.Role.LIBRARIAN));
        assertEquals("UserName should not be null", exception.getMessage());
    }
	
	@Test
    public void testShouldThrowExceptionWhenUserRoleisNotFound() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new User("Shubh",null));
        assertEquals("User role should not be null", exception.getMessage());
    }
}
