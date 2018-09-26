package ch.buhls.billmanager.gui.framework;

import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.data.GUIPerson;
import ch.buhls.billmanager.gui.data.GUIRole;

/**
 *
 * @author simon
 */
public class DEPersonStringCollection extends DEDefaultStringCollection<GUIPerson>
{

    // ===================================== Tabs ========================================================
    @Override
    public String getTabTitle_Create() {
        return "Mitglied erfassen";
    }

    @Override
    public String getTabTitle_Edit(GUIPerson person) {
        return String.format("Bearbeite %s %s", person.getBaseData().getName().get(), person.getBaseData().getPrename().get());
    }

    @Override
    public String getTabTitle_Show(GUIPerson person) {
        return String.format("Anzeigen %s %s", person.getBaseData().getName().get(), person.getBaseData().getPrename().get());
    }

    public String getTabTitle_ListPersons() {
        return "Personen";
    }

    public String getTabTitle_ListVersions(GUIPerson person){
        return String.format("Versionen von %s %s", person.getBaseData().getName().get(), person.getBaseData().getPrename().get());
    }
    
    public String getTabTitle_ListRoles(GUIPerson person){
        return String.format("Rollen von %s %s", person.getBaseData().getName().get(), person.getBaseData().getPrename().get());
    }
    
    // ===================================== Hints ======================================================
    @Override
    public String getHintTxt_Created(GUIPerson person) {
        return String.format("%s %s erfolgreich erfasst", person.getBaseData().getName().get(), person.getBaseData().getPrename().get());
    }

    @Override
    public String getHintTxt_Edited(GUIPerson person) {
        return String.format("%s %s erfolgreich bearbeited", person.getBaseData().getName().get(), person.getBaseData().getPrename().get());
    }

    public String getHintTxt_roleAdded(GUIRole role, int nrAdded) {
        return String.format("Rolle \"%s\" zu %d Personen hinzugefügt", role.getName().get(), nrAdded);
    }

    public String getHintTxt_artAdded(GUIArticle art, int nrPers, int nrArt) {
        return String.format("Artikel \"%s; %s\" zu %d Personen jeweils %d mal hinzugefügt", art.getTitle().get(), art.getDescription().get(), nrPers, nrArt);
    }
    
    public String getHintTxt_imported(int nrPers) {
        return String.format("%d Personen importiert",nrPers);
    }
    
    // ===================================== Confirms ===================================================
    @Override
    public String getConfirmTxt_Save(GUIPerson person) {
        return String.format("%s %s", person.getBaseData().getName().get(), person.getBaseData().getPrename().get());
    }

    public DialoquesStringsTO getConfirmTxt_AddRole(GUIRole role, int nrPersons) {
        return new DialoquesStringsTO("Bestätigung erforderlich", String.format("Soll die Rolle \"%s\" zu %d Personen hinzugefügt werden?", role.getName().get(), nrPersons));
    }

    public DialoquesStringsTO getConfirmTxt_RemoveRole(GUIRole role) {
        return new DialoquesStringsTO("Bestätigung erforderlich", String.format("Soll die Rolle \"%s\" entfernt werden?", role.getName().get()));
    }
    
    public DialoquesStringsTO getConfirmTxt_AddArticle(GUIArticle art, int nrPers, int nrArt) {
        return new DialoquesStringsTO(
                "Bestätigung erforderlich", 
                String.format("Soll der Artikel \"%s; %s\" zu %d Personen jeweils %d mal hinzugefügt werden?", art.getTitle().get(), art.getDescription().get(), nrPers, nrArt));
    }

    public DialoquesStringsTO getConfirmTxt_ImportMembers(int nrPers) {
        return new DialoquesStringsTO(
                "Bestätigung erforderlich", 
                String.format("Sollen %d Personen importiert werden?", nrPers));
    }
    
    // ===================================== Txt inputs ==================================================
    public DialoquesStringsTO getDialoqueTxt_NrArticlesToAdd(GUIArticle art) {
        return new DialoquesStringsTO(
                String.format("Wie oft soll der Artikel \"%s; %s\" hinzugefügt werden?", art.getTitle().get(), art.getDescription().get()), 
                "Anzahl");
    }
    
    public DialoquesStringsTO getDialoqueTxt_AgeFilter() {
        return new DialoquesStringsTO(
                "Bitte spezifizieren Sie das Alter für den Filter.", "Alter");
    }
   
}
