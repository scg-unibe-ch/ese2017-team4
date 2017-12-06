package ese4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping()
public class HomeController {
	
	@RequestMapping("/home")
    public String get() {
    	return "homeScreen";
    }	
	
	@RequestMapping("/")
    public String getSlash() {
    	return "homeScreen";
    }
	
}
