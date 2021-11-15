package com.stage.WebApp21.model;

import java.math.BigInteger;

import lombok.Data;

@Data
public class QuestionOptionUser {
	
	private BigInteger id_option_utilisateur;
	private BigInteger id_question_Opt;
	private BigInteger id_Quest_Rempli;
	private BigInteger id_question;
	private Integer option_ordre;
	private String option_texte;
	private String option_valeur;
	
}
