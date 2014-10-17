package projet.facture.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


import ch.qos.logback.core.net.server.Client;
import projet.employe.model.Employe;
import projet.produit.model.Produit;


@Entity
public class FactureModel {
	
	@Id
	@GeneratedValue
	private long id = -1;
	
	
	// ientitifiant de l'employé qui a encaissé 
//	@OneToOne
//	private Employe employee;
//	
	// identifiant du client de la facture
//	@OneToOne
//	private Client client;
	
	// list des produits encaisse
//	@ManyToMany
//	private Collection<Produit> produits ;
	
    
	// nombre de produits commande
	private long nombre ;
	
	// montant de la cature
	private long montant;
	
	//moyen de paiement
	private String moyen;

	



}
