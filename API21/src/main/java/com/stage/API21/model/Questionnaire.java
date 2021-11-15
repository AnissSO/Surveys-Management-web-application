package com.stage.API21.model;

import java.math.BigInteger;
import java.sql.Blob;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "questionnaire_definition")
public class Questionnaire {
	
	@Id
	@Column(name="id_questionnaire_definition")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;
	
	//@Column(columnDefinition = "BigInteger default 1")
	private BigInteger id_utilisateur;
	
	private LocalDateTime date_creation;
	private int id_categorie;
	private int autoriser_plusieurs_soumissions;
	private String status;
	
	private String nom;
	
	private String description;
	private int est_public;
}
