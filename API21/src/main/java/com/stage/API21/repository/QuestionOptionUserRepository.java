package com.stage.API21.repository;

import java.math.BigInteger;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stage.API21.model.Question;
import com.stage.API21.model.QuestionOptionUser;

@Repository
public interface QuestionOptionUserRepository extends CrudRepository<QuestionOptionUser, BigInteger>{

}
