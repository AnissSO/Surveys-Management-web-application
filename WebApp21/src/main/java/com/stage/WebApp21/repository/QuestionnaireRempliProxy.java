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
import com.stage.WebApp21.model.Questionnaire;
import com.stage.WebApp21.model.QuestionnaireRempli;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class QuestionnaireRempliProxy {
	
	@Autowired
	private CustomProperties props;
	
	public QuestionnaireRempli getQuestionnaireRempli(BigInteger id) {
		String baseApiUrl = props.getApiUrl();
		String getQuestionnaireUrl = baseApiUrl + "/questionnaireRempli/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<QuestionnaireRempli> response = restTemplate.exchange(getQuestionnaireUrl, HttpMethod.GET, null, QuestionnaireRempli.class);
		
		log.debug("Get Survey call " + response.getStatusCode().toString());
		
		return response.getBody();	
	}
	
	public Iterable<QuestionnaireRempli> getQuestionnairesRemplis(){
		String baseApiUrl = props.getApiUrl();
		String getQuestionnairesUrl = baseApiUrl + "/questionnairesRemplis";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<QuestionnaireRempli>> response = restTemplate.exchange(getQuestionnairesUrl, HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<QuestionnaireRempli>>() {});
		
		log.debug("Get Surveys call " + response.getStatusCode().toString());
		
		return response.getBody();		
	}
	
	public QuestionnaireRempli createQuestionnaireRempli(QuestionnaireRempli q) {
		String baseApiUrl = props.getApiUrl();
		String createQuestionnaireRempliUrl = baseApiUrl + "/questionnaireRempli";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<QuestionnaireRempli> request = new HttpEntity<QuestionnaireRempli>(q);
		ResponseEntity<QuestionnaireRempli> response = restTemplate.exchange(createQuestionnaireRempliUrl, HttpMethod.POST, request, QuestionnaireRempli.class);
		
		log.debug("Create Survey call " + response.getStatusCode().toString());
		
		return response.getBody();		
	}

}
