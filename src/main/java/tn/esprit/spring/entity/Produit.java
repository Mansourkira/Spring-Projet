package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType; 
import javax.persistence.CascadeType;

import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.JoinColumn;

@Entity
@Table(name="produit")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Produit implements Serializable {
	
	 static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "idProduit")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
     Long idProduit;
	
	//int idProduit;
	@Column(name = "code")

	 String  code;
	@Column(name = "libelle")
	 String  libelle;
	@Column(name = "prixunitaire")

	 float prixUnitaire;
	
	 @ManyToMany
	 @JoinTable(name = "produit_fournissuer",
     joinColumns = @JoinColumn(name = "idProduit"),
     inverseJoinColumns = @JoinColumn(name = "idFournisseur"))
	 Set<Fournisseur> fournisseur;

	 @Enumerated(EnumType.STRING)
	   CategorieProduit categorie;
	
	  @ManyToOne
		 @JsonIgnore
	  private Rayon rayon;
	

	  @OneToOne(cascade = CascadeType.ALL)
		 @JsonIgnore
	  private DetailProduit detailproduit;
	  
	  
	  @ManyToOne
		 @JsonIgnore

	  private Stock stock;
	  
	  
	
	//constructeur
	public Produit(String code , String libelle , float PrixUnitaire )
	{
		this.code=code;
		this.libelle=libelle;
		this.prixUnitaire=PrixUnitaire;
	}



	public Produit(String code, String libelle, float prixUnitaire, List<Fournisseur> fournisseur, Rayon rayon,
			DetailProduit detailproduit, Stock stock) {
		super();
		this.code = code;
		this.libelle = libelle;
		this.prixUnitaire = prixUnitaire;
		this.fournisseur = (Set<Fournisseur>) fournisseur;
		this.rayon = rayon;
		this.detailproduit = detailproduit;
		this.stock = stock;
	}



	public Long getIdProduit() {
		return idProduit;
	}



	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getLibelle() {
		return libelle;
	}



	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}



	public float getPrixUnitaire() {
		return prixUnitaire;
	}



	public void setPrixUnitaire(float prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}



	public Set<Fournisseur> getFournisseur() {
		return fournisseur;
	}



	public void setFournisseur(Set<Fournisseur> hset) {
		this.fournisseur = hset;
	}



	public Rayon getRayon() {
		return rayon;
	}



	public void setRayon(Rayon rayon) {
		this.rayon = rayon;
	}



	public DetailProduit getDetailproduit() {
		return detailproduit;
	}



	public void setDetailproduit(DetailProduit detailproduit) {
		this.detailproduit = detailproduit;
	}



	public Stock getStock() {
		return stock;
	}



	public void setStock(Stock stock) {
		this.stock = stock;
	}



	public CategorieProduit getCategorie() {
		return categorie;
	}



	public void setCategorie(CategorieProduit categorie) {
		this.categorie = categorie;
	}



	
	
	  
	  
	  

	
	

}
