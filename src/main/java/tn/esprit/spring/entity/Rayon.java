package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "rayon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Rayon implements Serializable {
   
	

	 static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	 Long idRayon;
	 String Code;
	 String libelle;
	 @JsonIgnore
	@OneToMany(mappedBy = "rayon")
	private  List <Produit> Produits;

	
	public Rayon( String code, String libelle) {
		Code = code;
		this.libelle = libelle;
	}
	
	
	
	
	
	
}
