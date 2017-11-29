package ese4.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ese4.model.*;
import ese4.model.Package;
import ese4.repository.PackageRepository;
import ese4.repository.TourRepository;
import ese4.repository.UserRepository;
import ese4.service.UserService;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/tour") // This means URL's start with /demo (after Application path)
public class TourController {
	@Autowired // This means to get the bean called packageRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private TourRepository tourRepository;
	
	@Autowired
	private PackageRepository packageRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping()
    public String get() {
    	return "homeScreen";
    }
	
	@GetMapping("/makeTour")
	public String makeTour() {
		return "tour/createTour";
	}
	
	
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
	
	
    @GetMapping("/listAll")
    public String allTours() {    
    	return "tour/listAllTours";
    }
    
    @PostMapping("/listSelectedTour")
    public String listSelectedTour(@RequestParam("tourId") Integer tourId, Model model) {
    	Tour tour = tourRepository.findById(tourId);
    	model.addAttribute("selectedTour", tour);
    	return "tour/selectedTour";
    }
    
    User getCurrentUser() {
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByName(auth.getName());
    	return user;
    }
    
    /*
     * zeige tour für den momentan eingeloggten fahrer an
     */
    @GetMapping("/listMyTour")
    public String listMyTour() {
    	
    	return "tour/listMyTour";
    }
    
    @GetMapping("/confirmMyTour")
    public String listMyTourToConfirm() {
    	return "tour/confirmMyTour";
    }
    
    /*
     * wir bekommen liste aller tour id's wo der fahrer als zugestellt angekreuzt hat
     */
    @PostMapping("/confirmMyTour")
    public String confirmMyTour(@RequestParam(value = "notDeliverablePackage", required=false) List<Integer> notDeliverablePackages, 
    		@RequestParam(value = "deliveredPackage", required=false) List<Integer> deliveredPackages) {
    	//setze pakete mit den Id's als zugestellt
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
    		if(pack.getIsDelivered() == Status.GEPLANT) {
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
    	
    	//now set tour to finished
    	myTour.setFinished();
    	tourRepository.save(myTour);
    	return "homeScreen";
    }
       
    @ModelAttribute("packagesNotDelivered")
    public Iterable<Package> allPackagesAsList() {
    		return this.packageRepository.findByIsStatusOrIsStatusOrderByDeliveryCounterDesc("pendent", "zurückSenden");
    }
    
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
    
    @ModelAttribute("tours")
    public Iterable<Tour> allTourAsList() {
    	return this.tourRepository.findAll();
    }
    
    @ModelAttribute("myTour")
    public Tour myTour() {
    	return this.tourRepository.findTourByDriverNotFinished(getCurrentUser());
    }
    
    @ModelAttribute("toursNotConfirmed")
    public Iterable<Tour> allToursAsList(){
    	return this.tourRepository.findByIsFinished(false);
    }
}