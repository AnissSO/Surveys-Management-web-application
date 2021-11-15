package com.stage.API21.model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "question_option_utilisateur")
public class QuestionOptionUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id_option_utilisateur;
	
	private Integer option_ordre;
	private BigInteger id_question_Opt;
	private BigInteger id_Quest_Rempli;
	private BigInteger id_question;
	private String option_texte;
	private String option_valeur;

}
