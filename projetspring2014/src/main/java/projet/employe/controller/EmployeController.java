package projet.employe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import projet.employe.model.Employe;
import projet.employe.repository.*;


@Controller
public class EmployeController {
	
	@Autowired
	private EmployeRepository employeRepository;
	

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createForm(Model model) {
		model.addAttribute("employe", new Employe());
		return "create";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String submitForm(@ModelAttribute Employe employe, Model model) {
		
		employeRepository.save(employe);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String listEmployes(Model model) {
		
		model.addAttribute("employes", employeRepository.findAll());
		return "list";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String deleteEmploye(@RequestParam("id") Long id, Model model) {
		
		employeRepository.delete(id);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String editForm(@RequestParam("id") Long id, Model model) {
		
		model.addAttribute("employe", employeRepository.findOne(id));
		return "create";
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editPost(@ModelAttribute Employe employe, Model model) {
		employeRepository.save(employe);
		return "redirect:/";
	}
	
}