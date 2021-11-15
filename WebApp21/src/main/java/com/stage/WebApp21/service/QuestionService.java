package com.stage.WebApp21.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.WebApp21.model.PageQuestionnaire;
import com.stage.WebApp21.model.Question;
import com.stage.WebApp21.model.QuestionOption;
import com.stage.WebApp21.model.QuestionOptionUser;
import com.stage.WebApp21.model.QuestionUser;
import com.stage.WebApp21.model.Questionnaire;
import com.stage.WebApp21.repository.QuestionProxy;

import lombok.Data;

@Data
@Service
public class QuestionService {

	@Autowired
	private QuestionProxy questionProxy;
	
	public Iterable<Question> getQuestions(){
		return questionProxy.getQuestions();
	}
	
	public Iterable<Question> getQuestionsDeLaPage(BigInteger idPage){
		return questionProxy.getQuestionsDeLaPage(idPage);
	}
	
	public Iterable<QuestionOption> getOptionsQuestion(BigInteger idQuestion){
		return questionProxy.getOptionsQuestion(idQuestion);
	}
	
	public Iterable<QuestionOption> getQuestionOptions(){
		return questionProxy.getQuestionOptions();
	}
	
	public void deleteQuestion(final BigInteger idQuestion) {
		questionProxy.deleteQuestion(idQuestion);
	}
	
	public void deleteQuestionOption(final BigInteger idQuestionOption) {
		questionProxy.deleteQuestionOption(idQuestionOption);
	}
	
	
	public Question getQuestion(final BigInteger idQuestion) {
		return questionProxy.getQuestion(idQuestion);
	}
	
	public Question saveQuestion(Question question) {
		Question savedQuestion;
		
		if(question.getId_question() == null) {
			savedQuestion = questionProxy.createQuestion(question);
		}
		else {
			savedQuestion = questionProxy.updateQuestion(question);
		}
		
		return savedQuestion;
	}
	
	public QuestionUser saveQuestionUser(QuestionUser questionUser) {
		QuestionUser savedQuestion;
		
		
			savedQuestion = questionProxy.createQuestionUser(questionUser);
		
		
		return savedQuestion;
	}
	
	public QuestionOption saveQuestionOption(QuestionOption questionOption) {
		QuestionOption savedQuestionOption;
		
		if(questionOption.getId_option() == null) {
		savedQuestionOption = questionProxy.createQuestionOption(questionOption);
		}
		else {
			savedQuestionOption = questionProxy.updateQuestionOption(questionOption);
		}
		
		return savedQuestionOption;
	}
	
	public QuestionOptionUser saveQuestionOptionUser(QuestionOptionUser questionOption) {
		QuestionOptionUser savedQuestionOption;
		
		
		savedQuestionOption = questionProxy.createQuestionOptionUser(questionOption);
		
		
		return savedQuestionOption;
	}
}
