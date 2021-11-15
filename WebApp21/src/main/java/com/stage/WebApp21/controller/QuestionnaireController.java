package com.stage.WebApp21.controller;


import java.math.BigInteger;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stage.WebApp21.model.PageQuestionnaire;
import com.stage.WebApp21.model.Question;
import com.stage.WebApp21.model.QuestionOption;
import com.stage.WebApp21.model.Questionnaire;
import com.stage.WebApp21.service.PageQuestionnaireService;
import com.stage.WebApp21.service.QuestionService;
import com.stage.WebApp21.service.QuestionnaireService;

import lombok.Data;

@Data
@Controller
public class QuestionnaireController {

	@Autowired
	private QuestionnaireService questionnaireService;
	
	@Autowired 
	private PageQuestionnaireService questionnairePageService;
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/")
	public String home(Model model) {
		Iterable<Questionnaire> listQuestionnaire = questionnaireService.getQuestionnaires();
		model.addAttribute("questionnaires", listQuestionnaire);
		return "home";
	}
	
	
	@GetMapping("/createQuestionnaireAdmin")
	public String createQuestionnaireAdmin(Model model) {
		Questionnaire q = new Questionnaire();
		q.setId_utilisateur(BigInteger.valueOf(1));
		model.addAttribute("idUser", q.getId_utilisateur());
		model.addAttribute("questionnaire", q);
		return "formNewQuestionnaireAdmin";
	}
	
	
	@GetMapping("/createQuestionnaire/{id}")
	public String createQuestionnaire(Model model, @PathVariable("id") final BigInteger id) {
		Questionnaire q = new Questionnaire();
		q.setId_utilisateur(id);
		model.addAttribute("idUser", id);
		model.addAttribute("questionnaire", q);
		return "formNewQuestionnaire";
	}
	
	
	
	@GetMapping("/deleteQuestionnaire/{id}")
	public ModelAndView deleteQuestionnaire(@PathVariable("id") final BigInteger id) {
		System.out.println("ID est : " + id);
		
		Questionnaire q = questionnaireService.getQuestionnaire(id);
		Iterable<PageQuestionnaire> pagesDuQuestionnaire = questionnairePageService.getQuestionnairePages(id);
		if(pagesDuQuestionnaire != null) {
			for(PageQuestionnaire pq : pagesDuQuestionnaire) {
				Iterable<Question> listQuestionsDeLaPage = questionService.getQuestionsDeLaPage(pq.getId_questionnaire_definition_page());
				if(listQuestionsDeLaPage != null) {
					for(Question f : listQuestionsDeLaPage) {
						Iterable<QuestionOption> listOptionsDeQuestion = questionService.getOptionsQuestion(f.getId_question());
						if(listOptionsDeQuestion != null) {
							for(QuestionOption g : listOptionsDeQuestion) {
								questionService.deleteQuestionOption(g.getId_option());
							}
						}
						questionService.deleteQuestion(f.getId_question());
					}
				}
				questionnairePageService.deletePage(pq.getId_questionnaire_definition_page());
			}
		}
		questionnaireService.deleteQuestionnaire(id);	
		return new ModelAndView("redirect:/");
		
		
	}


	@GetMapping("/deleteQuestionnaireUser/{id}")
	public ModelAndView deleteQuestionnaireUser(@PathVariable("id") final BigInteger id) {
		System.out.println("ID est : " + id);
		
		Questionnaire q = questionnaireService.getQuestionnaire(id);
		Iterable<PageQuestionnaire> pagesDuQuestionnaire = questionnairePageService.getQuestionnairePages(id);
		if(pagesDuQuestionnaire != null) {
			for(PageQuestionnaire pq : pagesDuQuestionnaire) {
				Iterable<Question> listQuestionsDeLaPage = questionService.getQuestionsDeLaPage(pq.getId_questionnaire_definition_page());
				if(listQuestionsDeLaPage != null) {
					for(Question f : listQuestionsDeLaPage) {
						Iterable<QuestionOption> listOptionsDeQuestion = questionService.getOptionsQuestion(f.getId_question());
						if(listOptionsDeQuestion != null) {
							for(QuestionOption g : listOptionsDeQuestion) {
								questionService.deleteQuestionOption(g.getId_option());
							}
						}
						questionService.deleteQuestion(f.getId_question());
					}
				}
				questionnairePageService.deletePage(pq.getId_questionnaire_definition_page());
			}
		}
		questionnaireService.deleteQuestionnaire(id);
		return new ModelAndView("redirect:/homeUser/" + q.getId_utilisateur());
		
	}
	
	@PostMapping("/saveQuestionnaire")
	public ModelAndView saveQuestionnaire(@ModelAttribute Questionnaire questionnaire) {
		System.out.println(questionnaire);
		Questionnaire q = questionnaire;
		System.out.println("hhhhhhhhhhhhhh");
		//q.setId_utilisateur(BigInteger.valueOf(1));
		q.setDate_creation(LocalDateTime.now());
		q.setStatus("I");
		questionnaireService.saveQuestionnaire(q);
		
		System.out.println(q);
		if(q.getId_utilisateur() == BigInteger.valueOf(1)) {
			return new ModelAndView("redirect:/"); 
		}
		else {
			return new ModelAndView("redirect:/homeUser/" + q.getId_utilisateur()); 
		}
		
	}
	
	
	
	@PostMapping("/saveQuestionnaireAdmin")
	public ModelAndView saveQuestionnaireAdmin(@ModelAttribute Questionnaire questionnaire) {
		System.out.println(questionnaire);
		Questionnaire q = questionnaire;
		System.out.println("hhhhhhhhhhhhhh");
		//q.setId_utilisateur(BigInteger.valueOf(1));
		q.setDate_creation(LocalDateTime.now());
		q.setStatus("I");
		questionnaireService.saveQuestionnaire(q);
		
		System.out.println(q);
			return new ModelAndView("redirect:/"); 
		
		
	}
	
	@GetMapping("/updateQuestionnaire/{id}")
	public String updateQuestionnaire(@PathVariable("id") final BigInteger id, Model model) {
		Questionnaire q = questionnaireService.getQuestionnaire(id);
		model.addAttribute("questionnaire", q);
		model.addAttribute("qId", q.getId());
		/*
		model.addAttribute("qpId", q.getId_questionnaire_definition_page());
		PageQuestionnaire pageQuestionnaire = questionnairePageService.getPageQuestionnaire(q.getId_questionnaire_definition_page());
		model.addAttribute("pageQuestionnaire", pageQuestionnaire);
		
		Questionnaire questionnaire = questionnaireService.getQuestionnaire(pageQuestionnaire.getId_questionnaire_definition());
		model.addAttribute("questionnaire", questionnaire);*/
		
		return "formUpdateQuestionnaire";
	}
	
	
	@GetMapping("/updateQuestionnaireUser/{id}")
	public String updateQuestionnaireUser(@PathVariable("id") final BigInteger id, Model model) {
		Questionnaire q = questionnaireService.getQuestionnaire(id);
		model.addAttribute("questionnaire", q);
		model.addAttribute("qId", q.getId());
		model.addAttribute("idUser", q.getId_utilisateur());
		/*
		model.addAttribute("qpId", q.getId_questionnaire_definition_page());
		PageQuestionnaire pageQuestionnaire = questionnairePageService.getPageQuestionnaire(q.getId_questionnaire_definition_page());
		model.addAttribute("pageQuestionnaire", pageQuestionnaire);
		
		Questionnaire questionnaire = questionnaireService.getQuestionnaire(pageQuestionnaire.getId_questionnaire_definition());
		model.addAttribute("questionnaire", questionnaire);*/
		
		return "formUpdateQuestionnaireUser";
	}
	
	@GetMapping("/publishQuestionnaire/{id}")
	public ModelAndView publishQuestionnaire(@PathVariable("id") final BigInteger id) {
		Questionnaire q = questionnaireService.getQuestionnaire(id);
		q.setStatus("P");
		questionnaireService.saveQuestionnaire(q);
		
		return new ModelAndView("redirect:/showQuestionnaire/" + q.getId());
	}
	
	@PostMapping("/updateTextQuestionnaire")
	public ModelAndView updateTextQuestionnaire(@ModelAttribute Questionnaire questionnaire) {
		System.out.println(questionnaire);
		questionnaireService.saveQuestionnaire(questionnaire);
		
		
		return new ModelAndView("redirect:/showQuestionnaire/" + questionnaire.getId());
	}
	
	@PostMapping("/updateTextQuestionnaireUser")
	public ModelAndView updateTextQuestionnaireUser(@ModelAttribute Questionnaire questionnaire) {
		System.out.println(questionnaire);
		questionnaireService.saveQuestionnaire(questionnaire);
		
		
		return new ModelAndView("redirect:/showQuestionnaireUser/" + questionnaire.getId());
	}
	
}
