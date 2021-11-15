package com.stage.API21.service;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.API21.model.PageQuestionnaire;
import com.stage.API21.model.Questionnaire;
import com.stage.API21.model.QuestionnaireRempli;
import com.stage.API21.repository.PageQuestionnaireRepository;
import com.stage.API21.repository.QuestionnaireRempliRepository;

import lombok.Data;

@Data
@Service
public class PageQuestionnaireService {

	@Autowired
	private PageQuestionnaireRepository pageQuestionnaireRepository;
	
	@Autowired
	private QuestionnaireRempliRepository questionnaireRempliRepository;
	
	public Iterable<PageQuestionnaire> getQuestionnairePages(BigInteger idQuestionnaire){
		return pageQuestionnaireRepository.findPagesByIdQuestionnaire(idQuestionnaire);
	}
	
	public Iterable<QuestionnaireRempli> getQuestionnairesRemplis(BigInteger idQuestionnaire){
		return questionnaireRempliRepository.getQuestionnairesRemplis(idQuestionnaire);
	}
	
	public void deletePage(final BigInteger idPage) {
		pageQuestionnaireRepository.deleteById(idPage);
	}
	
	public Optional<PageQuestionnaire> getPageQuestionnaire(final BigInteger idPageQuestionnaire){
		return pageQuestionnaireRepository.findPageById(idPageQuestionnaire);
	}
	
	public PageQuestionnaire savePageQuestionnaire(PageQuestionnaire pageQues) {
		PageQuestionnaire savedPageQuestionnaire = pageQuestionnaireRepository.save(pageQues);
		return savedPageQuestionnaire;
	}
	
}
