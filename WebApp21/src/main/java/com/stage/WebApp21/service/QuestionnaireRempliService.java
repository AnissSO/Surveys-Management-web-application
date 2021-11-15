package com.stage.WebApp21.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.WebApp21.model.Questionnaire;
import com.stage.WebApp21.model.QuestionnaireRempli;
import com.stage.WebApp21.repository.QuestionnaireProxy;
import com.stage.WebApp21.repository.QuestionnaireRempliProxy;

import lombok.Data;

@Data
@Service
public class QuestionnaireRempliService {

	@Autowired
	private QuestionnaireRempliProxy questionnaireRempliProxy;
	
	public QuestionnaireRempli getQuestionnaireRempli(final BigInteger id) {
		return questionnaireRempliProxy.getQuestionnaireRempli(id);
	}
	
	public Iterable<QuestionnaireRempli> getQuestionnairesRemplis(){
		return questionnaireRempliProxy.getQuestionnairesRemplis();
	}
	
	public QuestionnaireRempli saveQuestionnaireRempli(QuestionnaireRempli questionnaireRempli) {
		QuestionnaireRempli savedQuestionnaire;
		
		savedQuestionnaire = questionnaireRempliProxy.createQuestionnaireRempli(questionnaireRempli);
		
		return savedQuestionnaire;
	}
	
}
