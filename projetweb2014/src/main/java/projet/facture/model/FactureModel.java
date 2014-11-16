package projet.facture.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import antlr.collections.List;

@Entity
public class FactureModel {

	@Id
	@GeneratedValue
	private long id = -1;

//	private String panier;

	// montant de la caisse
	private double montant;
	private  String Nomclient;
	private Date datecommande;
	
	// moyen de paiement
		private String moyen;
		private ArrayList<String> mesproduits = new ArrayList<String>();
		
		private ArrayList<Integer> mesqtt = new ArrayList<Integer>();

	public Date getDatecommande() {
		return datecommande;
	}

	public void setDatecommande(Date datecommande) {
		this.datecommande = datecommande;
	}

	public String getNomclient() {
		return Nomclient;
	}

	public void setNomclient(String nomclient) {
		Nomclient = nomclient;
	}

	

	public ArrayList<Integer> getMesqtt() {
		return mesqtt;
	}

	public void setMesqtt(ArrayList<Integer> mesqtt) {
		this.mesqtt = mesqtt;
	}

	public ArrayList<String> getMesproduits() {
		return mesproduits;
	}

	public void setMesproduits(ArrayList<String> mesproduits) {
		this.mesproduits = mesproduits;
	}

	public void maliste(String prod) {

		ArrayList<String> mesprod = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(prod, "|");
		String str = st.nextToken();
		mesprod.add(str);

	}

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

//	public String getPanier() {
//		return panier;
//	}
//
//	public void setPanier(String panier) {
//		this.panier = panier;
//	}

}
