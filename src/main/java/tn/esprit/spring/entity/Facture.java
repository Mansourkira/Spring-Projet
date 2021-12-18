package tn.esprit.spring.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "facture")
public class Facture implements Serializable {
	

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)

	 Long idFActure;
	 float montantRemise ;
	public float montantFacture;
	@Temporal (TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-MM")

	 Date dateFacture;
	 Boolean active;
	 
	 @ManyToOne()
	  public Client client;
	 
	 
	@OneToMany(mappedBy="factures", cascade = CascadeType.REMOVE)
	  private List<DetailFacture> detailsFacture;
	 
	 
	@Override
	public int hashCode() {
		return Objects.hash(active, client, dateFacture, idFActure, montantFacture, montantRemise);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Facture other = (Facture) obj;
		return Objects.equals(active, other.active) && Objects.equals(client, other.client)
				&& Objects.equals(dateFacture, other.dateFacture) && Objects.equals(idFActure, other.idFActure)
				&& Float.floatToIntBits(montantFacture) == Float.floatToIntBits(other.montantFacture)
				&& Float.floatToIntBits(montantRemise) == Float.floatToIntBits(other.montantRemise);
	}
	public Facture(Long idFActure, float montantRemise, float montantFacture, Date dateFacture, Boolean active,
			Client client) {
		super();
		this.idFActure = idFActure;
		this.montantRemise = montantRemise;
		this.montantFacture = montantFacture;
		this.dateFacture = dateFacture;
		this.active = active;
		this.client = client;
	}
	public Long getIdFActure() {
		return idFActure;
	}
	public void setIdFActure(Long idFActure) {
		this.idFActure = idFActure;
	}
	public float getMontantRemise() {
		return montantRemise;
	}
	public void setMontantRemise(float montantRemise) {
		this.montantRemise = montantRemise;
	}
	public float getMontantFacture() {
		return montantFacture;
	}
	public void setMontantFacture(float montantFacture) {
		this.montantFacture = montantFacture;
	}
	public Date getDateFacture() {
		return dateFacture;
	}
	public void setDateFacture(Date dateFacture) {
		this.dateFacture = dateFacture;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	
	
	public List<DetailFacture> getDetailsFacture() {
		return detailsFacture;
	}
	public void setDetailsFacture(List<DetailFacture> detailsFacture) {
		this.detailsFacture = detailsFacture;
	}
	public Facture()
	{
		
	}
	
	

}
