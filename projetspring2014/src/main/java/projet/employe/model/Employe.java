package projet.employe.model;

import java.io.ObjectStreamField;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employe implements Serializable{
	
	@Id
	@GeneratedValue
	private long id = -1;
	private String name;
	private String prenom;
	private String login;
	private String password;
	private String email;
	private String adresse;
	
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
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	  
    private static final ObjectStreamField[] serialPersistentFields = {
        new ObjectStreamField("name", String.class),
        new ObjectStreamField("prenom", String.class),
        new ObjectStreamField("login", String.class),
        new ObjectStreamField("password", String.class),
        new ObjectStreamField("adresse", String.class),
        new ObjectStreamField("email", String.class),
    
        //new ObjectStreamField("age", int.class)
    };
	

	
}
