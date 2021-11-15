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
import com.stage.WebApp21.model.PageQuestionnaire;
import com.stage.WebApp21.model.QuestionOptionUser;
import com.stage.WebApp21.model.QuestionUser;
import com.stage.WebApp21.model.Questionnaire;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class QuestionnaireProxy {

	@Autowired
	private CustomProperties props;
	
	public Iterable<Questionnaire> getQuestionnaires(){
		String baseApiUrl = props.getApiUrl();
		String getQuestionnairesUrl = baseApiUrl + "/questionnaires";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Questionnaire>> response = restTemplate.exchange(getQuestionnairesUrl, HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Questionnaire>>() {});
		
		log.debug("Get Surveys call " + response.getStatusCode().toString());
		
		return response.getBody();		
	}
	
	public Iterable<QuestionOptionUser> getQuestionOptionsUsers(){
		String baseApiUrl = props.getApiUrl();
		String getQuestionnairesUrl = baseApiUrl + "/questionOptionsUsers";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<QuestionOptionUser>> response = restTemplate.exchange(getQuestionnairesUrl, HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<QuestionOptionUser>>() {});
		
		log.debug("Get Surveys call " + response.getStatusCode().toString());
		
		return response.getBody();		
	}
	
	public Iterable<QuestionUser> getQuestionUsers(){
		String baseApiUrl = props.getApiUrl();
		String getQuestionnairesUrl = baseApiUrl + "/questionUsers";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<QuestionUser>> response = restTemplate.exchange(getQuestionnairesUrl, HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<QuestionUser>>() {});
		
		log.debug("Get Surveys call " + response.getStatusCode().toString());
		
		return response.getBody();		
	}
	
	public Questionnaire getQuestionnaire(BigInteger id) {
		String baseApiUrl = props.getApiUrl();
		String getQuestionnaireUrl = baseApiUrl + "/questionnaire/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Questionnaire> response = restTemplate.exchange(getQuestionnaireUrl, HttpMethod.GET, null, Questionnaire.class);
		
		log.debug("Get Survey call " + response.getStatusCode().toString());
		
		return response.getBody();	
	}
	
	
	public Questionnaire createQuestionnaire(Questionnaire q) {
		String baseApiUrl = props.getApiUrl();
		String createQuestionnaireUrl = baseApiUrl + "/questionnaire";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Questionnaire> request = new HttpEntity<Questionnaire>(q);
		ResponseEntity<Questionnaire> response = restTemplate.exchange(createQuestionnaireUrl, HttpMethod.POST, request, Questionnaire.class);
		
		log.debug("Create Survey call " + response.getStatusCode().toString());
		
		return response.getBody();		
	}
	
	
	public Questionnaire updateQuestionnaire(Questionnaire q) {
		String baseApiUrl = props.getApiUrl();
		String updateQuestionnaireUrl = baseApiUrl + "/questionnaire/" + q.getId();
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Questionnaire> request = new HttpEntity<Questionnaire>(q);
		ResponseEntity<Questionnaire> response = restTemplate.exchange(updateQuestionnaireUrl, HttpMethod.PUT, request, Questionnaire.class);
		
		log.debug("Update Survey call " + response.getStatusCode().toString());
		
		return response.getBody();	
	}
	
	public void deleteQuestionnaire(BigInteger id) {
		String baseApiUrl = props.getApiUrl();
		String deleteQuestionnaireUrl = baseApiUrl + "/questionnaire/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(deleteQuestionnaireUrl, HttpMethod.DELETE, null, Void.class);
		
		log.debug("Delete Survey call " + response.getStatusCode().toString());	
	}
	
	
}
