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
	private User testUser;
	
	@Before
	public void constructor()
	{
		testPackage1 = new Package("Bern", 4,"Banane");
		testPackage2 = new Package("Zürich", 5,"Apfel");
		testTour = new Tour(packages, testUser);
	}
	
	@Test
	public void isFinishedTest()
	{
		assertFalse(testTour.getIsFinished());
		testTour.setFinished();
		assertTrue(testTour.getIsFinished());
	}
	
	@Test
	public void numberDeliveredPacksTest()
	{
		assertEquals(testTour.getDeliveredPacks(), 0);
	}
	
	@Test
	public void addPackagesToTourTest()
	{
		testTour.addPackageToTour(testPackage1);
		assertEquals(testTour.getPacks().get(0), testPackage1);
	}
	
	@Test
	public void setPackageToDeliveredTest()
	{
		testTour.addPackageToTour(testPackage2);
		testTour.setPackageToDelivered(testPackage2);
		assertEquals(testTour.getDeliveredPacks(),1);
		assertTrue(testTour.getIsFinished());
	}

}
