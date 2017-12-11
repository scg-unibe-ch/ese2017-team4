package ese4.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ese4.model.Package;
import ese4.model.PackageAudited;
import ese4.model.Status;
import ese4.repository.PackageRepository;
import ese4.repository.PackageAuditedRepository;

/**
 * 
 * @author ese04
 * Takes care of all requests under /package
 *
 */
@Controller
@RequestMapping(path="/package")
public class PackageController {
	
	@Autowired
	private PackageRepository packageRepository;
	
	@Autowired
	private PackageAuditedRepository packageAuditedRepository;
	
	int packageId;

	// Returns the user to the home screen
    @GetMapping("/home")
    public String get() {
    	return "homeScreen";
    }	
    
    // The Get Mapping for the addPackageForm which creates a new Package Object
    // so we can work with it in the HTML and in the input Validation
    @GetMapping("/addPackageForm")
    public String addPackage(Model model) {
        model.addAttribute("package", new Package());
        return "package/addPackageForm";
    }

    // Handles the Post Mapping of the addPackageForm where there are two cases
    // either the input is valid and we save the package or we reload the page with an
    // error message
    @PostMapping("/addPackageForm")
    public String formSubmit(@Valid Package pack, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
    		System.out.println("test");
    		return "package/addPackageForm";
    	}
    	
    	packageRepository.save(pack);
        return "homescreen";
    }
    
    // Used for listAllPAckages, gets all the packages
    @ModelAttribute("packages")
    public Iterable<Package> allPackagesAsList() {
    	return this.packageRepository.findAll();
    }
    
    // Gets all the not deliverable packages
    @ModelAttribute("notDeliverablePackages")
    public Iterable<Package> notDeliverablePackagesAsList() {
    	return this.packageRepository.findByStatusDisplay("nicht zustellbar");
    }
    
    // The Get Mapping for the listAll link which displays all the packages
    @GetMapping("/listAll")
    public String viewAllPackages() {
    	return "package/listAllPackages";
    }
    
    // lists either all the packages or all the packages with a selected status
    @PostMapping("/listAll")
    public String viewPackagesByStatus(@RequestParam("status") String status, Model model) {
    		
    	Iterable <Package> packages;
    	
    		switch (status) {
         case "alle":  packages = this.packageRepository.findAll();
                  break;
         default: packages = this.packageRepository.findByStatusDisplay(status);;
                  break;
     } 
  
    		model.addAttribute("packages", packages);
    		model.addAttribute("status", status);

    		return "package/listAllPackages";
    } 
    
    // The Post Mapping for listSelectedPackage which either redirects to the history
    // of a packet or to the /editPackage site
    @PostMapping("/listSelectedPackage")
    public String selectedPackage(@RequestParam(value = "historyPackageId", required=false) Integer historyPackageId,
    		@RequestParam(value = "editPackageId", required=false) Integer editPackageId, Model model) {
    		
    		if (historyPackageId != null)
    		{
    			Iterable<PackageAudited> specificPackageHistory = this.packageAuditedRepository.findByIdOrderByRevDesc(historyPackageId);
        	  	model.addAttribute("specificPackageHistory", specificPackageHistory);
        	  	return "/package/listSelectedPackage";
    		}
    		else
    		{
    			if (editPackageId != null)
    			{
    			Package editPackage;
    			editPackage = this.packageRepository.findById(editPackageId).get(0);
    			model.addAttribute("editPackage", editPackage);
    	    	  	return "/package/editPackage";
    			}
    			else
    			{
    				return "package/listAllPackages";
    			}
    		}
    }
    
    // The PostMapping for /editPackage which either deletes the packages 
    // or saves the new information that was inserted
    @PostMapping("/editPackage")
    public String editPackage(@RequestParam(value = "newAddress", required = false) String newAddress,
    		@RequestParam("newWeight") double newWeight, @RequestParam("newLength") double newLength,
    		@RequestParam("newHeight") double newHeight, @RequestParam("newWidth") double newWidth,
    		@RequestParam(value="delete", required=false) Integer delete, @RequestParam("packageId") Integer packageId){
    		
    		Package editPackage = this.packageRepository.findById(packageId).get(0);
    	
    		if (delete != null)
    		{
    			packageRepository.delete(editPackage);
    		}
    		else
    		{
    			editPackage.setAddress(newAddress);
    			editPackage.setWeight(newWeight);
           	editPackage.setLength(newLength);
           	editPackage.setHeight(newHeight);
            	editPackage.setWidth(newWidth);
        		
        		packageRepository.save(editPackage);
    		}
    	
    	  	return "homescreen";
    }
    
    @GetMapping("/manageNotDeliverablePackages")
    public String viewNotDeliverablePackages() {
    	return "package/manageNotDeliverablePackages";
    }
    
    // The PostMapping for not /manageNotDeliverablePackages which displays all the
    // packages that have a "not delivered" (for whatever reason) status
    @PostMapping("/manageNotDeliverablePackages")
    public String manageNotDeliverablePackages(@RequestParam(value="action", required=false) List<String> action,
    		@RequestParam(value="newAddress", required=false) List<String> newAddresses)
    {
    		List<Package> notDeliverablePackages = this.packageRepository.findByStatusDisplay("nicht zustellbar");
    		
    		for (int counter = 0; counter < notDeliverablePackages.size(); counter++)
    		{
    			Status status = null;
    			String newStatus = action.get(counter);
    			String newAddress = newAddresses.get(counter);
    			
    			if (newAddress != "")
    			{
    				notDeliverablePackages.get(counter).setAddress(newAddress);
    			}
    			

    			switch (newStatus)
    			{
    			case "nicht zustellbar":
    				status = Status.NICHTZUSTELLBAR;
    				break;
    				
    			case "zurück gesendet":
    				status = Status.ZURÜCKSENDEN;
    				break;
    				
    			case "pendent":
    				status = Status.PENDENT;
    				break;
    				
    			case "vernichtet":
    				status = Status.VERNICHTET;
    				break;
    			}

    			notDeliverablePackages.get(counter).setStatus(status);
    		}
    		
    		packageRepository.save(notDeliverablePackages);
    		
    		return "homeScreen";
    }
}