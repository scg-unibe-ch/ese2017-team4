package ese4.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ese4.model.*;
import ese4.model.Package;
import ese4.repository.PackageRepository;
import ese4.repository.TourRepository;
import ese4.repository.UserRepository;
import ese4.service.UserService;

/**
 * 
 * @author ese04
 * 
 * Takes care of all requests under /tour
 *
 */
@Controller
@RequestMapping(path="/tour")
public class TourController {
	
	@Autowired
	private TourRepository tourRepository;
	
	@Autowired
	private PackageRepository packageRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	// Returns the user to the homescreen
	@GetMapping("/home")
    public String get() {
    	return "homeScreen";
    }
	
	// Sends the user to the /makeTour site
	@GetMapping("/makeTour")
	public String makeTour() {
		return "tour/createTour";
	}
	
	// The PostMapping for /createTour which creates the tour object
	// adds all the packages to the Tour Object and sets the driver
	// and estimated Time
	@PostMapping("/createTour")
	public String createTour(@RequestParam("driverId") Integer driverId,
			@RequestParam("packageId") List<Integer> packageIds,
			@RequestParam("estimatedDeliveryTime") int estimatedDeliveryTime)
	{
		Tour tour = new Tour();
		
    		List<Package> packs = packageRepository.findByIdIn(packageIds);
    		for(Package pack : packs) {
    			pack.incrementDeliveryCounter();
    			pack.setTour(tour);
    			pack.placedInTour();
    			tour.addPackageToTour(pack);
    		}
    	
    		tour.setDriver(userRepository.findById(driverId));
    		
    		tour.setEstimatedDeliveryTime(estimatedDeliveryTime);
    	
    		tourRepository.save(tour);	
		
		return "homescreen";
	}
	
	// Sends the user to /listAll
    @GetMapping("/listAll")
    public String allTours() {    
    	return "tour/listAllTours";
    }
    
    // Displays the selected tour
    @PostMapping("/listSelectedTour")
    public String listSelectedTour(@RequestParam(value="tourId", required=false) Integer tourId,
    		Model model) {
    if (tourId == null)
    {
    		return "tour/listAllTours";
    }
    	
    	Tour tour = tourRepository.findById(tourId);
    	model.addAttribute("selectedTour", tour);
    	return "tour/selectedTour";
    }
    
    // Gets the current user
    User getCurrentUser() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByName(auth.getName());
    	return user;
    }
    
    // Sends the user to /listMyTour
    @GetMapping("/listMyTour")
    public String listMyTour() {
    	return "tour/listMyTour";
    }
    
    // Sends the user to /confirmMyTour
    @GetMapping("/confirmMyTour")
    public String listMyTourToConfirm() {
    	return "tour/confirmMyTour";
    }
    
    // Sets the statuses that the driver set on the /confirmMyTourPage
    // And finishes the Tour
    @PostMapping("/confirmMyTour")
    public String confirmMyTour(@RequestParam(value = "notDeliverablePackage", required=false) List<Integer> notDeliverablePackages, 
    		@RequestParam(value = "deliveredPackage", required=false) List<Integer> deliveredPackages) {

    	Tour myTour = myTour();
    	List<Package> packages;
    	
    	if(deliveredPackages != null) {
	    	packages = packageRepository.findByIdIn(deliveredPackages);
	    	for(Package pack : packages) {
	    		pack.setStatus(Status.ZUGESTELLT);
	    	}
    	}
    	
    	if(notDeliverablePackages != null) {
    		packages = packageRepository.findByIdIn(notDeliverablePackages);
	    	for(Package pack : packages) {
	    		pack.incrementNotDeliverableCounter();
	    	}
    	}
    	
    	for(Package pack: myTour.getPacks())
    	{
    		if(pack.getStatus() == Status.GEPLANT) {
    			if(pack.getNotDeliverableCounter() > 1)
    			{
    				pack.setStatus(Status.NICHTZUSTELLBAR);
    			}
    			else
    			{
    	   			pack.setStatus(Status.PENDENT);
    			}
    		}
    	}

    	packageRepository.save(myTour().getPacks());
    	
    	myTour.setFinished();
    	tourRepository.save(myTour);
    	return "homeScreen";
    }
      
    // Handles the request to the database getting the notDelivered Packages
    @ModelAttribute("packagesNotDelivered")
    public Iterable<Package> allPackagesAsList() {
    		return this.packageRepository.findByStatusDisplayOrStatusDisplayOrderByDeliveryCounterDesc("pendent", "zurück gesendet");
    }
    
    // Handles the requests to the database for the users
    @ModelAttribute("drivers")
    public Iterable<User> allDriversAsList() {
    	List<User> allDrivers = (List<User>) this.userRepository.findbyRole("Driver");
    	List<User> driversWithoutTour = new ArrayList<User>();
    	for(User driver: allDrivers) {
    		driversWithoutTour.add(driver);
    	}

    	for(User driver : allDrivers) {
    		for(Tour tour : driver.getTours()) {
    			if(tour.getIsFinished() == false) {
    				driversWithoutTour.remove(driver);
    			}
    			
    		}
    	}
    	return driversWithoutTour;
    }
    
    // Handles the database request for getting the tours
    @ModelAttribute("tours")
    public Iterable<Tour> allTourAsList() {
    	return this.tourRepository.findAll();
    }
    
    // Handles the database request for finding a drivers tour
    @ModelAttribute("myTour")
    public Tour myTour() {
    	return this.tourRepository.findTourByDriverNotFinished(getCurrentUser());
    }
    
    // Handles the database request for not confirmed tours
    @ModelAttribute("toursNotConfirmed")
    public Iterable<Tour> allToursAsList(){
    	return this.tourRepository.findByIsFinished(false);
    }
}