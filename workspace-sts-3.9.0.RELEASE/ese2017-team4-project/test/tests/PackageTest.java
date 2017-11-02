package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import ese4.model.Package;

public class PackageTest {
	
	private Package testPackage;
	@Before
	public void constructor(){
		testPackage = new Package("ww",5,"ww");
	}

	@Test
	public void isDeliveredPackageTest() {	
		assertFalse(testPackage.getIsDelivered());
		testPackage.setToDelivered();
		assertTrue(testPackage.getIsDelivered());
	}

}
