package dev.SpringMVCInit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@RequestMapping("/")
	public String showPage() {
		return "<ol>hello<li>HI</li><li>NiHao</li></ol>"
				+"<button onclick=\"getElementById('demo').innerHTML=Date()\">What is the time?</button>"
				+"<p id=\"demo\"></p>"
				+ "<button  onclick=\"getElementById('seat').innerHTML = 'A2'\">Select Seat</button>"
				+"<p id=\"seat\"></p>";
	}


}
