package org.enset1.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EtudiantController {
	
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	

}
