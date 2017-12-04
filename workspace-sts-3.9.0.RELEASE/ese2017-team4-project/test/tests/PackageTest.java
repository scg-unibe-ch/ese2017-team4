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
		testPackage = new Package("Bern",5, 6, 7, 8);
	}

	@Test
	public void pendantPackageTest() {	
		assertEquals(Status.PENDENT, testPackage.getIsDelivered());
		assertEquals("pendent", testPackage.getIsStatus());
	}
	
	@Test
	public void isPlacedInTourTest() {
		testPackage.placedInTour();
		assertEquals(Status.GEPLANT, testPackage.getIsDelivered());
		assertEquals("geplant", testPackage.getIsStatus());
	}
	
	@Test
	public void setToDeliveredTest() {
		testPackage.setToDelivered();
		assertEquals( Status.ZUGESTELLT, testPackage.getIsDelivered());
		assertEquals("zugestellt", testPackage.getIsStatus());
	}
	
	@Test
	public void incrementDeliveryCounterTest() {
		assertEquals(0, testPackage.getDeliveryCounter());
		testPackage.incrementDeliveryCounter();
		assertEquals(1, testPackage.getDeliveryCounter());
	}
	
	@Test
	public void incrementNotDeliverableCounterTest() {
		assertEquals(0, testPackage.getNotDeliverableCounter());
		testPackage.incrementNotDeliverableCounter();
		assertEquals(1, testPackage.getNotDeliverableCounter());
	}
}
