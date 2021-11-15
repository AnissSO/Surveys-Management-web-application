package com.stage.WebApp21.model;

import java.math.BigInteger;
import java.time.LocalDateTime;

import lombok.Data;
@Data
public class Utilisateur {

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
