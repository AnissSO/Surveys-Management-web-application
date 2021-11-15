package com.stage.API21.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stage.API21.model.PageQuestionnaire;
import com.stage.API21.model.QuestionnaireRempli;

@Repository
public interface QuestionnaireRempliRepository extends CrudRepository<QuestionnaireRempli, BigInteger>{

	@Query("select q from QuestionnaireRempli q where q.id_questionnaire_definition = ?1")
    Iterable<QuestionnaireRempli> getQuestionnairesRemplis(BigInteger idQuestionnaire);
	
}
