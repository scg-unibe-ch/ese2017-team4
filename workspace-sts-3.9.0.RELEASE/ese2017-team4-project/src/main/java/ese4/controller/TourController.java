package ese4.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@GetMapping("/confirm")
	public String unconfirmedTours() {
		return "tour/confirmTours";
	}
	
	@PostMapping("/confirm")
	public String confirmTours(@RequestParam("tourId") List<Integer> tourIds) {
		confirmation(tourIds);
		return "homescreen";
	}
	
    @GetMapping("/listAll")
    public String allTours() {        
    	return "tour/listAllTours";
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
    
    public void confirmation(List<Integer> tourIds) {
    	List<Tour> tours = tourRepository.findByIdIn(tourIds);
    	for(Tour tour : tours) {
    		tour.setFinished();
    		tourRepository.save(tour);
    	}    	
    }
        
    @ModelAttribute("packagesNotDelivered")
    public Iterable<Package> allPackagesAsList() {
    	return this.packageRepository.findByIsStatus("pendant");
    }
    
    @ModelAttribute("drivers")
    public Iterable<User> allDriversAsList() {
    	return this.userRepository.findByRoles(null);	//TODO: change
    }
    
    @ModelAttribute("tours")
    public Iterable<Tour> allTourAsList() {
    	return this.tourRepository.findAll();
    }
    
    @ModelAttribute("toursNotConfirmed")
    public Iterable<Tour> allToursAsList(){
    	return this.tourRepository.findByIsFinished(false);
    }
}