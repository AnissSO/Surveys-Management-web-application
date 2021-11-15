package com.stage.WebApp21.model;


import java.math.BigInteger;
import java.sql.Blob;

import lombok.Data;

@Data
public class PageQuestionnaire {

	private BigInteger id_questionnaire_definition_page;
	private BigInteger id_questionnaire_definition;
	private String titre;
	private short page_ordre;
	private String instructions;
	
}
