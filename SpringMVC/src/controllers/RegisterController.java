package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import pojo.User;

@Controller
@RequestMapping("/User")
public class RegisterController {
	
	@RequestMapping("/showForm")
	public String showRegForm(Model Model) {
		User newUser = new User();
		
		Model.addAttribute("User",newUser);
		
		
		return "registrationform";
	}
	
	@RequestMapping("/processForm")
	public String processForm(@ModelAttribute("User") User newUser) {
		
		//log the input data
		System.out.println("theStudent : " + newUser.getFirstName() + " " + newUser.getLastName());
		return "registration-confirmation";
		
	}

	
}
