package tests;

import java.util.List;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import ese4.model.*;
import ese4.model.Package;

public class RoleTest {
	
	private Role testRole = new Role();
	
	@Test
	public void getSetRoleTest() {
		testRole.setId(10);
		testRole.setRole("Driver");
		assertEquals(10, testRole.getId());
		assertEquals("Driver", testRole.getRole());
	}

}
