package com.stage.API21.service;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.API21.model.PageQuestionnaire;
import com.stage.API21.model.Question;
import com.stage.API21.model.QuestionOption;
import com.stage.API21.model.QuestionUser;
import com.stage.API21.model.Questionnaire;
import com.stage.API21.repository.QuestionRepository;
import com.stage.API21.repository.QuestionUserRepository;

import lombok.Data;

@Data
@Service
public class QuestionService {
	
	@Autowired QuestionRepository questionRepository;
	
	@Autowired QuestionUserRepository questionUserRepository;
	
	public Iterable<Question> getQuestions(){
		return questionRepository.findAll();
	}
	
	public Iterable<Question> getQuestionsDeLaPage(BigInteger idPage){
		return questionRepository.getQuestionsDeLaPage(idPage);
	}
	
	public Optional<Question> getQuestion(final BigInteger idQuestion){
		return questionRepository.findById(idQuestion);
	}
	
	public void deleteQuestion(final BigInteger idQuestion) {
		questionRepository.deleteById(idQuestion);
	}
	
	public Question saveQuestion(Question quest) {
		Question savedQuestion = questionRepository.save(quest);
		
		return savedQuestion;
	}
	
	public QuestionUser saveQuestionUser(QuestionUser quest) {
		QuestionUser savedQuestion = questionUserRepository.save(quest);
		
		return savedQuestion;
	}

}
