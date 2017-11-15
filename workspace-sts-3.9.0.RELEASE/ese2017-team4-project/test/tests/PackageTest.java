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
	public void pendantPackageTest() {	
		assertEquals(Status.PENDANT, testPackage.getIsDelivered());
		assertEquals("pendant", testPackage.isStatus);
	}
	
	@Test
	public void isPlacedInTourTest() {
		testPackage.placedInTour();
		assertEquals(Status.GEPLANT, testPackage.getIsDelivered());
		assertEquals("geplant", testPackage.isStatus);
	}
	
	@Test
	public void setToDeliveredTest() {
		testPackage.setToDelivered();
		assertEquals( Status.ZUGESTELLT, testPackage.getIsDelivered());
		assertEquals("zugestellt", testPackage.isStatus);
	}
	
	@Test
	public void toStringTest() {
		testPackage.setId(33);
		assertEquals("33", testPackage.toString());
	}
}
