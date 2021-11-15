package com.stage.API21.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "question_utilisateur")
public class QuestionUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id_question_utilisateur;
	
	private BigInteger id_question_origni;
	private BigInteger id_questionnaire_definition_page;
	private BigInteger id_Survey_filled;
	private short question_ordre;

	private String type;
	private String question_texte;
	
	private String indice;
	
}
