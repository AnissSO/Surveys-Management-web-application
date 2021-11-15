package com.stage.API21.service;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.API21.model.PageQuestionnaire;
import com.stage.API21.model.Question;
import com.stage.API21.model.QuestionOption;
import com.stage.API21.model.QuestionOptionUser;
import com.stage.API21.repository.QuestionOptionRepository;
import com.stage.API21.repository.QuestionOptionUserRepository;

import lombok.Data;

@Data
@Service
public class QuestionOptionService {

	@Autowired QuestionOptionRepository questionOptionRepository;
	
	@Autowired QuestionOptionUserRepository questionOptionUserRepository;
	
	public Iterable<QuestionOption> getOptionsQuestion(BigInteger idQuestion){
		return questionOptionRepository.getOptionsQuestion(idQuestion);
	}
	
	
	
	
	public Optional<QuestionOption> getQuestionOption(final BigInteger idQuestionOption){
		return questionOptionRepository.findById(idQuestionOption);
	}
	
	public Iterable<QuestionOption> getQuestionOptions(){
		return questionOptionRepository.findAll();
	}
	
	public void deleteQuestionOption(final BigInteger idQuestionOption) {
		questionOptionRepository.deleteById(idQuestionOption);
	}
	
	public QuestionOption saveQuestionOption(QuestionOption questionOption) {
		QuestionOption savedQuestionOption = questionOptionRepository.save(questionOption);
		
		return savedQuestionOption;
	}
	
	public QuestionOptionUser saveQuestionOptionUser(QuestionOptionUser questionOption) {
		QuestionOptionUser savedQuestionOption = questionOptionUserRepository.save(questionOption);
		
		return savedQuestionOption;
	}
}
