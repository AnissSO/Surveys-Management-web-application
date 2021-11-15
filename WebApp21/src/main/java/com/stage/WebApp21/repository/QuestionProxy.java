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
import com.stage.WebApp21.model.QuestionOption;
import com.stage.WebApp21.model.QuestionOptionUser;
import com.stage.WebApp21.model.QuestionUser;
import com.stage.WebApp21.model.Questionnaire;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class QuestionProxy {

	@Autowired
	private CustomProperties props;
	
	
	public Iterable<Question> getQuestionsDeLaPage(BigInteger idPage){
		String baseApiUrl = props.getApiUrl();
		String getQuestionsDeLaPageUrl = baseApiUrl + "/questionsDeLaPage/" + idPage;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Question>> response = restTemplate.exchange(getQuestionsDeLaPageUrl, HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Question>>() {});
		
		log.debug("Get Surveys call " + response.getStatusCode().toString());
		
		return response.getBody();		
	}
	
	public Iterable<QuestionOption> getOptionsQuestion(BigInteger idQuestion){
		String baseApiUrl = props.getApiUrl();
		String getOptionsQuestionUrl = baseApiUrl + "/questionOptions/" + idQuestion;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<QuestionOption>> response = restTemplate.exchange(getOptionsQuestionUrl, HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<QuestionOption>>() {});
		
		log.debug("Get Pages of Surveys call " + response.getStatusCode().toString());
		
		return response.getBody();		
	}
	
	public Iterable<Question> getQuestions(){
		String baseApiUrl = props.getApiUrl();
		String getQuestionsUrl = baseApiUrl + "/questions";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<Question>> response = restTemplate.exchange(getQuestionsUrl, HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<Question>>() {});
		
		log.debug("Get Surveys call " + response.getStatusCode().toString());
		
		return response.getBody();		
	}
	
	public Iterable<QuestionOption> getQuestionOptions(){
		String baseApiUrl = props.getApiUrl();
		String getQuestionOptionsUrl = baseApiUrl + "/questionOptions";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Iterable<QuestionOption>> response = restTemplate.exchange(getQuestionOptionsUrl, HttpMethod.GET, null, new ParameterizedTypeReference<Iterable<QuestionOption>>() {});
		
		log.debug("Get Surveys call " + response.getStatusCode().toString());
		
		return response.getBody();		
	}
	
	public void deleteQuestion(BigInteger idQuestion) {
		String baseApiUrl = props.getApiUrl();
		String deleteQuestionUrl = baseApiUrl + "/question/" + idQuestion;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteQuestionUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Delete Employee call " + response.getStatusCode().toString());
	}
	
	public void deleteQuestionOption(BigInteger idQuestionOption) {
		String baseApiUrl = props.getApiUrl();
		String deleteQuestionOptionUrl = baseApiUrl + "/questionOption/" + idQuestionOption;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Void> response = restTemplate.exchange(
				deleteQuestionOptionUrl, 
				HttpMethod.DELETE, 
				null, 
				Void.class);
		
		log.debug("Delete Employee call " + response.getStatusCode().toString());
	}
	
	public Question getQuestion(BigInteger idQuestion) {
		String baseApiUrl = props.getApiUrl();
		String getQuestionUrl = baseApiUrl + "/question/" + idQuestion;
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Question> response = restTemplate.exchange(getQuestionUrl, HttpMethod.GET, null, Question.class);
		
		log.debug("Get Survey call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	public Question createQuestion(Question q) {
		String baseApiUrl = props.getApiUrl();
		String createQuestionUrl = baseApiUrl + "/question";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Question> request = new HttpEntity<Question>(q);
		ResponseEntity<Question> response = restTemplate.exchange(createQuestionUrl, HttpMethod.POST, request, Question.class);
		
		log.debug("Create Survey call " + response.getStatusCode().toString());
		
		return response.getBody();		
	}
	
	public QuestionUser createQuestionUser(QuestionUser q) {
		String baseApiUrl = props.getApiUrl();
		String createQuestionUrl = baseApiUrl + "/questionUser";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<QuestionUser> request = new HttpEntity<QuestionUser>(q);
		ResponseEntity<QuestionUser> response = restTemplate.exchange(createQuestionUrl, HttpMethod.POST, request, QuestionUser.class);
		
		log.debug("Create Survey call " + response.getStatusCode().toString());
		
		return response.getBody();		
	}
	
	public Question updateQuestion(Question q) {
		String baseApiUrl = props.getApiUrl();
		String updateQuestionUrl = baseApiUrl + "/question/" + q.getId_question();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<Question> request = new HttpEntity<Question>(q);
		ResponseEntity<Question> response = restTemplate.exchange(
				updateQuestionUrl, 
				HttpMethod.PUT, 
				request, 
				Question.class);
		
		log.debug("Update Employee call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
	
	public QuestionOption createQuestionOption(QuestionOption qo) {
		String baseApiUrl = props.getApiUrl();
		String createQuestionOptionUrl = baseApiUrl + "/questionOption";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<QuestionOption> request = new HttpEntity<QuestionOption>(qo);
		ResponseEntity<QuestionOption> response = restTemplate.exchange(createQuestionOptionUrl, HttpMethod.POST, request, QuestionOption.class);
		
		log.debug("Create Survey call " + response.getStatusCode().toString());
		
		return response.getBody();		
	}

	public QuestionOptionUser createQuestionOptionUser(QuestionOptionUser qo) {
		String baseApiUrl = props.getApiUrl();
		String createQuestionOptionUrl = baseApiUrl + "/questionOptionUser";
		
		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<QuestionOptionUser> request = new HttpEntity<QuestionOptionUser>(qo);
		ResponseEntity<QuestionOptionUser> response = restTemplate.exchange(createQuestionOptionUrl, HttpMethod.POST, request, QuestionOptionUser.class);
		
		log.debug("Create Survey call " + response.getStatusCode().toString());
		
		return response.getBody();		
	}
	
	public QuestionOption updateQuestionOption(QuestionOption questionOption){
		String baseApiUrl = props.getApiUrl();
		String updatequestionOptionUrl = baseApiUrl + "/questionOption/" + questionOption.getId_option();

		RestTemplate restTemplate = new RestTemplate();
		HttpEntity<QuestionOption> request = new HttpEntity<QuestionOption>(questionOption);
		ResponseEntity<QuestionOption> response = restTemplate.exchange(
				updatequestionOptionUrl, 
				HttpMethod.PUT, 
				request, 
				QuestionOption.class);
		
		log.debug("Update Employee call " + response.getStatusCode().toString());
		
		return response.getBody();
	}
}

