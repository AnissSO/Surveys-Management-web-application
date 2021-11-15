package com.stage.WebApp21.controller;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.stage.WebApp21.model.Option;
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
public class QuestionController {

	@Autowired 
	private PageQuestionnaireService questionnairePageService;
	
	@Autowired
	private QuestionnaireService questionnaireService;
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/createQuestion/{id}")
	public String createQuestion(@PathVariable("id") final BigInteger id, Model model) {
		Question q = new Question();
		q.setId_questionnaire_definition_page(id);
		System.out.println("1 --> " + q);
		PageQuestionnaire pageQuestionnaire = questionnairePageService.getPageQuestionnaire(id);
		BigInteger idQuestionnaire = pageQuestionnaire.getId_questionnaire_definition();
		Questionnaire questionnaire = questionnaireService.getQuestionnaire(idQuestionnaire); 
		
		BigInteger idpage = id;
		model.addAttribute("idPage", idpage);
		System.out.println(idpage);
		model.addAttribute("lapage", pageQuestionnaire);
		model.addAttribute("questionnaireDelapage", questionnaire);
		model.addAttribute("question", q);
		return "formNewQuestion";
	}
	
	@GetMapping("/createQuestionUser/{id}")
	public String createQuestionUser(@PathVariable("id") final BigInteger id, Model model) {
		Question q = new Question();
		q.setId_questionnaire_definition_page(id);
		System.out.println("1 --> " + q);
		PageQuestionnaire pageQuestionnaire = questionnairePageService.getPageQuestionnaire(id);
		BigInteger idQuestionnaire = pageQuestionnaire.getId_questionnaire_definition();
		Questionnaire questionnaire = questionnaireService.getQuestionnaire(idQuestionnaire); 
		
		BigInteger idpage = id;
		model.addAttribute("idPage", idpage);
		System.out.println(idpage);
		model.addAttribute("lapage", pageQuestionnaire);
		model.addAttribute("questionnaireDelapage", questionnaire);
		model.addAttribute("question", q);
		return "formNewQuestionUser";
	}
	
	@GetMapping("/updateOptionsQuestion/{id}")
	public String updateOptionsQuestion(@PathVariable("id") final BigInteger id, Model model) {
		Question q = questionService.getQuestion(id);
		model.addAttribute("questionUpdateOptions", q);
		Option o = new Option();
		
		
		
		Iterable<QuestionOption> listOptionsQuestion = questionService.getOptionsQuestion(id);
		if(listOptionsQuestion != null) {
			Iterator<QuestionOption> iterator = listOptionsQuestion.iterator();
			if(iterator.hasNext() == true) {
				o.setOption1(iterator.next().getOption_texte());
			}
			if(iterator.hasNext() == true) {
				o.setOption2(iterator.next().getOption_texte());
			}
			if(iterator.hasNext() == true) {
				o.setOption3(iterator.next().getOption_texte());
			}
			if(iterator.hasNext() == true) {
				o.setOption4(iterator.next().getOption_texte());
			}
			if(iterator.hasNext() == true) {
				o.setOption5(iterator.next().getOption_texte());
			}
			if(iterator.hasNext() == true) {
				o.setOption6(iterator.next().getOption_texte());
			}
		}
		
		o.setIdQuest(id);
		System.out.println(id);
		BigInteger idQuest = id;
		model.addAttribute("idquesti", idQuest);
		model.addAttribute("option", o);
		
		return "formUpdateOptionsQuestion";
	}
	
	@GetMapping("/updateOptionsQuestionUser/{id}")
	public String updateOptionsQuestionUser(@PathVariable("id") final BigInteger id, Model model) {
		Question q = questionService.getQuestion(id);
		model.addAttribute("questionUpdateOptions", q);
		Option o = new Option();
		
		
		
		Iterable<QuestionOption> listOptionsQuestion = questionService.getOptionsQuestion(id);
		if(listOptionsQuestion != null) {
			Iterator<QuestionOption> iterator = listOptionsQuestion.iterator();
			if(iterator.hasNext() == true) {
				o.setOption1(iterator.next().getOption_texte());
			}
			if(iterator.hasNext() == true) {
				o.setOption2(iterator.next().getOption_texte());
			}
			if(iterator.hasNext() == true) {
				o.setOption3(iterator.next().getOption_texte());
			}
			if(iterator.hasNext() == true) {
				o.setOption4(iterator.next().getOption_texte());
			}
			if(iterator.hasNext() == true) {
				o.setOption5(iterator.next().getOption_texte());
			}
			if(iterator.hasNext() == true) {
				o.setOption6(iterator.next().getOption_texte());
			}
		}
		
		o.setIdQuest(id);
		System.out.println(id);
		BigInteger idQuest = id;
		model.addAttribute("idquesti", idQuest);
		model.addAttribute("option", o);
		
		return "formUpdateOptionsQuestionUser";
	}
	
	 
	
	@PostMapping("/selectOptionsQuestion")
	public ModelAndView selectOptionsQuestion(@ModelAttribute Option option) {

		System.out.println("option 1 est : " + option.getOption1() + " et option 2 est : " + option.getOption2()
		+ " et option 3 est : " + option.getOption3());
		System.out.println(option.getIdQuest());
		Iterable<QuestionOption> listOptionsQuestion = questionService.getOptionsQuestion(option.getIdQuest());
		Iterator<QuestionOption> iterator = listOptionsQuestion.iterator();
		if(option.getOption1()!= "" ) {
			QuestionOption questionOption1 = new QuestionOption();
			questionOption1.setOption_texte(option.getOption1());
			questionOption1.setOption_ordre((short) 1);
			questionOption1.setId_question(option.getIdQuest());
			System.out.println(questionOption1);
			if(iterator.hasNext() == true) {
				questionOption1.setId_option(iterator.next().getId_option());
			}
			System.out.println(questionOption1);
			questionService.saveQuestionOption(questionOption1);
		}
		if(option.getOption2()!= "" ) {
			QuestionOption questionOption2 = new QuestionOption();
			questionOption2.setOption_texte(option.getOption2());
			questionOption2.setOption_ordre((short) 2);
			questionOption2.setId_question(option.getIdQuest());
			System.out.println(questionOption2);
			if(iterator.hasNext() == true) {
				questionOption2.setId_option(iterator.next().getId_option());
			}
				questionService.saveQuestionOption(questionOption2);
		}
		if(option.getOption3()!= "" ) {
			QuestionOption questionOption3 = new QuestionOption();
			questionOption3.setOption_texte(option.getOption3());
			questionOption3.setOption_ordre((short) 3);
			questionOption3.setId_question(option.getIdQuest());
			System.out.println(questionOption3);
			if(iterator.hasNext() == true) {
				questionOption3.setId_option(iterator.next().getId_option());
			}
			questionService.saveQuestionOption(questionOption3);
		}
		if(option.getOption4()!= "" ) {
			QuestionOption questionOption4 = new QuestionOption();
			questionOption4.setOption_texte(option.getOption4());
			questionOption4.setOption_ordre((short) 4);
			questionOption4.setId_question(option.getIdQuest());
			System.out.println(questionOption4);
			if(iterator.hasNext() == true) {
				questionOption4.setId_option(iterator.next().getId_option());
			}
			questionService.saveQuestionOption(questionOption4);
		}
		if(option.getOption5()!= "" ) {
			QuestionOption questionOption5 = new QuestionOption();
			questionOption5.setOption_texte(option.getOption5());
			questionOption5.setOption_ordre((short) 5);
			questionOption5.setId_question(option.getIdQuest());
			System.out.println(questionOption5);
			if(iterator.hasNext() == true) {
				questionOption5.setId_option(iterator.next().getId_option());
			}
			questionService.saveQuestionOption(questionOption5);
		}
		if(option.getOption6() != "" ) {
			QuestionOption questionOption6 = new QuestionOption();
			questionOption6.setOption_texte(option.getOption6());
			questionOption6.setOption_ordre((short) 6);
			questionOption6.setId_question(option.getIdQuest());
			System.out.println(questionOption6);
			if(iterator.hasNext() == true) {
				questionOption6.setId_option(iterator.next().getId_option());
			}
			questionService.saveQuestionOption(questionOption6);
		}
		
		Question question = questionService.getQuestion(option.getIdQuest());
		PageQuestionnaire pageQuestionnaire = questionnairePageService.getPageQuestionnaire(question.getId_questionnaire_definition_page());

		return new ModelAndView("redirect:/showQuestionnaire/" + pageQuestionnaire.getId_questionnaire_definition());
	}
	
	@PostMapping("/selectOptionsQuestionUser")
	public ModelAndView selectOptionsQuestionUser(@ModelAttribute Option option) {

		System.out.println("option 1 est : " + option.getOption1() + " et option 2 est : " + option.getOption2()
		+ " et option 3 est : " + option.getOption3());
		System.out.println(option.getIdQuest());
		Iterable<QuestionOption> listOptionsQuestion = questionService.getOptionsQuestion(option.getIdQuest());
		Iterator<QuestionOption> iterator = listOptionsQuestion.iterator();
		if(option.getOption1()!= "" ) {
			QuestionOption questionOption1 = new QuestionOption();
			questionOption1.setOption_texte(option.getOption1());
			questionOption1.setOption_ordre((short) 1);
			questionOption1.setId_question(option.getIdQuest());
			System.out.println(questionOption1);
			if(iterator.hasNext() == true) {
				questionOption1.setId_option(iterator.next().getId_option());
			}
			System.out.println(questionOption1);
			questionService.saveQuestionOption(questionOption1);
		}
		if(option.getOption2()!= "" ) {
			QuestionOption questionOption2 = new QuestionOption();
			questionOption2.setOption_texte(option.getOption2());
			questionOption2.setOption_ordre((short) 2);
			questionOption2.setId_question(option.getIdQuest());
			System.out.println(questionOption2);
			if(iterator.hasNext() == true) {
				questionOption2.setId_option(iterator.next().getId_option());
			}
				questionService.saveQuestionOption(questionOption2);
		}
		if(option.getOption3()!= "" ) {
			QuestionOption questionOption3 = new QuestionOption();
			questionOption3.setOption_texte(option.getOption3());
			questionOption3.setOption_ordre((short) 3);
			questionOption3.setId_question(option.getIdQuest());
			System.out.println(questionOption3);
			if(iterator.hasNext() == true) {
				questionOption3.setId_option(iterator.next().getId_option());
			}
			questionService.saveQuestionOption(questionOption3);
		}
		if(option.getOption4()!= "" ) {
			QuestionOption questionOption4 = new QuestionOption();
			questionOption4.setOption_texte(option.getOption4());
			questionOption4.setOption_ordre((short) 4);
			questionOption4.setId_question(option.getIdQuest());
			System.out.println(questionOption4);
			if(iterator.hasNext() == true) {
				questionOption4.setId_option(iterator.next().getId_option());
			}
			questionService.saveQuestionOption(questionOption4);
		}
		if(option.getOption5()!= "" ) {
			QuestionOption questionOption5 = new QuestionOption();
			questionOption5.setOption_texte(option.getOption5());
			questionOption5.setOption_ordre((short) 5);
			questionOption5.setId_question(option.getIdQuest());
			System.out.println(questionOption5);
			if(iterator.hasNext() == true) {
				questionOption5.setId_option(iterator.next().getId_option());
			}
			questionService.saveQuestionOption(questionOption5);
		}
		if(option.getOption6() != "" ) {
			QuestionOption questionOption6 = new QuestionOption();
			questionOption6.setOption_texte(option.getOption6());
			questionOption6.setOption_ordre((short) 6);
			questionOption6.setId_question(option.getIdQuest());
			System.out.println(questionOption6);
			if(iterator.hasNext() == true) {
				questionOption6.setId_option(iterator.next().getId_option());
			}
			questionService.saveQuestionOption(questionOption6);
		}
		
		Question question = questionService.getQuestion(option.getIdQuest());
		PageQuestionnaire pageQuestionnaire = questionnairePageService.getPageQuestionnaire(question.getId_questionnaire_definition_page());

		return new ModelAndView("redirect:/showQuestionnaireUser/" + pageQuestionnaire.getId_questionnaire_definition());
	}
	
	@GetMapping("/updateQuestion/{id}")
	public String updateQuestion(@PathVariable("id") final BigInteger id, Model model) {
		Question q = questionService.getQuestion(id);
		model.addAttribute("questionUpdateOptions", q);
		model.addAttribute("qId", q.getId_question());
		model.addAttribute("qpId", q.getId_questionnaire_definition_page());
		PageQuestionnaire pageQuestionnaire = questionnairePageService.getPageQuestionnaire(q.getId_questionnaire_definition_page());
		model.addAttribute("pageQuestionnaire", pageQuestionnaire);
		
		Questionnaire questionnaire = questionnaireService.getQuestionnaire(pageQuestionnaire.getId_questionnaire_definition());
		model.addAttribute("questionnaire", questionnaire);
		
		return "formUpdateQuestion";
	}
	
	@GetMapping("/updateQuestionUser/{id}")
	public String updateQuestionUser(@PathVariable("id") final BigInteger id, Model model) {
		Question q = questionService.getQuestion(id);
		model.addAttribute("questionUpdateOptions", q);
		model.addAttribute("qId", q.getId_question());
		model.addAttribute("qpId", q.getId_questionnaire_definition_page());
		PageQuestionnaire pageQuestionnaire = questionnairePageService.getPageQuestionnaire(q.getId_questionnaire_definition_page());
		model.addAttribute("pageQuestionnaire", pageQuestionnaire);
		
		Questionnaire questionnaire = questionnaireService.getQuestionnaire(pageQuestionnaire.getId_questionnaire_definition());
		model.addAttribute("questionnaire", questionnaire);
		
		return "formUpdateQuestionUser";
	}
	
	@PostMapping("/updateTextQuestion")
	public ModelAndView updateTextQuestion(@ModelAttribute Question questionUpdateOptions) {
		System.out.println(questionUpdateOptions);
		questionService.saveQuestion(questionUpdateOptions);
		
		PageQuestionnaire pageQuestionnaire = questionnairePageService.getPageQuestionnaire(questionUpdateOptions.getId_questionnaire_definition_page());
		
		return new ModelAndView("redirect:/showQuestionnaire/" + pageQuestionnaire.getId_questionnaire_definition());
	}
	
	@PostMapping("/updateTextQuestionUser")
	public ModelAndView updateTextQuestionUser(@ModelAttribute Question questionUpdateOptions) {
		System.out.println(questionUpdateOptions);
		questionService.saveQuestion(questionUpdateOptions);
		
		PageQuestionnaire pageQuestionnaire = questionnairePageService.getPageQuestionnaire(questionUpdateOptions.getId_questionnaire_definition_page());
		
		return new ModelAndView("redirect:/showQuestionnaireUser/" + pageQuestionnaire.getId_questionnaire_definition());
	}
	
	@GetMapping("/deleteQuestion/{id}")
	public ModelAndView deleteQuestion(@PathVariable("id") final BigInteger id) {
		Question q = questionService.getQuestion(id);
		Iterable<QuestionOption> listOptionsQuestion = questionService.getOptionsQuestion(id);
		
		PageQuestionnaire pageQuestionnaire = questionnairePageService.getPageQuestionnaire(q.getId_questionnaire_definition_page());
		
		if(listOptionsQuestion != null) {
			for(QuestionOption s: listOptionsQuestion){
				questionService.deleteQuestionOption(s.getId_option());
			}
		}
		questionService.deleteQuestion(id);
		
		return new ModelAndView("redirect:/showQuestionnaire/"+ pageQuestionnaire.getId_questionnaire_definition());	
	}
	
	@GetMapping("/deleteQuestionUser/{id}")
	public ModelAndView deleteQuestionUser(@PathVariable("id") final BigInteger id) {
		Question q = questionService.getQuestion(id);
		Iterable<QuestionOption> listOptionsQuestion = questionService.getOptionsQuestion(id);
		
		PageQuestionnaire pageQuestionnaire = questionnairePageService.getPageQuestionnaire(q.getId_questionnaire_definition_page());
		
		if(listOptionsQuestion != null) {
			for(QuestionOption s: listOptionsQuestion){
				questionService.deleteQuestionOption(s.getId_option());
			}
		}
		questionService.deleteQuestion(id);
		
		return new ModelAndView("redirect:/showQuestionnaireUser/"+ pageQuestionnaire.getId_questionnaire_definition());	
	}
	
	@PostMapping("/saveQuestion")
	public ModelAndView saveQuestion(@ModelAttribute Question question) {
		//q.setId_utilisateur(BigInteger.valueOf(1));
		//q.setDate_creation(LocalDateTime.now());
		//q.setStatus("I");
		Question q = question;
		System.out.println("2 --> " + q);
		
		Iterable<Question> listQuestionsDeLaPage = questionService.getQuestionsDeLaPage(q.getId_questionnaire_definition_page());
		int i = 0;
		if(listQuestionsDeLaPage != null) {
			for(Question e : listQuestionsDeLaPage) {
				i++;
			}
		}
		q.setQuestion_ordre((short)(i+1));
		
		questionService.saveQuestion(q);
		BigInteger idPage = q.getId_questionnaire_definition_page();
		PageQuestionnaire pageQ = questionnairePageService.getPageQuestionnaire(idPage);
		
		BigInteger idQuestionnaire = pageQ.getId_questionnaire_definition();
		
		System.out.println(question);
		return new ModelAndView("redirect:/showQuestionnaire/" + idQuestionnaire); 
	}
	
	@PostMapping("/saveQuestionUser")
	public ModelAndView saveQuestionUser(@ModelAttribute Question question) {
		//q.setId_utilisateur(BigInteger.valueOf(1));
		//q.setDate_creation(LocalDateTime.now());
		//q.setStatus("I");
		Question q = question;
		System.out.println("2 --> " + q);
		
		Iterable<Question> listQuestionsDeLaPage = questionService.getQuestionsDeLaPage(q.getId_questionnaire_definition_page());
		int i = 0;
		if(listQuestionsDeLaPage != null) {
			for(Question e : listQuestionsDeLaPage) {
				i++;
			}
		}
		q.setQuestion_ordre((short)(i+1));
		
		questionService.saveQuestion(q);
		BigInteger idPage = q.getId_questionnaire_definition_page();
		PageQuestionnaire pageQ = questionnairePageService.getPageQuestionnaire(idPage);
		
		BigInteger idQuestionnaire = pageQ.getId_questionnaire_definition();
		
		System.out.println(question);
		return new ModelAndView("redirect:/showQuestionnaireUser/" + idQuestionnaire); 
	}
}
