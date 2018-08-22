
package ch.buhls.billmanager.gui;

import ch.buhls.billmanager.gui.data.GUIPersonBaseData;

/**
 *
 * @author simon
 */
public class GUIStringCollection
{
    public static String DB_ID = "DB_ID";
    public static String DB_VERSION = "DB_Version";
    
    public static String TRACKED_ENRITY_VERSION_NR = "Versionsnummer";
    public static String TRACKED_ENRITY_CHANGE_TXT = "Änderungstext";
    public static String TRACKED_ENRITY_DATE = "Erstelldatum";
    
    public static String PERSON_BASE_DATA_DB_ID = "Daten DB_ID";
    public static String PERSON_BASE_DATA_DB_VERSION = "Daten DB_Version";
    public static String PERSONAL_ID = "Mitgliedernummer";
    public static String NAME = "Namen";
    public static String PRENAME = "Vornamen";
    public static String STREET = "Strasse";
    public static String POSTAL_CODE = "PLZ";
    public static String CITY = "Ort";
    public static String PHONE_P = "Tel. P";
    public static String PHONE_M = "Tel. M";
    public static String E_MAIL = "E-Mail";
    public static String IBAN = "IBAN";
    public static String TEAM = "Team";
    public static String SALUTATION = "Begrüssung";
    public static String TITLE = "Titel";
    
    public static String PERSON_NR_OF_ART = "Artikel in Warenkorb";
    public static String PERSON_NR_OF_ROLES = "Anzahl Rollen";
    public static String PERSON_NR_OF_BILLS = "Anzahl Rechnungen";
    
    
    public static String PERSON_NEW_BILL = "Rechnung eröffnen";
    public static String PERSON_SHOW_BILL = "Rechnung anzeigen";
    public static String PERSON_ADD_ART_TO_BILL = "Markierter Artikel hinzufügen";
    public static String PERSON_ADD_ROLE = "Markierte Rolle hinzufügen";
    public static String PERSON_REMOVE_ROLE = "Rolle entfernen";
    public static String PERSON_ADD_1 = "1 hinzufügen";
    public static String PERSON_ADD_2 = "2 hinzufügen";
    public static String PERSON_ADD_3 = "3 hinzufügen";
    public static String PERSON_ADD_4 = "4 hinzufügen";
    public static String PERSON_ADD_5 = "5 hinzufügen";
    public static String PERSON_SHOW_BUSKET = "Warenkorb anzeigen";
    public static String PERSON_SHOW_ROLES = "Rollen anzeigen";
    
    public static String POSITION_AMOUNT = "Menge";
    public static String POSITION_POS = "Position";
    public static String POSITION_REMOVE_POSITION = "Pos. entfernen";
    public static String POSITION_EDIT_POSITION = "Pos. bearbeiten";
    public static String POSITION_SAVE_POSITION = "Pos. speichern";
    public static String POSITION_ADD_MARKED_POSITION = "Markierte Pos. hinzufügen";
    
    public static String BILL_STATUS = "Status";
    public static String BILL_STATUS_IN_PROGRESS = "In Arbeit";
    public static String BILL_STATUS_SENDET = "Gesendet";
    public static String BILL_STATUS_PAID = "Bezahlt";
    public static String BILL_STATUS_STORNO = "Storniert";
    public static String BILL_STATUS_SUCCESSOR = "Nachfolgerechnung erstellen";
    public static String BILL_LOCATION = "Ort";
    
    public static String ART_TITLE = "Titel";
    public static String ART_DESCRIPTION = "Beschrieb";
    public static String ART_CATEGORIE = "Kategorie";
    public static String ART_PRICE = "Preis [Rp]";
    
    public static String ROLE_NAME = "Bezeichnung";
    
    public static String TEMPLATE_NAME = "Bezeichnung";
    public static String TEMPLATE_MAX_POS = "Max. Anzahl Positionen";
    
    public static String DATE = "Datum";
    public static String TEMPLATE = "Vorlage";
    
    public static String BIRTHDAY = "Geburtstag";
    public static String BIRTHMONTH = "Geburtsmonat";
    public static String BIRTHYEAR = "Jahrgang";
    
    public static String EDIT = "bearbeiten";
    public static String SAVE = "speichern";
    public static String ABORT = "abbrechen";
    public static String NEW = "neu";
    public static String SHOW = "anzeigen";
    public static String SHOW_VERSIONS = "Versionsverlauf anzeigen";
    public static String OPEN_FILE = "Öffne Datei";
    public static String CONFIRM = "bestätigen";
    public static String DELETE = "löschen";
    public static String MARK = "markieren";
    public static String IS_MARKED = "markiert";
    
    public static String CHANGE_STATE = "Status ändern";
    
    public static String SHOW_PDF = "PDF anzeigen";
    public static String GENERATE_PDF = "PDF generieren";
    
    public static String DIFFERENT_DATA = "<diff>";
    
    public static String getTitleForCreatePerson(){
        return "Mitglied erfassen";
    }
    public static String getTitleForEditPerson(GUIPersonBaseData person){
        return String.format("Bearbeite Mitglied %d", person.getPersonalId().get());
    }
    public static String getTitleForShowPerson(GUIPersonBaseData person){
        return String.format("Anzeigen Mitglied %d V%d", person.getPersonalId().get(), person.getVersionNr().get());
    }
    public static String getTitleForListPersonVersions(GUIPersonBaseData person){
        return String.format("Versionen Mitglied %d", person.getPersonalId().get());
    }
    public static String getTitleForManageRoles(GUIPersonBaseData person){
        return String.format("Rollen Mitglied %d", person.getPersonalId().get());
    }
    
    
}
