package thymeleafexamples.layouts.home;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    		String hostname = "";
    		try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("hostname").getInputStream()));
				hostname = reader.readLine();
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		model.addAttribute("message", this.message+" "+hostname);
        return principal != null ? "home/homeSignedIn" : "home/homeNotSignedIn";
    }
}
