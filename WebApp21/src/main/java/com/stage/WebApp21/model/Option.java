package com.stage.WebApp21.model;

import java.math.BigInteger;

import lombok.Data;

@Data
public class Option {
	//il ne s'agit pas d'une table de BD mais simplement une classe qu'on a créé pour obtenir les options d'une question
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private String option5;
	private String option6;
	private BigInteger idQuest;
	
}
