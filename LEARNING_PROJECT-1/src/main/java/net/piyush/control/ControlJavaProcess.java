package net.piyush.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import net.piyush.dto.DtoJavaScheduledTask;

@Controller
@RequestMapping(value="/javaprocess",method = {RequestMethod.POST, RequestMethod.GET})
public class ControlJavaProcess {
 
	@GetMapping
	public String getJavaProcess(Model model) {

		DtoJavaScheduledTask dtoJavaScheduled =  new DtoJavaScheduledTask();
		model.addAttribute("dtoObject", dtoJavaScheduled);
		
		
		return "page/javaprocess_page";
	}
}
