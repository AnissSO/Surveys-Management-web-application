package com.stage.API21.model;

import java.math.BigInteger;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "questionnaire_definition_page")
public class PageQuestionnaire {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id_questionnaire_definition_page;
	
	private BigInteger id_questionnaire_definition;
	private String titre;
	private short page_ordre;
	private String instructions;
	
}