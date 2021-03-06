package tests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import ese4.model.Package;
import ese4.model.Status;
import ese4.model.Tour;


public class PackageTest {
	
	private Package testPackage;
	private Package testPackageDefault;
	private Tour testTour = mock(Tour.class);
	
	@Before
	public void constructor(){
		testPackage = new Package("Bern",5, 6, 7, 8);
		testPackageDefault = new Package();
		
	}
	
	@Test
	public void defaultConstructorTest() {
		assertTrue(testPackageDefault.getStatus() == Status.PENDENT);
		assertTrue(testPackageDefault.getStatusDisplay() == "pendent");
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
		testPackage.setTour(testTour);
		assertEquals("Zürich", testPackage.getAddress());
		assertTrue((9.1 > testPackage.getWeight()) && (8.9 < testPackage.getWeight()));
		assertTrue((8.1 > testPackage.getHeight()) && (7.9 < testPackage.getHeight()));
		assertTrue((5.1 > testPackage.getLength()) && (4.9 < testPackage.getLength()));
		assertTrue((6.1 > testPackage.getWidth()) && (5.9 < testPackage.getWidth()));
		assertEquals(testTour, testPackage.getTour());
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
		assertEquals("At the start the package is defined as PENDENT",Status.PENDENT, testPackage.getStatus());
		assertEquals("String representation is according to the Status pendent", "pendent", testPackage.getStatusDisplay());
	}
	
	@Test
	public void isPlacedInTourTest() {
		testPackage.placedInTour();
		assertEquals("Package is defined as geplant after placed in a Tour", Status.GEPLANT, testPackage.getStatus());
		assertEquals("Shows status as geplant in stringrepresentation","geplant" , testPackage.getStatusDisplay());
	}
	
	@Test
	public void setToDeliveredTest() {
		testPackage.setToDelivered();
		assertEquals("Delivered Package shows as zugestellt after confirmation",Status.ZUGESTELLT, testPackage.getStatus());
		assertEquals("String is according to zugestellt","zugestellt", testPackage.getStatusDisplay());
	}
	
	@Test
	public void incrementDeliveryCounterTest() {
		assertEquals("Is zero at the start", 0, testPackage.getDeliveryCounter());
		testPackage.incrementDeliveryCounter();
		assertEquals("Equals 1 after incremention", 1, testPackage.getDeliveryCounter());
	}
	
	@Test
	public void incrementNotDeliverableCounterTest() {
		assertEquals("Is zero at the start", 0, testPackage.getNotDeliverableCounter());
		testPackage.incrementNotDeliverableCounter();
		assertEquals("Is 1 after incremention", 1, testPackage.getNotDeliverableCounter());
	}
}
