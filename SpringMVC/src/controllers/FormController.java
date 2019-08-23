package controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/base")
public class FormController {
	
	// method to show the initial HTML form
	@RequestMapping("/showForm")
	public String showForm() {
		return "form";
	}
	
	//method to process the HTML form
	@RequestMapping("/processForm")
	public String processForm() {
		return "printForm";
	}
	
	//method to read form data & add data to model
	@RequestMapping("processFormV2")
	public String Shouting(HttpServletRequest request, Model model) {
		//read request parameter from the HTML form
		// gets the attribute 'studentName' from the <input> element
		String name = request.getParameter("studentName");
		
		//convert data to all caps
		name = name.toUpperCase();
		
		//create the message
		String result = "Yo! " + name;
		
		//add message to the model
		model.addAttribute("message", result);
		
		return "printForm";
	}
	
	@RequestMapping("processFormV3")
	public String processFormV3(@RequestParam("studentName") String name, Model model) {
		
		//convert data to all caps
		name = name.toUpperCase();
		
		//create the message
		String result = "Welcome " + name + "!";
		
		//add message to the model
		model.addAttribute("message", result);
		
		return "printForm";
	}

	
	
}
