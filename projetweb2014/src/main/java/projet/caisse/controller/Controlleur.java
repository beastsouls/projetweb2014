package projet.caisse.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import projet.caisse.model.*;

@Controller
public class Controlleur {
		 @RequestMapping(value="/", method=RequestMethod.GET)
		 public String createForm(Model model) {
		 model.addAttribute("product", new Product());
			return "caisse";
	}
	 
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String productSubmit(@ModelAttribute Product product, HttpSession session, Model model) {
				
	List<Product> panier = (List<Product>)session.getAttribute("panier");
				if(panier == null)
					panier = new ArrayList<Product>();
				
				panier.add(product);
				session.setAttribute("panier", panier);
				
				return "redirect:/";
			}
}
