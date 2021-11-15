package com.stage.WebApp21.repository;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.stage.WebApp21.CustomProperties;
import com.stage.WebApp21.model.Question;
import com.stage.WebApp21.model.Questionnaire;
import com.stage.WebApp21.model.Utilisateur;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UtilisateurProxy {

	@Autowired
	private CustomProperties props;
	
	public void deleteUser(BigInteger idQuestion) {
		String baseApiUrl = props.getApiUrl();
		String deleteQuestionUrl = baseApiUrl + "/user/" + idQuestion;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteQuestionUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Delete Employee call " + response.getStatusCode().toString());
	}
	
	public Utilisateur updateUser(Utilisateur u) {
		String baseApiUrl = props.getApiUrl();
		String updateUtilisateurUrl = baseApiUrl + "/utilisateur/" + u.getId_utilisateur();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Utilisateur> request = new HttpEntity<Utilisateur>(u);
		ResponseEntity<Utilisateur> response = restTemplate.exchange(
				updateUtilisateurUrl, 
				HttpMethod.PUT, 
				request, 
				Utilisateur.class);
		
		log.debug("Update Employee call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	public Utilisateur getUser(BigInteger id) {
		String baseApiUrl = props.getApiUrl();
		String getUtilisateurUrl = baseApiUrl + "/utilisateur/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Utilisateur> response = restTemplate.exchange(getUtilisateurUrl, HttpMethod.GET, null, Utilisateur.class);
		
		log.debug("Get Survey call " + response.getStatusCode().toString());
		
		return response.getBody();	
	}
	
	public Utilisateur createUser(Utilisateur u) {
		String baseApiUrl = props.getApiUrl();
		String createUtilisateurUrl = baseApiUrl + "/utilisateur";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Utilisateur> request = new HttpEntity<Utilisateur>(u);
		ResponseEntity<Utilisateur> response = restTemplate.exchange(createUtilisateurUrl, HttpMethod.POST, request, Utilisateur.class);
		
		log.debug("Create Survey call " + response.getStatusCode().toString());
		
		return response.getBody();		
	}
	
	public Iterable<Utilisateur> getUtilisateurs(){
		String baseApiUrl = props.getApiUrl();
		String getUtilisateursUrl = baseApiUrl + "/utilisateurs";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Utilisateur>> response = restTemplate.exchange(getUtilisateursUrl, HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Utilisateur>>() {});
		
		log.debug("Get Surveys call " + response.getStatusCode().toString());
		
		return response.getBody();		
	}
	
	public Utilisateur findUserByEmail(String emailUser) {
		String baseApiUrl = props.getApiUrl();
		String getUserByEmailUrl = baseApiUrl + "/userByEmail/" + emailUser;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Utilisateur> response = restTemplate.exchange(getUserByEmailUrl, HttpMethod.GET, null, Utilisateur.class);
		
		log.debug("Get Survey call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
}
