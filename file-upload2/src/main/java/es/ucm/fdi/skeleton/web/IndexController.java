package es.ucm.fdi.skeleton.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	@RequestMapping(method = RequestMethod.GET, value = "/")
	public String index() {
		return "redirect:/files";
	}
}
