package com.stage.API21.controller;

import java.math.BigInteger;
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
import com.stage.API21.model.Question;
import com.stage.API21.model.QuestionOption;
import com.stage.API21.model.QuestionOptionUser;
import com.stage.API21.model.QuestionUser;
import com.stage.API21.model.Questionnaire;
import com.stage.API21.service.QuestionOptionService;
import com.stage.API21.service.QuestionService;

@RestController
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuestionOptionService questionOptionService;
	
	
	
	@GetMapping("/questionsDeLaPage/{id}")
	public Iterable<Question> getQuestionsDeLaPage(@PathVariable("id") final BigInteger idPage){
		return questionService.getQuestionsDeLaPage(idPage);
	}
	
	@GetMapping("/questionOptions/{idQuestion}")
	public Iterable<QuestionOption> getOptionsQuestion(@PathVariable("idQuestion") final BigInteger idQuestion){
		return questionOptionService.getOptionsQuestion(idQuestion);
	}
	
	@DeleteMapping("/question/{id}")
	public void deleteQuestion(@PathVariable("id") final BigInteger id) {
		questionService.deleteQuestion(id);
	}
	
	@DeleteMapping("/questionOption/{id}")
	public void deleteQuestionOption(@PathVariable("id") final BigInteger id) {
		questionOptionService.deleteQuestionOption(id);
	}
	
	@PutMapping("/questionOption/{id}")
	public QuestionOption updateQuestionOption(@PathVariable("id") final BigInteger idQuestionOption, @RequestBody QuestionOption questionOption) {
		Optional<QuestionOption> q = questionOptionService.getQuestionOption(idQuestionOption);
		if(q.isPresent()) {
			QuestionOption currentQuestionOption = q.get();
			
			String optionTexte = questionOption.getOption_texte();
			if(optionTexte != null) {
				currentQuestionOption.setOption_texte(optionTexte);
			}
			
			Short optionOrdre = questionOption.getOption_ordre();
			if(optionOrdre != null) {
				currentQuestionOption.setOption_ordre(optionOrdre);
			}
			
			BigInteger idQuestion = questionOption.getId_question();
			if(idQuestion != null) {
				currentQuestionOption.setId_question(idQuestion);
			}
			questionOptionService.saveQuestionOption(currentQuestionOption);
			return currentQuestionOption;
		}else {
			return null;
		}
	}
	
	@PutMapping("/question/{id}")
	public Question updateQuestion(@PathVariable("id") final BigInteger id, @RequestBody Question question) {
		Optional<Question> q = questionService.getQuestion(id);
		if(q.isPresent()) {
			Question currentQuestion = q.get();
			
			String questionTexte = question.getQuestion_texte();
			if(questionTexte != null) {
				currentQuestion.setQuestion_texte(questionTexte);
			}
			System.out.println(currentQuestion);
			questionService.saveQuestion(currentQuestion);
			return currentQuestion;
		}else{
			return null;
		}
	}
	
	@GetMapping("/question/{idQuestion}")
	public Question getPageQuestionnaire(@PathVariable("idQuestion") final BigInteger idQuestion) {
		Optional<Question> question = questionService.getQuestion(idQuestion);
		if(question.isPresent()) {
			return question.get();
		}
		else {
			return null;
		}
	}
	
	@GetMapping("/questions")
	public Iterable<Question> getQuestions(){
		return questionService.getQuestions();
	}
	
	@GetMapping("/questionOptions")
	public Iterable<QuestionOption> getQuestionOptions(){
		return questionOptionService.getQuestionOptions();
	}
	
	@PostMapping("/question")
	public Question createQuestion(@RequestBody Question question) {
		return questionService.saveQuestion(question);
	}
	
	@PostMapping("/questionUser")
	public QuestionUser createQuestionUser(@RequestBody QuestionUser question) {
		return questionService.saveQuestionUser(question);
	}

	@PostMapping("/questionOption")
	public QuestionOption createQuestionOption(@RequestBody QuestionOption questionOption) {
		return questionOptionService.saveQuestionOption(questionOption);
	}
	
	
	@PostMapping("/questionOptionUser")
	public QuestionOptionUser createQuestionOptionUser(@RequestBody QuestionOptionUser questionOption) {
		return questionOptionService.saveQuestionOptionUser(questionOption);
	}
}
