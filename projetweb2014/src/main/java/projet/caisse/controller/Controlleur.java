package projet.caisse.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	private Map<Long,ProduitQuantity> panierListe;
	private double total=0;

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

		panierListe = (Map<Long,ProduitQuantity>) session.getAttribute("panierListe");
		if (panierListe == null)
			{panierListe = new LinkedHashMap<Long,ProduitQuantity>(); }
		
		if(!panierListe.containsKey(produit.getId())){
		ProduitQuantity Pquantity = new ProduitQuantity();
		Pquantity.setElementPanier(produit);
		Pquantity.setQuantity(1);
		panierListe.put(Pquantity.getElementPanier().getId(),Pquantity);
		}

		session.setAttribute("panierListe", panierListe);
		return "redirect:/caisse";
	}

	
	
	@RequestMapping(value = "/calcul", method = RequestMethod.POST)
	public String calculpanierSubmit(@RequestParam("qt") int qt,@RequestParam("id") Long id, Model model, HttpSession session) {
		//System.out.println(id);
		panierListe = (Map<Long,ProduitQuantity>) session.getAttribute("panierListe");
		ProduitQuantity prodQuantity = panierListe.get(id);
		prodQuantity.setQuantity(qt);
		prodQuantity.setSomme(qt * prodQuantity.getElementPanier().getPrix());
		//prodQuantity.setSommeTotalFacture(prodQuantity.getSommeTotalFacture() + prodQuantity.getSomme());
		
			total =total  + panierListe.get(prodQuantity.getElementPanier().getId()).getSomme();
//			System.out.print("la somme s'eleve a " + total + "  apres le " + i + "e element \n");
		
		//prodQuantity.setSommeTotalFacture(total);
		session.setAttribute("total", total);
		
		session.setAttribute("panierListe", panierListe);
		return "redirect:/caisse";
	}
	
	@RequestMapping(value = "/resetPanier", method = RequestMethod.POST)
	public String resetpanierSubmit(Model model, HttpSession session) {
		//System.out.println(id);
		panierListe = (Map<Long,ProduitQuantity>) session.getAttribute("panierListe");
		panierListe = new LinkedHashMap<Long,ProduitQuantity>();
		total=0;
		session.setAttribute("panierListe", panierListe);
		session.setAttribute("total", total);
		return "redirect:/caisse";
	}
}