package com.stage.WebApp21.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.WebApp21.model.PageQuestionnaire;
import com.stage.WebApp21.model.QuestionOptionUser;
import com.stage.WebApp21.model.QuestionUser;
import com.stage.WebApp21.model.Questionnaire;
import com.stage.WebApp21.repository.QuestionnaireProxy;

import lombok.Data;

@Data
@Service
public class QuestionnaireService {

	@Autowired
	private QuestionnaireProxy questionnaireProxy;
	
	
	public Questionnaire getQuestionnaire(final BigInteger id) {
		return questionnaireProxy.getQuestionnaire(id);
	}
	
	public Iterable<Questionnaire> getQuestionnaires(){
		return questionnaireProxy.getQuestionnaires();
	}
	
	public Iterable<QuestionOptionUser> getQuestionOptionsUsers(){
		return questionnaireProxy.getQuestionOptionsUsers();
	}
	
	public Iterable<QuestionUser> getQuestionUsers(){
		return questionnaireProxy.getQuestionUsers();
	}
	
	public void deleteQuestionnaire(final BigInteger id) {
		questionnaireProxy.deleteQuestionnaire(id);
	}
	
	public Questionnaire saveQuestionnaire(Questionnaire questionnaire) {
		Questionnaire savedQuestionnaire;
		
		if(questionnaire.getId() == null) {
			savedQuestionnaire = questionnaireProxy.createQuestionnaire(questionnaire);
		}else {
			savedQuestionnaire = questionnaireProxy.updateQuestionnaire(questionnaire);
		}
		
		return savedQuestionnaire;
	}
	
	
}
