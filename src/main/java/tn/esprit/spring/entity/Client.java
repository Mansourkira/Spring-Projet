package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Client implements Serializable {
	

	 static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	 Long idClient;
	 String nom;
	 String  prenom;
	@Temporal (TemporalType.DATE)
	 Date dateNaissance;
	 String  email;
	 String password;
	@Enumerated(EnumType.STRING)
	   CategorieClient categorie;
	@Enumerated(EnumType.STRING)
	private profession  profession;
	
	 @JsonIgnore
	@OneToMany(mappedBy="client", cascade = CascadeType.DETACH,fetch=FetchType.LAZY)
	private List<Facture> facture;
	
	public Client()
	{
		
	}
	
	public Client(String nom, String prenom, Date dateNaissance, String email, String password,
			CategorieClient categorie, tn.esprit.spring.entity.profession profession,
			List<Facture> facture) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.dateNaissance = dateNaissance;
		this.email = email;
		this.password = password;
		this.categorie = categorie;
		this.profession = profession;
		this.facture = facture;
	}



	@Override
	public int hashCode() {
		return Objects.hash(categorie, dateNaissance, email, facture, idClient, nom, password, prenom, profession);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return categorie == other.categorie && Objects.equals(dateNaissance, other.dateNaissance)
				&& Objects.equals(email, other.email) && Objects.equals(facture, other.facture)
				&& Objects.equals(idClient, other.idClient) && Objects.equals(nom, other.nom)
				&& Objects.equals(password, other.password) && Objects.equals(prenom, other.prenom)
				&& profession == other.profession;
	}



	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance="
				+ dateNaissance + ", email=" + email + ", password=" + password + ", categorie=" + categorie
				+ ", profession=" + profession + ", facture=" + facture + "]";
	}



	public Long getIdClient() {
		return idClient;
	}



	public void setIdClient(Long idClient) {
		this.idClient = idClient;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public Date getDateNaissance() {
		return dateNaissance;
	}



	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public CategorieClient getCategorie() {
		return categorie;
	}



	public void setCategorie(CategorieClient categorie) {
		this.categorie = categorie;
	}



	public profession getProfession() {
		return profession;
	}



	public void setProfession(profession profession) {
		this.profession = profession;
	}



	public List<Facture> getFacture() {
		return facture;
	}



	public void setFacture(List<Facture> facture) {
		this.facture = facture;
	}




}
