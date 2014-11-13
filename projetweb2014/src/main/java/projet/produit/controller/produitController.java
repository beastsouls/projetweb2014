package projet.produit.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import projet.client.model.Client;
import projet.produit.model.Produit;
import projet.produit.repository.produitRepository;


@Controller
public class produitController implements ResourceLoaderAware{
	
	private ResourceLoader resourceLoader;
	
	@Autowired
	private produitRepository produitRepository;
	

	@RequestMapping(value = "/create/produit", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("produit", new Produit());
		return "createproduit";
	}
	
//	@RequestMapping(value = "/create/produit", method = RequestMethod.POST)
//	public String submitForm( @Valid @ModelAttribute Produit produit, BindingResult bindingresult) {
//		if (bindingresult.hasErrors()) {
//            return "createproduit";
//        }
//		produitRepository.save(produit);
//		return "redirect:/produit/";
//	}
	
	@RequestMapping(value = "/create/produit", method = RequestMethod.POST)
	public String submitForm( @Valid @ModelAttribute Produit produit, BindingResult bindingresult, @RequestParam("file") MultipartFile file) {
		if (bindingresult.hasErrors()) {
            return "createproduit";
        }
		produitRepository.save(produit);
		
		
		if (!file.isEmpty()) {
            try {System.out.println(file.toString());
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(produit.getName() + ".jpg")));
                stream.write(bytes);
                
               
                File dest =  new File (resourceLoader.getResource("file:src/main/resources/public/images/produits/"+produit.getName() + ".jpg").getURL().getFile());
             //   File dest =  new File ("src/main/resources/public/images/produits/"+produit.getName() + ".jpg");
                System.out.println("chemin relatif de dest : "+dest.getPath());
                System.out.println("chemin absolu de dest : "+dest.getAbsolutePath());
                System.out.println("avant transfert dest");
                file.transferTo(dest.getAbsoluteFile());
                System.out.println("apres transfert dest");
                
                stream.close();
            } 
            catch (Exception e) {
            	System.out.println("empty file");
            }
        }
		return "redirect:/produit/";
	}
	
	
	
	
	
	@RequestMapping(value = "/produit/", method = RequestMethod.GET)
	public String listproduits(Model model) {
		
		model.addAttribute("produits", produitRepository.findAll());
		return "listproduit";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String accueil(Model model) {
		model.addAttribute("produits", produitRepository.findAll());
		return "form";
	}
	
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
	
	
	@Override
	public void setResourceLoader(ResourceLoader rl) {
		// TODO Auto-generated method stub
		this.resourceLoader = rl;
	}

}
