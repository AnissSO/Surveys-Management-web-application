package com.stage.API21.repository;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stage.API21.model.Question;
import com.stage.API21.model.Utilisateur;

@Repository
public interface UtilisateurRepository extends CrudRepository<Utilisateur, BigInteger>{

	@Query("select q from Utilisateur q where q.email = ?1")
	Optional<Utilisateur> findUserByEmail(String emailUser);
}
