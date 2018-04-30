package ch.buhls.billmanager;

import ch.buhls.billmanager.persistance.PersistanceFascade;
import ch.buhls.billmanager.persistance.PersistanceException;
import ch.buhls.billmanager.persistance.database.entities.Article;
import ch.buhls.billmanager.persistance.database.entities.Person;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FXMLController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
        
        PersistanceFascade modell = new PersistanceFascade(new File("temp.db"));
        
        /*Article art = modell.createArticle();
        art.setTitle("Titel");
        art.setDescription("Beschrieb");
        art.setChangeTxt("neu erstellt");
        art.setCosts(12000);
        art.setInternalCategorie("interen Kategorie");*/
        
        Article art = modell.getAllArticles().get(0);
        art = modell.editArticle(art);
        
        art.setTitle("Neuer Titel");
        
        try {
            modell.storeArticle(art);
        }
        catch (PersistanceException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Person person = modell.createPerson();
        person.getPersonBaseData().setName("Fischer");
        person.getPersonBaseData().setPrename("Debbie");
        
        try {
            modell.storePersonBaseDataAndPerson(person);
            modell.storePerson(person);
        }
        catch (PersistanceException ex) {
            Logger.getLogger(FXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        for(Person pers : modell.getAllPersons()){
            System.out.println(pers.getPersonBaseData().getName());
        }
        
        System.out.println("Finito!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
