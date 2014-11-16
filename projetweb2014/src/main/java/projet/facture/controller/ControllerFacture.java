package projet.facture.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import projet.client.repository.ClientRepository;
import projet.facture.model.FactureModel;
import projet.facture.repository.FactureRepository;
import projet.produit.model.Produit;
import projet.produit.model.ProduitQuantity;



@Controller
public class ControllerFacture {
	

	private Map<Long,ProduitQuantity> panierListe;
	
	@Autowired
	private FactureRepository factureRepository;

	@RequestMapping(value = "/facture", method = RequestMethod.POST)
	public String createForm(Model model) {
		model.addAttribute("facture", new FactureModel());
		
		return "caisse";
	}

}
