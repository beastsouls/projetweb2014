package projet;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


import projet.client.model.Client;
import projet.client.repository.ClientRepository;
import projet.produit.model.Produit;
import projet.produit.repository.produitRepository;

@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        produitRepository prepository = context.getBean(produitRepository.class);
        ClientRepository crepository = context.getBean(ClientRepository.class);
        
        prepository.save(new Produit("Orange", "Nourriture", 10.0 , "orange" , 10));
        prepository.save(new Produit("Pomme", "Nourriture", 10.0 , "orange" , 10));
        prepository.save(new Produit("Poire", "Nourriture", 10.0 , "orange" , 10)); 
        prepository.save(new Produit("Avocat", "Nourriture", 10.0 , "orange" , 10));
        prepository.save(new Produit("Prune", "Nourriture", 10.0 , "orange" , 10));
        prepository.save(new Produit("Fraise", "Nourriture", 10.0 , "orange" , 10));
        prepository.save(new Produit("Figue", "Nourriture", 10.0 , "orange" , 10));  
        prepository.save(new Produit("Citron", "Nourriture", 10.0 , "orange" , 10));
        prepository.save(new Produit("Raisin", "Nourriture", 10.0 , "orange" , 10));
        prepository.save(new Produit("Framboise", "Nourriture", 10.0 , "orange" , 10));
        prepository.save(new Produit("kiwi", "Nourriture", 10.0 , "orange" , 10));
        prepository.save(new Produit("Banane", "Nourriture", 95.0 , "banane" , 75));
        prepository.save(new Produit("abricot", "Nourriture", 10.0 , "orange" , 10));
        prepository.save(new Produit("peche", "Nourriture", 10.0 , "orange" , 10));
        prepository.save(new Produit("pamplemousse", "Nourriture", 10.0 , "orange" , 10));
       

        crepository.save(new Client("jean","pdg", "test" , "n@gmail.com", "test"));
        crepository.save(new Client("pierre","pdg", "test" , "n@gmail.com", "test"));
        crepository.save(new Client("sophie","pdg", "test" , "n@gmail.com", "test"));
        crepository.save(new Client("lucie","pdg", "test" , "n@gmail.com", "test"));
        crepository.save(new Client("boris","pdg", "test" , "n@gmail.com", "test"));
        crepository.save(new Client("lol","pdg", "test" , "n@gmail.com", "test"));
        crepository.save(new Client("test","pdg", "test" , "n@gmail.com", "test"));
        
        
    }
}
