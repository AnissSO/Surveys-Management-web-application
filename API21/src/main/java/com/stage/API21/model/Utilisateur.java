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
@Table(name = "utilisateur")
public class Utilisateur {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id_utilisateur;
	
	private LocalDateTime date_creation;
	private LocalDateTime date_naissance;
	private String email;
	private String prenom;
	private String nom;
	private String login;	
	private String password;
	private int Role_id;
}
