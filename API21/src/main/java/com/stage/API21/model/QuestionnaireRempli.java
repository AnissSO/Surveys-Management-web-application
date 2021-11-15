package com.stage.API21.model;

import java.math.BigInteger;
import java.sql.Blob;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "questionnaire")
public class QuestionnaireRempli {

	@Id
	private BigInteger id_questionnaire;
	
	private BigInteger id_questionnaire_definition;
	private LocalDateTime date_creation;
	private String nom_questionnaire;
	private LocalDateTime date_soumission;
	
}
