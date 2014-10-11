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

import projet.produit.model.*;
import projet.produit.repository.*;


@Controller
public class Controlleur {

	@Autowired
	private produitRepository produitRepository;

	@RequestMapping(value = "/caisse", method = RequestMethod.GET)
	public String createForm(Model model) {
		
		long taille = 0;
		ArrayList<Produit> listeProduit = (ArrayList<Produit>) produitRepository.findAll();
		if(listeProduit.size()%5 == 0)
			taille = listeProduit.size()/5;
		else
			taille = (listeProduit.size()/5) + 1;
		
		model.addAttribute("products", produitRepository.findAll());
		model.addAttribute("nbProducts", produitRepository.count());
		model.addAttribute("nbLignes", taille);
		
		model.addAttribute("product", new Produit());
		return "caisse";
	}

	@RequestMapping(value = "/caisse", method = RequestMethod.POST)
	public String productSubmit(@ModelAttribute Produit product,
			HttpSession session, Model model) {

		List<Produit> panier = (List<Produit>) session.getAttribute("panier");
		if (panier == null)
			panier = new ArrayList<Produit>();

		panier.add(product);
		session.setAttribute("panier", panier);

		return "redirect:/caisse";
	}
	
	

	//Lien vers la caisse
	/*		@RequestMapping(value = "/caissen", method = RequestMethod.GET)
			public String boutonsProducts(Model model) {
				
				long taille = 0;
				ArrayList<Produit> listeProduit = (ArrayList<Produit>) produitRepository.findAll();
				if(listeProduit.size()%5 == 0)
					taille = listeProduit.size()/5;
				else
					taille = (listeProduit.size()/5) + 1;
				
				model.addAttribute("products", produitRepository.findAll());
				model.addAttribute("nbProducts", produitRepository.count());
				model.addAttribute("nbLignes", taille);
				model.addAttribute("produit", new Produit());
				return "caisse";
			}*/
			
		/*	@RequestMapping(value = "/caissen", method = RequestMethod.POST)
			public String boutonSubmit(@ModelAttribute Produit produit, HttpSession session, Model model) {
						
			List<Produit> panier = (List<Produit>)session.getAttribute("panier");
						if(panier == null)
							panier = new ArrayList<Produit>();
						
						panier.add(produit);
						session.setAttribute("panier", panier);
						
						return "redirect:/caisse";
					}
	*/

}
