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
	
	private TourForm tourForm;
	
	
	@GetMapping()
    public String get() {
    	return "homeScreen";
    }
	
	@GetMapping("/makeTour")
	public String makeTour(Model model) {
		//List<Integer> packageIds = new ArrayList<Integer>();
		//model.addAttribute("packageIds", packageIds );
		return "tour/driverSelection";
	}
	
	@PostMapping("/driverSelection")
	public String driverSelection(@RequestParam("driverId") Integer driverId) {
		tourForm = new TourForm();
		tourForm.setDriverId(driverId);		
		return "tour/packageSelection";
	}
	
	@PostMapping("/packageSelection")
	public String packageSelection(@RequestParam("packageId") List<Integer> packageIds) {
		tourForm.setPackIds(packageIds);
		saveTourBuild();
		return "homescreen";
	}
	
	
    @GetMapping("/listAll")
    public String allTours() {    
    	return "tour/listAllTours";
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
    public String confirmMyTour(@RequestParam("packageId") List<Integer> packageIds) {
    	//setze pakete mit den Id's als zugestellt
    	List<Package> packages = packageRepository.findByIdIn(packageIds);
    	Tour myTour = myTour();
    	for(Package pack : packages) {
    		pack.setStatus(Status.ZUGESTELLT);
    	}
    	for(Package pack: myTour.getPacks())
    	{
    		if(pack.getIsDelivered() == Status.GEPLANT) {
    			pack.setStatus(Status.PENDENT);			//falls ein packet nach bestätigen der tour noch geplant ist, wird es weder pendent gesetzt.
    		}
    	}

    	packageRepository.save(myTour().getPacks());
    	
    	//now set tour to finished
    	myTour.setFinished();
    	tourRepository.save(myTour);
    	return "homeScreen";
    }
    
	
    public void saveTourBuild() {
    	Tour tour = new Tour();
    	List<Package> packs = packageRepository.findByIdIn(tourForm.getPackIds());
    	for(Package pack : packs) {
    		pack.setTour(tour);
    		pack.placedInTour();
    		tour.addPackageToTour(pack);
    	}
    	tour.setOrder();
    	tour.setDriver(userRepository.findById(tourForm.getDriverId()));
    	tourRepository.save(tour);
    }
    
            
    @ModelAttribute("packagesNotDelivered")
    public Iterable<Package> allPackagesAsList() {
    	return this.packageRepository.findByIsStatus("pendent");
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