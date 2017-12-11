package ese4.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;
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
	
	/**
	 * Returns the user to the homescreen
	 * @return the homescreen html
	 */
	@GetMapping("/home")
    public String get() {
    	return "homeScreen";
    }
	
	/**
	 * Sends the user to the /makeTour site
	 * @return the createTour html
	 */
	@GetMapping("/makeTour")
	public String makeTour() {
		return "tour/createTour";
	}
	
	/**
	 * The PostMapping for /createTour which creates the tour object
	 * adds all the packages to the Tour Object and sets the driver
	 * and estimated Time
	 * @param driverId Which Driver is concerned
	 * @param packageIds the List of Packages concerned
	 * @param estimatedDeliveryTime The estimated time the tour will take for the driver
	 * @return the homescreen html
	 */
	@PostMapping("/createTour")
	public String createTour(@RequestParam(value="driverId", required=false) Integer driverId,
			@RequestParam(value="packageId", required=false) List<Integer> packageIds,
			@RequestParam(value="estimatedDeliveryTime", required=false) Integer estimatedDeliveryTime,
			Model model)
	{
		if (driverId == null || packageIds == null || estimatedDeliveryTime == null)
		{
			String error = "Es wurden nicht alle benötigten Felder ausgefüllt.";
			model.addAttribute("error", error);
			return "tour/createTour";
		}
		
		if (estimatedDeliveryTime <= 0)
		{
			String error = "Die geschätzte Lieferzeit muss grösser als 0 sein.";
			model.addAttribute("error", error);
			return "tour/createTour";
		}
		
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
	
	/**
	 * Sends the user to /listAll
	 * @return the listAllTours html
	 */
    @GetMapping("/listAll")
    public String allTours() {    
    	return "tour/listAllTours";
    }
    
    /**
     * Displays the selected tour
     * @param tourId Id of the Tour
     * @param model
     * @return the selectedTour html
     */
    @PostMapping("/listSelectedTour")
    public String listSelectedTour(@RequestParam(value="tourId", required=false) Integer tourId,
    		Model model) {
    if (tourId == null)
    {
    		String error = "Es muss eine Tour angewählt werden.";
    		model.addAttribute("error", error);
    		return "tour/listAllTours";
    }
    	
    	Tour tour = tourRepository.findById(tourId);
    	model.addAttribute("selectedTour", tour);
    	return "tour/selectedTour";
    }
    
    /**
     * Gets the current user
     * @return user
     */
    User getCurrentUser() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByName(auth.getName());
    	return user;
    }
    
    /**
     * Sends the user to /listMyTour
     * @return the listMyTour html
     */
    @GetMapping("/listMyTour")
    public String listMyTour() {
    	return "tour/listMyTour";
    }
    
    /**
     * Sends the user to /confirmMyTour
     * @return the confirmMyTour html
     */
    @GetMapping("/confirmMyTour")
    public String listMyTourToConfirm() {
    	return "tour/confirmMyTour";
    }
    
    /**
     * Sets the statuses that the driver set on the /confirmMyTourPage
     * And finishes the Tour
     * @param notDeliverablePackages
     * @param deliveredPackages
     * @return the homescreen html
     */
    @PostMapping("/confirmMyTour")
    public String confirmMyTour(HttpServletRequest requests) {
    	
    	Tour myTour = myTour();
    	List<Package> packages;
    	for(Package pack : myTour.getPacks()) {
    		String value = requests.getParameter(pack.getId().toString());
    		if(value != null) {
    			if(value.equals("delivered")) {
        			pack.setStatus(Status.ZUGESTELLT);
        		}
        		if(value.equals("notDelivered")) {
        			pack.incrementNotDeliverableCounter();
        		}
    		} else {
    			
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
    	}
  
    	packageRepository.save(myTour().getPacks());
    	
    	myTour.setFinished();
    	tourRepository.save(myTour);
    	return "homeScreen";
    }
      
    /**
     * Handles the request to the database getting the notDelivered Packages
     * @return pending or sent back packages
     */
    @ModelAttribute("packagesNotDelivered")
    public Iterable<Package> allPackagesAsList() {
    		return this.packageRepository.findByStatusDisplayOrStatusDisplayOrderByDeliveryCounterDesc("pendent", "zurück gesendet");
    }
    
    /**
     * Handles the requests to the database for the users
     * @return a List of drivers without a tour
     */
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
    
    /**
     * Handles the database request for getting the tours
     * @return a List of all Tours
     */
    @ModelAttribute("tours")
    public Iterable<Tour> allTourAsList() {
    	return this.tourRepository.findAll();
    }
    
    /**
     * Handles the database request for finding a drivers tour
     * @return a specific drivers tour
     */
    @ModelAttribute("myTour")
    public Tour myTour() {
    	return this.tourRepository.findTourByDriverNotFinished(getCurrentUser());
    }
    
    /**
     * Handles the database request for not confirmed tours
     * @return a list of unfinished tours
     */
    @ModelAttribute("toursNotConfirmed")
    public Iterable<Tour> allToursAsList(){
    	return this.tourRepository.findByIsFinished(false);
    }
}