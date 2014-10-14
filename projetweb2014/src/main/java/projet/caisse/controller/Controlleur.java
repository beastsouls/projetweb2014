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
import org.springframework.web.bind.annotation.RequestParam;

import projet.produit.model.*;
import projet.produit.repository.*;


@Controller
public class Controlleur {

	@Autowired
	private produitRepository produitRepository;

	@RequestMapping(value = "/caisse", method = RequestMethod.GET)
	public String createForm(Model model) {
		
		model.addAttribute("products", produitRepository.findAll());
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
		System.out.println("dans le post de caisse "+panier.get(0).getName());
		session.setAttribute("panier", panier);

		return "redirect:/caisse";
	}
	
	
	
	
	//Réponse au clic pour ajouter un produit au panier
	
	@RequestMapping(value = "/ajoutPanier", method = RequestMethod.GET)
	public String boutonProduit(@RequestParam("id") Long id, Model model) {
		
		model.addAttribute("produit", produitRepository.findOne(id));
		
		return "caisse";
	}
	
	@RequestMapping(value = "/ajoutPanier", method = RequestMethod.POST)
	public String produitSubmit(@ModelAttribute Produit produit, HttpSession session2, Model model) {
		
		List<Produit> panierListe = (List<Produit>) session2.getAttribute("panierListe");
		if (panierListe == null)
			panierListe = new ArrayList<Produit>();

		panierListe.add(produit);
		System.out.println("dans le post "+panierListe.get(0).getName());
		session2.setAttribute("panierListe", panierListe);

		return "redirect:/caisse";
	}
}
