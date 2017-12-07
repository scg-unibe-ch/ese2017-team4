package tests;

import java.util.List;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import ese4.model.*;
import ese4.model.Package;

public class UserTest {
	
	private User testUser = new User();
	
	@Test
	public void getSetUserTest() {
		testUser.setId(77);
		testUser.setName("Bob");
		testUser.setPassword("1234");
		assertEquals(77, testUser.getId());
		assertEquals("Bob", testUser.getName());
		assertEquals("1234", testUser.getPassword());
	}
	
	@Test
	public void activeUserTest() {
		assertFalse(testUser.isActive());
		testUser.setActive(true);
		assertTrue(testUser.isActive());
	}
}
