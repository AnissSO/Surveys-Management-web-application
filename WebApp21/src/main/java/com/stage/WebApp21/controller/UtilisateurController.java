package com.stage.WebApp21.controller;

import java.math.BigInteger;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stage.WebApp21.model.PageQuestionnaire;
import com.stage.WebApp21.model.Question;
import com.stage.WebApp21.model.QuestionOption;
import com.stage.WebApp21.model.Questionnaire;
import com.stage.WebApp21.model.Utilisateur;
import com.stage.WebApp21.service.QuestionnaireService;
import com.stage.WebApp21.service.UtilisateurService;

import lombok.Data;

@Data
@Controller
public class UtilisateurController {

	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
	private QuestionnaireService questionnaireService;
	
	@GetMapping("/deleteUser/{id}")
	public ModelAndView deleteUser(@PathVariable("id") final BigInteger id) {
		Utilisateur u = utilisateurService.getUser(id);
		
		
		utilisateurService.deleteUser(id);
		
		return new ModelAndView("redirect:/securite/");	
	}
	
	
	@GetMapping("/updateUser/{id}")
	public String updateUser(@PathVariable("id") final BigInteger id, Model model) {
		Utilisateur u = utilisateurService.getUser(id);
		
		model.addAttribute("utilisateurUpdate", u);
		model.addAttribute("uId", u.getId_utilisateur());
		
		return "formUpdateUser";
	}
	
	@PostMapping("/updateTextUser")
	public ModelAndView updateTextUser(@ModelAttribute Utilisateur utilisateurUpdate) {
		System.out.println(utilisateurUpdate);
		utilisateurService.saveUtilisateur(utilisateurUpdate);
		
		
		return new ModelAndView("redirect:/securite");
	}
	
	
	@GetMapping("/showUser/{id}")
	public String showUser(@PathVariable("id") final BigInteger id, Model model) {
		Utilisateur u = utilisateurService.getUser(id);
		model.addAttribute("utilisateur", u);
		
		return "showUtilisateur";
	}
	
	@GetMapping("/monCompte/{id}")
	public String monCompte(@PathVariable("id") final BigInteger id, Model model) {
		Utilisateur u = utilisateurService.getUser(id);
		model.addAttribute("utilisateur", u);
		model.addAttribute("idUser", id);
		return "showMonCompte";
	}
	
	@GetMapping("/createUtilisateur")
	public String createUtilisateur(Model model) {
		Utilisateur u = new Utilisateur();
		
		model.addAttribute("utilisateur", u);
		return "formNewUtilisateur";
	}
	
	@PostMapping("/saveUtilisateur")
	public ModelAndView saveUtilisateur(@ModelAttribute Utilisateur utilisateur) {
		System.out.println(utilisateur);
		Utilisateur u = utilisateur;
		
		u.setRole_id(1);
		u.setDate_creation(LocalDateTime.now());
		
		utilisateurService.saveUtilisateur(u);
		
		
		return new ModelAndView("redirect:/securite"); 
	}
	
	
	@PostMapping("/saveUtilisateurVisitor")
	public ModelAndView saveUtilisateurVisitor(@ModelAttribute Utilisateur utilisateur) {
		System.out.println(utilisateur);
		Utilisateur u = utilisateur;
		
		u.setRole_id(1);
		u.setDate_creation(LocalDateTime.now());
		
		utilisateurService.saveUtilisateur(u);
		
		
		return new ModelAndView("redirect:/homeVisitor"); 
	}
	
	@GetMapping("/securite")
	public String securite(Model model) {
		Iterable<Utilisateur> listUtilisateurs = utilisateurService.getUtilisateurs();
		model.addAttribute("listeDesUtilisateurs", listUtilisateurs);
		return "securiteAdmin";
	}
	
	@GetMapping("/admin")
	public String homeAdmin(Model model) {
		Utilisateur user = new Utilisateur();
		
		//model.addAttribute("passwordIncorrect", user.getLogin());
		model.addAttribute("utilisateur", user);
		return "homeAdmin";
	}
	
	@GetMapping("/adminPasswordIncorrect")
	public String homeAdminPasswordIncorrect(Model model) {
		Utilisateur user = new Utilisateur();
		model.addAttribute("utilisateur", user);
		return "homeAdminPasswordIncorrect";
	}
	
	@GetMapping("/homeUser/{id}")
	public String homeUser(@PathVariable("id") final BigInteger id, Model model) {
		Iterable<Questionnaire> listQuestionnaire = questionnaireService.getQuestionnaires();
		model.addAttribute("questionnaires", listQuestionnaire);
		model.addAttribute("idUser", id);
		return "homeUser";
	}
	
	@GetMapping("/homeVisitor")
	public String homeVisitor(Model model) {
		Iterable<Questionnaire> listQuestionnaire = questionnaireService.getQuestionnaires();
		model.addAttribute("questionnaires", listQuestionnaire);
		//model.addAttribute("idUser", id);
		return "homeVisitor";
	}
	
	@GetMapping("/creerCompte")
	public String creerCompte(Model model) {
		Utilisateur u = new Utilisateur();
		model.addAttribute("utilisateur", u);
		//model.addAttribute("idUser", id);
		return "creerNouvCompte";
	}
	
	@PostMapping("formLogin")
	public ModelAndView formLogin(@ModelAttribute Utilisateur user, Model model) {
		Utilisateur utilisateur = utilisateurService.findUserByEmail(user.getEmail());
		
		if(utilisateur == null) {
			System.out.println("false 1");
			return new ModelAndView("redirect:/adminPasswordIncorrect");
		}
		else {
			if(utilisateur.getPassword().equals(user.getPassword())){
				System.out.println("true");
				if(utilisateur.getRole_id() == 0) {
					System.out.println("Admin" + utilisateur.getNom());
					return new ModelAndView("redirect:/");
				}
				else{
					System.out.println("Utilisateur"+ utilisateur.getNom());
					return new ModelAndView("redirect:/homeUser/" + utilisateur.getId_utilisateur());
				}
				
			}
			else {
				System.out.println("false 2");
				return new ModelAndView("redirect:/adminPasswordIncorrect");
			}
		}
		
	}
}
