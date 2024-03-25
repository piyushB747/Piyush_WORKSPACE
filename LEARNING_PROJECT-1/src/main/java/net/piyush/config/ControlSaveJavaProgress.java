package net.piyush.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/javarelated",method =RequestMethod.GET)
public class ControlSaveJavaProgress {

	@GetMapping("/get")
    public String handleGetRequest() {
        return "yourViewName";
    }
}
