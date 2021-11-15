package com.stage.WebApp21.service;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.WebApp21.model.PageQuestionnaire;
import com.stage.WebApp21.model.Questionnaire;
import com.stage.WebApp21.model.QuestionnaireRempli;
import com.stage.WebApp21.repository.PageQuestionnaireProxy;
import com.stage.WebApp21.repository.QuestionnaireProxy;

import lombok.Data;


@Data
@Service
public class PageQuestionnaireService {

	@Autowired
	private PageQuestionnaireProxy pageQuestionnaireProxy;
	
	public void deletePage(final BigInteger idPage) {
		pageQuestionnaireProxy.deletePage(idPage);
	}
	
	//obtenir les questionnaires remplis du questionnaire
	public Iterable<QuestionnaireRempli> getQuestionnairesRemplis(BigInteger idQuestionnaire){
		return pageQuestionnaireProxy.getQuestionnairesRemplis(idQuestionnaire);
	}
	
	public Iterable<PageQuestionnaire> getQuestionnairePages(BigInteger idQuestionnaire){
		return pageQuestionnaireProxy.getQuestionnairePages(idQuestionnaire);
	}
	
	public PageQuestionnaire getPageQuestionnaire(final BigInteger idPageQuestionnaire) {
		return pageQuestionnaireProxy.getPageQuestionnaire(idPageQuestionnaire);
	}
	
	public PageQuestionnaire savePageQuestionnaire(PageQuestionnaire pageQuestionnaire) {
		PageQuestionnaire savedPageQuestionnaire;
		
		if(pageQuestionnaire.getId_questionnaire_definition_page() == null) {
			savedPageQuestionnaire = pageQuestionnaireProxy.createPageQuestionnaire(pageQuestionnaire);
		}else {
			savedPageQuestionnaire = pageQuestionnaireProxy.updatePageQuestionnaire(pageQuestionnaire);
		}
			
		return savedPageQuestionnaire;
	}
	
}
