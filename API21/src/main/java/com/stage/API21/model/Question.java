package com.stage.API21.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "question")
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id_question;
	
	private BigInteger id_questionnaire_definition_page;
	
	private short question_ordre;
	
	private String type;
	private String question_texte;
	
	private String indice;
	
}
