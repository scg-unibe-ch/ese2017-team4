package tests;

import java.util.List;
import java.util.*;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import ese4.model.*;
import ese4.model.Package;


public class TourTest {
	
	private List<Package> packages = new ArrayList<Package>();
	private Tour testTour;
	private Package testPackage1;
	private Package testPackage2;
	private User testUser = new User();
	
	@Before
	public void constructor() {
		testPackage1 = new Package("Bern", 4, 4, 4, 4);
		testPackage2 = new Package("Zürich", 5, 5, 5, 5);
		testTour = new Tour(packages, testUser);
	}
	
	@Test
	public void getSetTourTest() {
		testTour.setId(10);
		testTour.setEstimatedDeliveryTime(60);
		assertEquals(testUser, testTour.getDriver());
		assertEquals(10, testTour.getId());
		assertEquals(60, testTour.getEstimatedDeliveryTime());
		
	}
	
	@Test
	public void isFinishedTest() {
		assertFalse(testTour.getIsFinished());
		testTour.setFinished();
		assertTrue(testTour.getIsFinished());
	}
	
	@Test
	public void addPackagesToTourTest() {
		testTour.addPackageToTour(testPackage1);
		assertEquals(testPackage1, testTour.getPacks().get(0));
		testTour.addPackageToTour(testPackage2);
		assertEquals(testPackage2, testTour.getPacks().get(1));
	}
}
