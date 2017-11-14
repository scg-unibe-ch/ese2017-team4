package tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import ese4.model.Package;
import ese4.model.Status;

public class PackageTest {
	
	private Package testPackage;
	@Before
	public void constructor(){
		testPackage = new Package("ww",5, 5, 5, 5, 5);
	}

	@Test
	public void isDeliveredPackageTest() {	
		assertEquals(testPackage.getIsDelivered(), Status.PENDANT);
		testPackage.setToDelivered();
		assertEquals(testPackage.getIsDelivered(), Status.ZUGESTELLT);
	}

}
