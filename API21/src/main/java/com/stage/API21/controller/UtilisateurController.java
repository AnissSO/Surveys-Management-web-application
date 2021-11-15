package com.stage.API21.controller;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stage.API21.model.Question;
import com.stage.API21.model.Questionnaire;
import com.stage.API21.model.Utilisateur;
import com.stage.API21.service.QuestionService;
import com.stage.API21.service.UtilisateurService;

@RestController
public class UtilisateurController {
	
	@Autowired
	private UtilisateurService utilisateurService;

	@DeleteMapping("/user/{id}")
	public void deleteUser(@PathVariable("id") final BigInteger id) {
		utilisateurService.deleteUser(id);
	}
	
	@PutMapping("/utilisateur/{id}")
	public Utilisateur updateUser(@PathVariable("id") final BigInteger id, @RequestBody Utilisateur user) {
		Optional<Utilisateur> u = utilisateurService.getUser(id);
		if(u.isPresent()) {
			Utilisateur currentUtilisateur = u.get();
			
			String prenom = user.getPrenom();
			if(prenom != null) {
				currentUtilisateur.setPrenom(prenom);
			}
			String nom = user.getNom();
			if(nom != null) {
				currentUtilisateur.setNom(nom);
			}
			String email = user.getEmail();
			if(email != null) {
				currentUtilisateur.setEmail(email);
			}
			
			System.out.println(currentUtilisateur);
			utilisateurService.saveUser(currentUtilisateur);
			return currentUtilisateur;
		}else{
			return null;
		}
	}
	
	@GetMapping("/utilisateur/{id}")
	public Utilisateur getUser(@PathVariable("id") final BigInteger id) {
		Optional<Utilisateur> utilisateur = utilisateurService.getUser(id);
		if(utilisateur.isPresent()) {
			return utilisateur.get();
		}
		else {
			return null;
		}
	}
	
	@PostMapping("/utilisateur")
	public Utilisateur createUser(@RequestBody Utilisateur utilisateur) {
		return utilisateurService.saveUser(utilisateur);
	}
	
	@GetMapping("/utilisateurs")
	public Iterable<Utilisateur> getUtilisateurs(){
		return utilisateurService.getUtilisateurs();
	}
	
	@GetMapping("/userByEmail/{emailUser}")
	public Utilisateur findUserByEmail(@PathVariable("emailUser") final String emailUser) {
		Optional<Utilisateur> user = utilisateurService.findUserByEmail(emailUser);
		if(user.isPresent()) {
			return user.get();
		}
		else {
			return null;
		}
	}
	
}
