package com.stage.API21.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stage.API21.model.PageQuestionnaire;
import com.stage.API21.model.QuestionOption;



@Repository
public interface QuestionOptionRepository extends CrudRepository<QuestionOption, BigInteger>{
	
	@Query("select q from QuestionOption q where q.id_question = ?1")
    Iterable<QuestionOption> getOptionsQuestion(BigInteger idQuestion);
}
