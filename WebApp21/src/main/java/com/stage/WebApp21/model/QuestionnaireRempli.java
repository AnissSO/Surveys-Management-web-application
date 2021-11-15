package com.stage.WebApp21.model;

import java.math.BigInteger;
import java.sql.Blob;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class QuestionnaireRempli {

	private BigInteger id_questionnaire;
	private BigInteger id_questionnaire_definition;
	private LocalDateTime date_creation;
	private String nom_questionnaire;
	private LocalDateTime date_soumission;
	
}
