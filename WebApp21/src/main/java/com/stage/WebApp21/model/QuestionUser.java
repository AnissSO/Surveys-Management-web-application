package com.stage.WebApp21.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class QuestionUser {

	private BigInteger id_question_utilisateur;
	private BigInteger id_question_origni;
	private BigInteger id_questionnaire_definition_page;
	private BigInteger id_Survey_filled;
	private short question_ordre;

	private String type;
	private String question_texte;
	
	private String indice;
	
	
}
