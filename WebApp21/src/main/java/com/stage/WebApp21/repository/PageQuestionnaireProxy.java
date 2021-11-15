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
import com.stage.WebApp21.model.Question;
import com.stage.WebApp21.model.Questionnaire;
import com.stage.WebApp21.model.QuestionnaireRempli;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Component
public class PageQuestionnaireProxy {

	
	@Autowired
	private CustomProperties props;
	
	public void deletePage(BigInteger idPage) {
		String baseApiUrl = props.getApiUrl();
		String deletePageUrl = baseApiUrl + "/page/" + idPage;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deletePageUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Delete Employee call " + response.getStatusCode().toString());
	}
	
	public Iterable<PageQuestionnaire> getQuestionnairePages(BigInteger idQuestionnaire){
		String baseApiUrl = props.getApiUrl();
		String getQuestionnairePagesUrl = baseApiUrl + "/questionnairePages/" + idQuestionnaire;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<PageQuestionnaire>> response = restTemplate.exchange(getQuestionnairePagesUrl, HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<PageQuestionnaire>>() {});
		
		log.debug("Get Pages of Surveys call " + response.getStatusCode().toString());
		
		return response.getBody();		
	}
	
	public Iterable<QuestionnaireRempli> getQuestionnairesRemplis(BigInteger idQuestionnaire){
		String baseApiUrl = props.getApiUrl();
		String getQuestionnairePagesUrl = baseApiUrl + "/questionnairesRempli/" + idQuestionnaire;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<QuestionnaireRempli>> response = restTemplate.exchange(getQuestionnairePagesUrl, HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<QuestionnaireRempli>>() {});
		
		log.debug("Get Pages of Surveys call " + response.getStatusCode().toString());
		
		return response.getBody();		
	}
	
	public PageQuestionnaire getPageQuestionnaire(BigInteger idPageQuestionnaire) {
		String baseApiUrl = props.getApiUrl();
		String getPageQuestionnaireUrl = baseApiUrl + "/questionnairePage/" + idPageQuestionnaire;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<PageQuestionnaire> response = restTemplate.exchange(getPageQuestionnaireUrl, HttpMethod.GET, null, PageQuestionnaire.class);
		
		log.debug("Get Survey call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	public PageQuestionnaire createPageQuestionnaire(PageQuestionnaire p) {
		String baseApiUrl = props.getApiUrl();
		String createPageQuestionnaireUrl = baseApiUrl + "/pageQuestionnaire";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<PageQuestionnaire> request = new HttpEntity<PageQuestionnaire>(p);
		ResponseEntity<PageQuestionnaire> response = restTemplate.exchange(createPageQuestionnaireUrl, HttpMethod.POST, request, PageQuestionnaire.class);
		
		log.debug("Create page of Survey call " + response.getStatusCode().toString());
		
		return response.getBody();		
	}
	
	
	public PageQuestionnaire updatePageQuestionnaire(PageQuestionnaire p) {
		String baseApiUrl = props.getApiUrl();
		String updateQuestionUrl = baseApiUrl + "/pageQuestionnaire/" + p.getId_questionnaire_definition_page();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<PageQuestionnaire> request = new HttpEntity<PageQuestionnaire>(p);
		ResponseEntity<PageQuestionnaire> response = restTemplate.exchange(
				updateQuestionUrl, 
				HttpMethod.PUT, 
				request, 
				PageQuestionnaire.class);
		
		log.debug("Update Employee call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
}
