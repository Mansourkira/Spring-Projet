package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Entity
@Getter
@Setter
@Table (name = "fournisseur")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Fournisseur implements Serializable  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	 Long idFournisseur;
	 String code;
	 String libelle;
	public Fournisseur(String code, String libelle) {
		super();
		this.code = code;
		this.libelle = libelle;
	}
	public Fournisseur()
	{
		
	}
	

	
	
}
