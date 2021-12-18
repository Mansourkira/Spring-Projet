package tn.esprit.spring.entity;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Entity
@Getter
@Setter
@Table(name="DetailProduit")
@FieldDefaults(level = AccessLevel.PRIVATE)

public class DetailProduit implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
     Long idDetailProduit;
	
	@Temporal(value=TemporalType.DATE)	
	 Date  dateCreation;
	
	 String  dateDerniereModification;
	
	@Enumerated(EnumType.STRING)
	 CategorieProduit categorieProduit;

	 
	 @OneToOne(mappedBy = "detailproduit")
	 @JsonIgnore
	 private Produit produit;

	 
	 public DetailProduit()
	 {
		 
	 }
	public DetailProduit(Date dateCreation, String dateDerniereModification, CategorieProduit categorieProduit,
			Produit produit) {
		super();
		this.dateCreation = dateCreation;
		this.dateDerniereModification = dateDerniereModification;
		this.categorieProduit = categorieProduit;
		this.produit = produit;
	}
	 
	public DetailProduit(Date dateCreation, String dateDerniereModification, CategorieProduit categorieProduit) {
		super();
		this.dateCreation = dateCreation;
		this.dateDerniereModification = dateDerniereModification;
		this.categorieProduit = categorieProduit;
	}
	 

}
