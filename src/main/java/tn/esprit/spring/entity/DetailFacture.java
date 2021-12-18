package tn.esprit.spring.entity;


import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name ="detailfacture")
public class DetailFacture {
	
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long idDetailFacture;
	private int qte;
	private Float prixTotal;
	private Float pourcentageRemise;
	private Float montanRemise;
	

	@ManyToOne
	private Produit produits;
	
	
	
	 @ManyToOne
	 @JsonIgnore
	  private Facture factures;

	 public DetailFacture()
	 {
		 
	 }

	public DetailFacture(Long idDetailFacture, int qte, Float prixTotal, Float pourcentageRemise, Float montanRemise,
			Produit produits, Facture factures) {
		super();
		this.idDetailFacture = idDetailFacture;
		this.qte = qte;
		this.prixTotal = prixTotal;
		this.pourcentageRemise = pourcentageRemise;
		this.montanRemise = montanRemise;
		this.produits = produits;
		this.factures = factures;
	}

	@Override
	public int hashCode() {
		return Objects.hash(factures, idDetailFacture, montanRemise, pourcentageRemise, prixTotal, produits, qte);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DetailFacture other = (DetailFacture) obj;
		return Objects.equals(factures, other.factures) && Objects.equals(idDetailFacture, other.idDetailFacture)
				&& Objects.equals(montanRemise, other.montanRemise) && pourcentageRemise == other.pourcentageRemise
				&& Objects.equals(prixTotal, other.prixTotal) && Objects.equals(produits, other.produits)
				&& qte == other.qte;
	}

	public Long getIdDetailFacture() {
		return idDetailFacture;
	}

	public void setIdDetailFacture(Long idDetailFacture) {
		this.idDetailFacture = idDetailFacture;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public Float getPrixTotal() {
		return prixTotal;
	}

	public void setPrixTotal(Float prixTotal) {
		this.prixTotal = prixTotal;
	}

	public Float getPourcentageRemise() {
		return pourcentageRemise;
	}

	public void setPourcentageRemise(Float pourcentageRemise) {
		this.pourcentageRemise = pourcentageRemise;
	}

	public Float getMontanRemise() {
		return montanRemise;
	}

	public void setMontanRemise(Float montanRemise) {
		this.montanRemise = montanRemise;
	}

	public Produit getProduits() {
		return produits;
	}

	public void setProduits(Produit produits) {
		this.produits = produits;
	}

	public Facture getFactures() {
		return factures;
	}

	public void setFactures(Facture factures) {
		this.factures = factures;
	}
	 
	
	 
	
	
	
	

}
