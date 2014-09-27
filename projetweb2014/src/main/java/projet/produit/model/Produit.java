package projet.produit.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Produit {
	
	@Id
	@GeneratedValue
	private long id = -1;
	private String name;
	private String Typeproduit;
	private String Prix;
	private String description;
	private int stock;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTypeproduit() {
		return Typeproduit;
	}
	public void setTypeproduit(String typeproduit) {
		Typeproduit = typeproduit;
	}
	public String getPrix() {
		return Prix;
	}
	public void setPrix(String prix) {
		Prix = prix;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
}
