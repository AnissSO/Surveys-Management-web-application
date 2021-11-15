package com.stage.API21.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stage.API21.model.Question;

@Repository
public interface QuestionRepository extends CrudRepository<Question, BigInteger>{

	@Query("select q from Question q where q.id_questionnaire_definition_page = ?1")
    Iterable<Question> getQuestionsDeLaPage(BigInteger idPage);
}
