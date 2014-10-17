package projet.facture.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import ch.qos.logback.core.net.server.Client;
import projet.employe.model.Employe;
import projet.produit.model.Produit;


@Entity
public class FactureModel {
	
	@Id
	@GeneratedValue
	private long id = -1;
	
	
	// ientitifiant de l'employé qui a encaissé 
	@OneToOne
	private Employe employee;
	
	// identifiant du client de la facture
	@OneToOne
	private Client client;
	
	// list des produits encaisse
	@ManyToMany
	private Collection<Produit> produits ;
	
    
	// nombre de produits commande
	private long nombre ;
	
	// montant de la cature
	private long montant;
	
	//moyen de paiement
	private String moyen;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Employe getEmployee() {
		return employee;
	}

	public void setEmployee(Employe employee) {
		this.employee = employee;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Collection<Produit> getProduits() {
		return produits;
	}

	public void setProduits(Collection<Produit> produits) {
		this.produits = produits;
	}

	public long getNombre() {
		return nombre;
	}

	public void setNombre(long nombre) {
		this.nombre = nombre;
	}

	public long getMontant() {
		return montant;
	}

	public void setMontant(long montant) {
		this.montant = montant;
	}

	public String getMoyen() {
		return moyen;
	}

	public void setMoyen(String moyen) {
		this.moyen = moyen;
	}



}
