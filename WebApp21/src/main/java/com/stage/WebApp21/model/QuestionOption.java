package com.stage.WebApp21.model;


import java.math.BigInteger;

import lombok.Data;

@Data
public class QuestionOption {

	private BigInteger id_option;
	private short option_ordre;
	private BigInteger id_question;
	private String option_texte;
	private String option_valeur;
}
