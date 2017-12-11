package ese4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author ese04
 * 
 * Takes care of the standard mappings when someone types / or /home
 *
 */
@Controller
@RequestMapping()
public class HomeController {
	
	/**
	 * sends the user to the homescreen
	 * @return the homescreen html
	 */
	@RequestMapping("/home")
    public String get() {
    	return "homeScreen";
    }	
	
	/**
	 * sends the user to the homescreen
	 * @return the homescreen html
	 */
	@RequestMapping("/")
    public String getSlash() {
    	return "homeScreen";
    }
	
}
