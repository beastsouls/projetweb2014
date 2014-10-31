package projet.caisse.controller;

import java.util.ArrayList;
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

import projet.CodePromo.model.CodePromo;
import projet.CodePromo.repository.CodePromoRepository;
import projet.client.repository.ClientRepository;
import projet.facture.model.FactureModel;
import projet.facture.repository.FactureRepository;
import projet.produit.model.Produit;
import projet.produit.model.ProduitQuantity;
import projet.produit.repository.produitRepository;

@Controller
public class Controlleur {
	@Autowired
	private produitRepository produitRepository;
	
	
	private Map<Long,ProduitQuantity> panierListe;
	private double total=0;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private FactureRepository factureRepository;
	
	@Autowired
	private CodePromoRepository CPrepository;

	@RequestMapping(value = "/caisse", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("products", produitRepository.findAll());
		model.addAttribute("clients",  clientRepository.findAll());
		model.addAttribute("codePromos",  CPrepository.findAll());
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
			ProduitQuantity  Pquantity = new ProduitQuantity();
		Pquantity.setElementPanier(produit);
		Pquantity.setQuantity(1);
		panierListe.put(Pquantity.getElementPanier().getId(),Pquantity);
		}

		session.setAttribute("panierListe", panierListe);
		return "redirect:/caisse";
	}

	
	
	@RequestMapping(value = "/calcul", method = RequestMethod.POST)
	public String calculpanierSubmit(@RequestParam("qt") int qt,@RequestParam("id") Long id, Model model, HttpSession session , Produit produit) {
		//System.out.println(id);
		panierListe = (Map<Long,ProduitQuantity>) session.getAttribute("panierListe");
		ProduitQuantity prodQuantity = panierListe.get(id);
		prodQuantity.setQuantity(qt);
		prodQuantity.setSomme(qt * prodQuantity.getElementPanier().getPrix());
		//prodQuantity.setSommeTotalFacture(prodQuantity.getSommeTotalFacture() + prodQuantity.getSomme());
		
//		if(!panierListe.containsKey(prodQuantity.getElementPanier().getId())){
//			total =total  + panierListe.get(prodQuantity.getElementPanier().getId()).getSomme();
////			System.out.print("la somme s'eleve a " + total + "  apres le " + i + "e element \n");
//		}
//		else
//		{
//			total =total  - panierListe.get(prodQuantity.getElementPanier().getId()).getSomme();
//		}
		total =total  + panierListe.get(prodQuantity.getElementPanier().getId()).getSomme();
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
	
	@RequestMapping(value = "/payespece", method = RequestMethod.POST)
	public String payerenespece(Model model, HttpSession session) {
		//System.out.println(id);
		panierListe = (Map<Long,ProduitQuantity>) session.getAttribute("panierListe");
		String liste="";
		FactureModel lafacture = new FactureModel();
		 for(long key: panierListe.keySet()){
			             System.out.println(key + " - " + panierListe.get(key).getElementPanier().getName()+ " - " +panierListe.get(key).getQuantity());
			             liste=liste+"  "+panierListe.get(key).getElementPanier().getName().toString()+ " ";
			             lafacture.setPanier(liste);
			             lafacture.setMontant(panierListe.get(key).getSomme());
			             lafacture.setMoyen("ESPECE");
			             
			           
		 }
		 System.out.println("la facture");
		 System.out.println(lafacture.getPanier());
		 System.out.println(lafacture.getMontant());
		 System.out.println(lafacture.getMoyen());
		factureRepository.save(lafacture);
//		session.setAttribute("panierListe", panierListe);
//		session.setAttribute("total", total);
		return "redirect:/caisse";
	}
	
	@RequestMapping(value = "/validCode", method = RequestMethod.GET)
	public String testCodeSubmit(@RequestParam("id") Long id, @RequestParam("codepromos") String code, Model model, HttpSession session) {
		
		for(int i=0; i< CPrepository.count(); i++)
		{
			if(CPrepository.findOne(id).getCode() == code)
			{
				total = total - CPrepository.findOne(id).getMontant();
			}
		}
		
		session.setAttribute("total", total);
		return "redirect:/caisse";
	}
}