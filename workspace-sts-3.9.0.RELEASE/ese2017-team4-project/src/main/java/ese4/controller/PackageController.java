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

	
    @GetMapping("/home")
    public String get() {
    	return "homeScreen";
    }	
    
    @GetMapping("/addPackageForm")
    public String addPackage(Model model) {
        model.addAttribute("package", new Package());
        return "package/addPackageForm";
    }

    @PostMapping("/addPackageForm")
    public String formSubmit(@Valid Package pack, BindingResult bindingResult) {
    	if (bindingResult.hasErrors()) {
    		System.out.println("test");
    		return "package/addPackageForm";
    	}
    	
    	packageRepository.save(pack);
        return "homescreen";
    }
    
    @ModelAttribute("packages")
    public Iterable<Package> allPackagesAsList() {
    	return this.packageRepository.findAll();
    }
    
    @ModelAttribute("notDeliverablePackages")
    public Iterable<Package> notDeliverablePackagesAsList() {
    	return this.packageRepository.findByStatusDisplay("nicht zustellbar");
    }
    
    @GetMapping("/listAll")
    public String viewAllPackages() {
    	return "package/listAllPackages";
    }
    
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