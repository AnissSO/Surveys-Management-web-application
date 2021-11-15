package com.stage.API21.service;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.API21.model.Question;
import com.stage.API21.model.Questionnaire;
import com.stage.API21.model.Utilisateur;
import com.stage.API21.repository.QuestionRepository;
import com.stage.API21.repository.UtilisateurRepository;

import lombok.Data;

@Data
@Service
public class UtilisateurService {

	@Autowired UtilisateurRepository utilisateurRepository;
	
	public Utilisateur saveUser(Utilisateur user) {
		Utilisateur savedUser = utilisateurRepository.save(user);
		
		return savedUser;
	}
	
	public void deleteUser(final BigInteger idQuestion) {
		utilisateurRepository.deleteById(idQuestion);
	}
	
	public Optional<Utilisateur> getUser(final BigInteger id){
		return utilisateurRepository.findById(id);
	}
	
	public Optional<Utilisateur> findUserByEmail(final String emailUser){
		return utilisateurRepository.findUserByEmail(emailUser);
	}
	
	public Iterable<Utilisateur> getUtilisateurs(){
		return utilisateurRepository.findAll();
	}
}
