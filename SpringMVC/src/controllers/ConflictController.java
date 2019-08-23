package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subMapping")
public class ConflictController {
	
	//Request mapped to location that is already mapped to another method
	// will run into error if dont fix
	@RequestMapping("/showForm")
	public String displayTheForm(Model model) {
		
		//add message to the model
		model.addAttribute("message", "CONFLICTED");
		
		return "printForm";
	}

	
}
