package projet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import projet.produit.model.Produit;
import projet.produit.repository.produitRepository;

@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        produitRepository prepository = context.getBean(produitRepository.class);
        
        prepository.save(new Produit("Orange", "Nourriture", 10.0 , "orange" , 10));
        prepository.save(new Produit("Orange", "Nourriture", 10.0 , "orange" , 10));
        prepository.save(new Produit("Orange", "Nourriture", 10.0 , "orange" , 10)); 
        prepository.save(new Produit("Orange", "Nourriture", 10.0 , "orange" , 10));
        prepository.save(new Produit("Orange", "Nourriture", 10.0 , "orange" , 10));
        prepository.save(new Produit("Orange", "Nourriture", 10.0 , "orange" , 10));
        prepository.save(new Produit("Orange", "Nourriture", 10.0 , "orange" , 10));  
        prepository.save(new Produit("Orange", "Nourriture", 10.0 , "orange" , 10));
        prepository.save(new Produit("Orange", "Nourriture", 10.0 , "orange" , 10));
        prepository.save(new Produit("Orange", "Nourriture", 10.0 , "orange" , 10));
        prepository.save(new Produit("Orange", "Nourriture", 10.0 , "orange" , 10));
        prepository.save(new Produit("Banane", "Nourriture", 95.0 , "banane" , 75));
        prepository.save(new Produit("Orange", "Nourriture", 10.0 , "orange" , 10));
        prepository.save(new Produit("Orange", "Nourriture", 10.0 , "orange" , 10));
        prepository.save(new Produit("Orange", "Nourriture", 10.0 , "orange" , 10));
       

    }
}
