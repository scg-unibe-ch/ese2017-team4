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
	public void getPackageTest() {
		assertEquals("Bern", testPackage.getAddress());
		assertTrue((5.1 > testPackage.getWeight()) && (4.9 < testPackage.getWeight()));
		assertTrue((6.1 > testPackage.getHeight()) && (5.9 < testPackage.getHeight()));
		assertTrue((7.1 > testPackage.getLength()) && (6.9 < testPackage.getLength()));
		assertTrue((8.1 > testPackage.getWidth()) && (7.9 < testPackage.getWidth()));
	}
	
	@Test
	public void setPackageTest() {
		testPackage.setAddress("Zürich");
		testPackage.setWeight(9);
		testPackage.setHeight(8);
		testPackage.setLength(5);
		testPackage.setWidth(6);
		assertEquals("Zürich", testPackage.getAddress());
		assertTrue((9.1 > testPackage.getWeight()) && (8.9 < testPackage.getWeight()));
		assertTrue((8.1 > testPackage.getHeight()) && (7.9 < testPackage.getHeight()));
		assertTrue((5.1 > testPackage.getLength()) && (4.9 < testPackage.getLength()));
		assertTrue((6.1 > testPackage.getWidth()) && (5.9 < testPackage.getWidth()));
	}
	
	@Test 
	public void setIDStatusTest() {
		testPackage.setId(7);
		testPackage.setStatus(Status.VERNICHTET);
		assertTrue(7 == testPackage.getId());
		assertTrue(Status.VERNICHTET == testPackage.getStatus());
	}
	
	@Test
	public void pendantPackageTest() {	
		assertEquals(Status.PENDENT, testPackage.getStatus());
		assertEquals("pendent", testPackage.getStatusDisplay());
	}
	
	@Test
	public void isPlacedInTourTest() {
		testPackage.placedInTour();
		assertEquals(Status.GEPLANT, testPackage.getStatus());
		assertEquals("geplant", testPackage.getStatusDisplay());
	}
	
	@Test
	public void setToDeliveredTest() {
		testPackage.setToDelivered();
		assertEquals( Status.ZUGESTELLT, testPackage.getStatus());
		assertEquals("zugestellt", testPackage.getStatusDisplay());
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
