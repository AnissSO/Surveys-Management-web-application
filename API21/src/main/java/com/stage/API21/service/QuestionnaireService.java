package com.stage.API21.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stage.API21.model.QuestionOptionUser;
import com.stage.API21.model.QuestionUser;
import com.stage.API21.model.Questionnaire;
import com.stage.API21.model.QuestionnaireRempli;
import com.stage.API21.repository.QuestionOptionUserRepository;
import com.stage.API21.repository.QuestionUserRepository;
import com.stage.API21.repository.QuestionnaireRempliRepository;
import com.stage.API21.repository.QuestionnaireRepository;

import lombok.Data;

@Data
@Service
public class QuestionnaireService {

	@Autowired
	private QuestionnaireRepository questionnaireRepository;
	
	@Autowired
	private QuestionnaireRempliRepository questionnaireRempliRepository;
	
	@Autowired
	private QuestionUserRepository questionUserRepository;
	
	@Autowired 
	private QuestionOptionUserRepository questionOptionUserRepository;
	
	public Optional<Questionnaire> getQuestionnaire(final BigInteger id){
		return questionnaireRepository.findById(id);
	}
	
	public Optional<QuestionnaireRempli> getQuestionnaireRempli(final BigInteger id){
		return questionnaireRempliRepository.findById(id);
	}
	
	public Iterable<Questionnaire> getQuestionnaires(){
		return questionnaireRepository.findAll();
	}
	
	public Iterable<QuestionOptionUser> getQuestionOptionsUsers(){
		return questionOptionUserRepository.findAll();
	}
	
	public Iterable<QuestionUser> getQuestionUsers(){
		return questionUserRepository.trouvertous();
	}
	
	public void deleteQuestionnaire(final BigInteger id) {
		questionnaireRepository.deleteById(id);
	}
	
	public Questionnaire saveQuestionnaire(Questionnaire quest) {
		Questionnaire savedQuestionnaire = questionnaireRepository.save(quest);
		
		return savedQuestionnaire;
	}
	
}
