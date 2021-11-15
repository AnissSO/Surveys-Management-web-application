package com.stage.WebApp21.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.WebApp21.model.Question;
import com.stage.WebApp21.model.Questionnaire;
import com.stage.WebApp21.model.Utilisateur;
import com.stage.WebApp21.repository.UtilisateurProxy;

import lombok.Data;

@Data
@Service
public class UtilisateurService {

	@Autowired
	private UtilisateurProxy utilisateurProxy;
	
	public void deleteUser(final BigInteger idUser) {
		utilisateurProxy.deleteUser(idUser);
	}
	
	public Utilisateur getUser(final BigInteger id) {
		return utilisateurProxy.getUser(id);
	}
	
	public Utilisateur saveUtilisateur(Utilisateur utilisateur) {
		Utilisateur savedUser = null;
		
		if(utilisateur.getId_utilisateur() == null) {
			savedUser = utilisateurProxy.createUser(utilisateur);
		}else {
			savedUser = utilisateurProxy.updateUser(utilisateur);
		}
		
		return savedUser;
	}
	
	public Iterable<Utilisateur> getUtilisateurs(){
		return utilisateurProxy.getUtilisateurs();
	}
	
	public Utilisateur findUserByEmail(String emailUser){
		return utilisateurProxy.findUserByEmail(emailUser);
	}
}
