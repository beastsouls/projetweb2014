package projet.authentification;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class AppController {



@RequestMapping(value="/index",method=RequestMethod.GET)
public String postLogin(Authentication authentication)
{
	
		if(authentication.getName().equalsIgnoreCase("admin"))
		{
			return "form";
		}
		else
		
			
			{return "user";
		
			}
		
	

}
}
