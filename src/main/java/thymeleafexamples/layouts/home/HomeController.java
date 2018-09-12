package thymeleafexamples.layouts.home;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
class HomeController {
	
	@Value("${welcome.message:test}")
	private String message = "Hello World";
	

    @ModelAttribute("module")
    String module() {
        return "home";
    }

    @GetMapping("/")
    String index(Principal principal,Model model) throws UnknownHostException {
    		
    		model.addAttribute("message", this.message+" "+InetAddress.getLocalHost().getHostName());
        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
    }
}
