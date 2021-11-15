package com.stage.API21.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stage.API21.model.Questionnaire;


@Repository
public interface QuestionnaireRepository extends CrudRepository<Questionnaire, BigInteger>{
	
	
}
