package com.stage.API21.controller;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stage.API21.service.PageQuestionnaireService;
import com.stage.API21.service.QuestionnaireRempliService;
import com.stage.API21.service.QuestionnaireService;
import com.stage.API21.model.PageQuestionnaire;
import com.stage.API21.model.Question;
import com.stage.API21.model.Questionnaire;
import com.stage.API21.model.QuestionnaireRempli;

@RestController
public class PageQuestionnaireController {
	
	@Autowired
	private PageQuestionnaireService pageQuestionnaireService;
	
	@Autowired
	private QuestionnaireRempliService questionnaireRempliService;
	
	@DeleteMapping("/page/{id}")
	public void deletePage(@PathVariable("id") final BigInteger id) {
		pageQuestionnaireService.deletePage(id);
	}
	
	@PostMapping("/questionnaireRempli")
	public QuestionnaireRempli createQuestionnaireRempli(@RequestBody QuestionnaireRempli questionnaireRempli) {
		return questionnaireRempliService.saveQuestionnaireRempli(questionnaireRempli);
	}
	
	@GetMapping("/questionnairePage/{idPageQuestionnaire}")
	public PageQuestionnaire getPageQuestionnaire(@PathVariable("idPageQuestionnaire") final BigInteger idPageQuestionnaire) {
		Optional<PageQuestionnaire> pageQuestionnaire = pageQuestionnaireService.getPageQuestionnaire(idPageQuestionnaire);
		if(pageQuestionnaire.isPresent()) {
			return pageQuestionnaire.get();
		}
		else {
			return null;
		}
	}
	
	@PutMapping("/pageQuestionnaire/{id}")
	public PageQuestionnaire updatePageQuestionnaire(@PathVariable("id") final BigInteger id, @RequestBody PageQuestionnaire pageQuestionnaire) {
		Optional<PageQuestionnaire> pq = pageQuestionnaireService.getPageQuestionnaire(id);
		if(pq.isPresent()) {
			PageQuestionnaire currentPage = pq.get();
			
			String pageTitre = pageQuestionnaire.getTitre();
			if(pageTitre != null) {
				currentPage.setTitre(pageTitre);
			}
			String pageInstructions = pageQuestionnaire.getInstructions();
			if(pageInstructions != null) {
				currentPage.setInstructions(pageInstructions);
			}
			
			System.out.println(currentPage);
			pageQuestionnaireService.savePageQuestionnaire(currentPage);
			return currentPage;
		}else{
			return null;
		}
	}
	
	@GetMapping("/questionnairePages/{idQuestionnaire}")
	public Iterable<PageQuestionnaire> getQuestionnairePages(@PathVariable("idQuestionnaire") final BigInteger idQuestionnaire){
		return pageQuestionnaireService.getQuestionnairePages(idQuestionnaire);
	}
	
	@GetMapping("/questionnairesRempli/{idQuestionnaire}")
	public Iterable<QuestionnaireRempli> getQuestionnairesRemplis(@PathVariable("idQuestionnaire") final BigInteger idQuestionnaire){
		return pageQuestionnaireService.getQuestionnairesRemplis(idQuestionnaire);
	}
	
	@PostMapping("/pageQuestionnaire")
	public PageQuestionnaire createPageQuestionnaire(@RequestBody PageQuestionnaire pageQuestionnaire) {
		return pageQuestionnaireService.savePageQuestionnaire(pageQuestionnaire);
	}
	
	
}
