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
@Table(name = "question_option")
public class QuestionOption {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id_option;
	
	private short option_ordre;
	private BigInteger id_question;
	private String option_texte;
	private String option_valeur;
	

}
