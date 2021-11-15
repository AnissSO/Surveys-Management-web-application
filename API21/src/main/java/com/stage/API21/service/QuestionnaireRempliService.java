package com.stage.API21.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.stage.API21.model.Questionnaire;
import com.stage.API21.model.QuestionnaireRempli;
import com.stage.API21.repository.QuestionnaireRempliRepository;
import com.stage.API21.repository.QuestionnaireRepository;

import lombok.Data;

@Data
@Service
public class QuestionnaireRempliService {

	@Autowired
	private QuestionnaireRempliRepository questionnaireRempliRepository;
	
	public QuestionnaireRempli saveQuestionnaireRempli(QuestionnaireRempli quest) {
		QuestionnaireRempli savedQuestionnaire = questionnaireRempliRepository.save(quest);
		
		return savedQuestionnaire;
	}
	
	
	
	@GetMapping("/questionnairesRemplis")
	public Iterable<QuestionnaireRempli> getQuestionnairesRemplis(){
		return questionnaireRempliRepository.findAll();
	}
}
