package ese4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ese4.model.Package;
import ese4.model.User;
import ese4.model.UserRepository;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/user") // This means URL's start with /user (after Application path)
public class UserController {
	@Autowired // This means to get the bean called packageRepository
	           // Which is auto-generated by Spring, we will use it to handle the data
	private UserRepository userRepository;

	 @GetMapping()
	    public String get() {
	    	return "homeScreen";
	    }	
	 
    @GetMapping("/listAll")
    public String listAllUsers() {        
    	return "user/listAllUsers";
    }	
	
    @GetMapping("/addUserForm")
    public String addPackage(Model model) {
        model.addAttribute("user", new User());
        return "user/addUserForm";
    }

    @PostMapping("/addUserForm")
    public String formSubmit(@ModelAttribute User user) {
    	userRepository.save(user);
        return "user/listAllUsers";
    }
    
    @ModelAttribute("users")
    public Iterable<User> allUserssAsList() {
    	return this.userRepository.findAll();
    }
 
    
}