package ese4.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import ese4.model.User;
import ese4.service.UserService;

/**
 * 
 * @author ese04
 * 
 * Takes care of the /login page where the user logs in as well as the /registratrion page where
 * we can create new users
 *
 */
@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;
		
	/**
	 * checks if there already exists an admin. if not, create the default one.
	 */
	public void initializeAdmin() {
		if(userService.findUserByName("admin") == null) {
			User user = new User();
			user.setName("admin");
			user.setPassword("1234");
			user.setRoleInput("ADMIN");
			userService.saveUser(user);
		}
	}

	/**
	 * Sends the user to the Login page
	 * @return the modelAndView of login
	 */
	@RequestMapping(value={"/login"}, method = RequestMethod.GET)
	public ModelAndView login(){
		initializeAdmin();		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	/**
	 * Sends the user to the registration page
	 * @return the modelAndView of registration
	 */
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public ModelAndView registration(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}
	
	/**
	 * Handles the requests to the registration page
	 * @param user the logged in user
	 * @param bindingResult does form input validation
	 * @return The specific modelAndView of where the user gets to 
	 */
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		User userExists = userService.findUserByName(user.getName());
		if (userExists != null) {
			bindingResult
					.rejectValue("name", "error.user",
							"Ein Nutzer mit diesem Namen besteht bereits.");
		}
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("registration");
		} else {
			System.out.println(user.getId());

			userService.saveUser(user);
			modelAndView.addObject("successMessage", "Benutzer konnte erfolgreich registriert werden.");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}
}
