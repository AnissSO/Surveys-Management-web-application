package com.stage.WebApp21.model;

import java.math.BigInteger;
import java.sql.Blob;
import java.time.LocalDateTime;


import lombok.Data;

@Data
public class Questionnaire {
	
	private BigInteger id;
	
	private BigInteger id_utilisateur;
	private LocalDateTime date_creation;
	private int id_categorie;
	private int autoriser_plusieurs_soumissions;
	private String status;
	
	private String nom;
	
	private String description;
	private int est_public;
	
}
