package projet.client.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import projet.client.model.Client;
import projet.client.repository.ClientRepository;


@Controller
public class ClientController {
	
	@Autowired
	private ClientRepository clientRepository;
	

	@RequestMapping(value = "/create/client", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("client", new Client());
		return "create";
	}
	
	@RequestMapping(value = "/create/client", method = RequestMethod.POST)
	public String submitForm(@Valid @ModelAttribute Client client, Model model, BindingResult bindingresult) {
		if (bindingresult.hasErrors()) {
            return "redirect:/create/client";
        }
		clientRepository.save(client);
		
		return "redirect:/client/";
	}
	
	@RequestMapping(value = "/client/", method = RequestMethod.GET)
	public String listclients(Model model) {
		
		model.addAttribute("clients", clientRepository.findAll());
		return "list";
	}
	
	@RequestMapping(value = "/delete/client", method = RequestMethod.GET)
	public String deleteclient(@RequestParam("id") Long id, Model model) {
		
		clientRepository.delete(id);
		
		return "redirect:/client/";
	}
	
	@RequestMapping(value = "/edit/client", method = RequestMethod.GET)
	public String editForm(@RequestParam("id") Long id, Model model) {
		
		model.addAttribute("client", clientRepository.findOne(id));
		return "create";
	}
	
	@RequestMapping(value = "/edit/client", method = RequestMethod.POST)
	public String editPost(@ModelAttribute Client client, Model model) {
		clientRepository.save(client);
		return "redirect:/client/";
	}
	
	
	/*public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/list").setViewName("list");
    }*/

  
    

}
