package com.stage.API21.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stage.API21.model.PageQuestionnaire;
import com.stage.API21.model.QuestionOptionUser;
import com.stage.API21.model.QuestionUser;
import com.stage.API21.model.Questionnaire;
import com.stage.API21.model.QuestionnaireRempli;
import com.stage.API21.service.QuestionnaireRempliService;
import com.stage.API21.service.QuestionnaireService;

@RestController
public class QuestionnaireController {
	
	@Autowired
	private QuestionnaireService questionnaireService;
	
	@Autowired
	private QuestionnaireRempliService questionnaireRempliService;
	
	@PostMapping("/questionnaire")
	public Questionnaire createQuestionnaire(@RequestBody Questionnaire questionnaire) {
		return questionnaireService.saveQuestionnaire(questionnaire);
	}
	
	@GetMapping("/questionnaire/{id}")
	public Questionnaire getQuestionnaire(@PathVariable("id") final BigInteger id) {
		Optional<Questionnaire> questionnaire = questionnaireService.getQuestionnaire(id);
		if(questionnaire.isPresent()) {
			return questionnaire.get();
		}
		else {
			return null;
		}
	}
	
	@GetMapping("/questionnaireRempli/{id}")
	public QuestionnaireRempli getQuestionnaireRempli(@PathVariable("id") final BigInteger id) {
		Optional<QuestionnaireRempli> questionnaire = questionnaireService.getQuestionnaireRempli(id);
		if(questionnaire.isPresent()) {
			return questionnaire.get();
		}
		else {
			return null;
		}
	}
	
	
	@GetMapping("/questionnairesRemplis")
	public Iterable<QuestionnaireRempli> getQuestionnairesRemplis(){
		return questionnaireRempliService.getQuestionnairesRemplis();
	}
	
	@GetMapping("/questionnaires")
	public Iterable<Questionnaire> getQuestionnaires(){
		return questionnaireService.getQuestionnaires();
	}
	
	@GetMapping("/questionOptionsUsers")
	public Iterable<QuestionOptionUser> getQuestionOptionsUsers(){
		return questionnaireService.getQuestionOptionsUsers();
	}
	
	
	@GetMapping("/questionUsers")
	public Iterable<QuestionUser> getQuestionUsers(){
		return questionnaireService.getQuestionUsers();
	}
	
	@PutMapping("/questionnaire/{id}")
	public Questionnaire updateQuestionnaire(@PathVariable("id") final BigInteger id, @RequestBody Questionnaire questionnaire) {
		Optional<Questionnaire> q = questionnaireService.getQuestionnaire(id);
		if(q.isPresent()) {
			Questionnaire currentQuestionnaire = q.get();
			
			String descriptionQuest = questionnaire.getDescription();
			if(descriptionQuest != null) {
				currentQuestionnaire.setDescription(descriptionQuest);
			}
			
			String nomQuest = questionnaire.getNom();
			if(nomQuest != null) {
				currentQuestionnaire.setNom(nomQuest);
			}
			
			String status = questionnaire.getStatus();
			if(status != null) {
				currentQuestionnaire.setStatus(status);
			}
			
			int autoriserPlusieurs = questionnaire.getAutoriser_plusieurs_soumissions();
			
			currentQuestionnaire.setAutoriser_plusieurs_soumissions(autoriserPlusieurs);
			
			
			int estPublic = questionnaire.getEst_public();
			currentQuestionnaire.setEst_public(estPublic);
			
			questionnaireService.saveQuestionnaire(currentQuestionnaire);
			return currentQuestionnaire;
			
		}else {
			return null;
		}
	} 
	
	
	@DeleteMapping("/questionnaire/{id}")
	public void deleteQuestionnaire(@PathVariable("id") final BigInteger id) {
		questionnaireService.deleteQuestionnaire(id);
	}
	
	
	
	
}
