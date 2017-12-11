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
import ese4.repository.UserRepository;

/**
 * 
 * @author ese04
 * 
 * Takes care of all requests under /user
 *
 */
@Controller
@RequestMapping(path="/user")
public class UserController {
	@Autowired
	private UserRepository userRepository;

	/**
	 * Returns the user to the home page
	 * @return the homescreen html
	 */
	@GetMapping("/home")
	public String get() {
	    return "homeScreen";
	}	
	 
	/**
	 * Lists all the users under /listAll
	 * @return the listAllUsers html
	 */
    @GetMapping("/listAll")
    public String listAllUsers() {        
    	return "user/listAllUsers";
    }	
    
    /**
     * Handles the database request to list all users
     * @return a list of all users
     */
    @ModelAttribute("users")
    public Iterable<User> allUserssAsList() {
    	return this.userRepository.findAll();
    }
 
    
}