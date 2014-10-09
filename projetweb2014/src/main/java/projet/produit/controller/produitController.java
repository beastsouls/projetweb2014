package projet.produit.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import projet.client.model.Client;
import projet.produit.model.Produit;
import projet.produit.repository.produitRepository;


@Controller
public class produitController {
	
	@Autowired
	private produitRepository produitRepository;
	

	@RequestMapping(value = "/create/produit", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("produit", new Produit());
		return "createproduit";
	}
	
	@RequestMapping(value = "/create/produit", method = RequestMethod.POST)
	public String submitForm( @Valid @ModelAttribute Produit produit, BindingResult bindingresult) {
		if (bindingresult.hasErrors()) {
            return "createproduit";
        }
		produitRepository.save(produit);
		return "redirect:/produit/";
	}
	
	@RequestMapping(value = "/produit/", method = RequestMethod.GET)
	public String listproduits(Model model) {
		
		model.addAttribute("produits", produitRepository.findAll());
		return "listproduit";
	}
	
	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public String accueil(Model model) {
		return "form";
	}*/
	
	@RequestMapping(value = "/delete/produit", method = RequestMethod.GET)
	public String deleteproduit(@RequestParam("id") Long id, Model model) {
		
		produitRepository.delete(id);
		
		return "redirect:/produit/";
	}
	
	@RequestMapping(value = "/edit/produit", method = RequestMethod.GET)
	public String editForm(@RequestParam("id") Long id, Model model) {
		
		model.addAttribute("produit", produitRepository.findOne(id));
		return "createproduit";
	}
	
	@RequestMapping(value = "/edit/produit", method = RequestMethod.POST)
	public String editPost(@ModelAttribute Produit produit, Model model) {
		produitRepository.save(produit);
		return "redirect:/produit/";
	}
}
