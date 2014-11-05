package projet.facture.model;


import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class FactureModel {
	
	@Id
	@GeneratedValue
	private long id = -1;
	
	private String panier;
	
	// montant de la caisse
	private double montant;
	
	//moyen de paiement
	private String moyen;

	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	

	public double getMontant() {
		return montant;
	}

	public void setMontant(double d) {
		this.montant = d;
	}

	public String getMoyen() {
		return moyen;
	}

	public void setMoyen(String moyen) {
		this.moyen = moyen;
	}

	public String getPanier() {
		return panier;
	}

	public void setPanier(String panier) {
		this.panier = panier;
	}




}
