package com.stage.API21.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stage.API21.model.PageQuestionnaire;

@Repository
public interface PageQuestionnaireRepository extends CrudRepository<PageQuestionnaire, BigInteger>{

	@Query("select q from PageQuestionnaire q where q.id_questionnaire_definition = ?1")
    Iterable<PageQuestionnaire> findPagesByIdQuestionnaire(BigInteger idQuestionnaire);
	
	@Query("select q from PageQuestionnaire q where q.id_questionnaire_definition_page = ?1")
	Optional<PageQuestionnaire> findPageById(BigInteger idPageQuestionnaire);
	
}
