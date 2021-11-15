package com.stage.WebApp21.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Question {
	
	private BigInteger id_question;
	private BigInteger id_questionnaire_definition_page;
	
	private short question_ordre;
	
	private String type;
	private String question_texte;
	
	private String indice;
	
}
