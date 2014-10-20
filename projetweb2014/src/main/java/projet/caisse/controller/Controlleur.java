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
	private List<Produit> panierListe;
//	private int index = 1;

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
		session.setAttribute("panier", panier);
		return "redirect:/caisse";
	}

	// Réponse au clic pour ajouter un produit au panier
	@RequestMapping(value = "/ajoutPanier", method = RequestMethod.GET)
	public String boutonProduit(@RequestParam("id") Long id, Model model) {
		model.addAttribute("produit", produitRepository.findOne(id));
		return "caisse";
	}

	@RequestMapping(value = "/ajoutPanier", method = RequestMethod.POST)
	public String produitSubmit(@ModelAttribute("id") Produit produit,	HttpSession session, Model model) {
		panierListe = (List<Produit>) session.getAttribute("panierListe");
		if (panierListe == null)
			{panierListe = new ArrayList<Produit>(); }
		
		panierListe.add(produit);
		
		//session.setAttribute("index", produit.getId());
//		produit.setCpt((int)produit.getId());
//		System.out.println(" on met a jour l'index " + produit.getCpt());
		session.setAttribute("panierListe", panierListe);
		return "redirect:/caisse";
	}

	// @RequestMapping(value = "/calcul", method = RequestMethod.GET)
	// public String calculpanierForm(@RequestParam("id") Long id, Model model)
	// {
	// model.addAttribute("produit", produitRepository.findOne(id));
	//
	// return "caisse";
	// }
	@RequestMapping(value = "/calcul", method = RequestMethod.POST)
	public String calculpanierSubmit(@RequestParam("qt") int qt,@ModelAttribute("id") Produit produit, Model model) {
		System.out.println(produit.getId());
		produit.setQt(qt);
		System.out.println(produit.getQt());
		produitRepository.save(produit);
		System.out.println(produit.getQt());
//		panierListe.set(produit.getCpt(), produit);
		panierListe.remove(produit);
		panierListe.set((int) produit.getId()-1, produit);
		//model.addAttribute("qant", produit.getQt());
		return "redirect:/caisse";
	}
}