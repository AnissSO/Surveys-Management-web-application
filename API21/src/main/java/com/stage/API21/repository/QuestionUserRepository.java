package com.stage.API21.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stage.API21.model.QuestionOption;
import com.stage.API21.model.QuestionUser;

@Repository
public interface QuestionUserRepository extends CrudRepository<QuestionUser, BigInteger>{

	
	@Query("select q from QuestionUser q")
    Iterable<QuestionUser> trouvertous();
}
