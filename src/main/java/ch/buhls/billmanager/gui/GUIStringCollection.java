
package ch.buhls.billmanager.gui;

import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.data.GUIFinancialYear;
import ch.buhls.billmanager.gui.data.GUIPersonBaseData;
import ch.buhls.billmanager.gui.data.GUIRole;

/**
 *
 * @author simon
 */
public class GUIStringCollection
{
    public static final String DB_ID = "DB_ID";
    public static final String DB_VERSION = "DB_Version";
    
    public static final String TRACKED_ENRITY_VERSION_NR = "Versionsnummer";
    public static final String TRACKED_ENRITY_CHANGE_TXT = "Änderungstext";
    public static final String TRACKED_ENRITY_DATE = "Erstelldatum";
    
    public static final String PERSON_BASE_DATA_DB_ID = "Daten DB_ID";
    public static final String PERSON_BASE_DATA_DB_VERSION = "Daten DB_Version";
    public static final String PERSONAL_ID = "Mitgliedernummer";
    public static final String NAME = "Namen";
    public static final String PRENAME = "Vornamen";
    public static final String STREET = "Strasse";
    public static final String POSTAL_CODE = "PLZ";
    public static final String CITY = "Ort";
    public static final String PHONE_P = "Tel. P";
    public static final String PHONE_M = "Tel. M";
    public static final String E_MAIL = "E-Mail";
    public static final String IBAN = "IBAN";
    public static final String TEAM = "Team";
    public static final String SALUTATION = "Begrüssung";
    public static final String TITLE = "Titel";
    
    public static final String PERSON_NR_OF_ART = "Artikel in Warenkorb";
    public static final String PERSON_NR_OF_ROLES = "Anzahl Rollen";
    public static final String PERSON_NR_OF_BILLS = "Anzahl Rechnungen";
    
    
    public static final String PERSON_NEW_BILL = "Rechnung eröffnen";
    public static final String PERSON_SHOW_BILL = "Rechnung anzeigen";
    public static final String PERSON_ADD_ART_TO_BILL = "Markierter Artikel hinzufügen";
    public static final String PERSON_ADD_ROLE = "Markierte Rolle hinzufügen";
    public static final String PERSON_REMOVE_ROLE = "Rolle entfernen";
    public static final String PERSON_ADD_1 = "1 hinzufügen";
    public static final String PERSON_ADD_2 = "2 hinzufügen";
    public static final String PERSON_ADD_3 = "3 hinzufügen";
    public static final String PERSON_ADD_4 = "4 hinzufügen";
    public static final String PERSON_ADD_5 = "5 hinzufügen";
    public static final String PERSON_SHOW_BUSKET = "Warenkorb anzeigen";
    public static final String PERSON_SHOW_ROLES = "Rollen anzeigen";
    public static final String PERSON_FILTER = "Filtern";
    public static final String PERSON_ADD_FILTER_SHOW_ROLE_MEMBER = "Rolleninhaber anzeigen";
    public static final String PERSON_ADD_FILTER_HIDE_ROLE_MEMBER = "Rolleninhaber ausblenden";
    
    public static final String POSITION_AMOUNT = "Menge";
    public static final String POSITION_POS = "Position";
    public static final String POSITION_REMOVE_POSITION = "Pos. entfernen";
    public static final String POSITION_EDIT_POSITION = "Pos. bearbeiten";
    public static final String POSITION_SAVE_POSITION = "Pos. speichern";
    public static final String POSITION_ADD_MARKED_POSITION = "Markierte Pos. hinzufügen";
    
    public static final String BILLS = "Rechnungen";
    public static final String BILL_STATUS = "Status";
    public static final String BILL_STATUS_IN_PROGRESS = "In Arbeit";
    public static final String BILL_STATUS_SENDET = "Gesendet";
    public static final String BILL_STATUS_PAID = "Bezahlt";
    public static final String BILL_STATUS_STORNO = "Storniert";
    public static final String BILL_STATUS_SUCCESSOR = "Nachfolgerechnung erstellen";
    public static final String BILL_LOCATION = "Rechnungsort";
    public static final String BILL_DATE = "Rechnungsdatum";
    public static final String BILL_PAYMENT_DEADLINE = "Zahlungsfrist (Tage)";
    public static final String BILL_TEMPLATE = "Rechnungsvorlage";
    public static final String BILL_FINANCIAL_YEAR = "Vereinsjahr";
    public static final String BILL_CREATE = "Erstellen";
    public static final String BILL_PERSONS = "Rechnungspersonen";
    public static final String BILL_REMOVE_PERSON = "Person entfernen";
    public static final String BILL_DATE_SENDET = "Datum Eröffnet";
    public static final String BILL_DATE_CLOSED = "Datum Geschlossen";
    public static final String BILL_SUM_RP= "Summe [Rp]";
    public static final String BILL_NR_POSITIONS= "Anzahl Positionen";
    public static final String BILL_COMMENT = "Kommentar";
    
    public static final String ART_TITLE = "Titel";
    public static final String ART_DESCRIPTION = "Beschrieb";
    public static final String ART_CATEGORIE = "Kategorie";
    public static final String ART_PRICE = "Preis [Rp]";
    
    public static final String ROLE_NAME = "Bezeichnung";
    
    public static final String TEMPLATE_NAME = "Bezeichnung";
    public static final String TEMPLATE_MAX_POS = "Max. Anzahl Positionen";
    
    public static final String YEAR_NAME = "Bezeichnung";
    public static final String YEAR_PREFIX = "Rechnungsnummer Präfix";
    public static final String YEAR_FIRST_DAY = "Erster Tag";
    public static final String YEAR_LAST_DAY = "Letzter Tag";
    
    
    public static final String DATE = "Datum";
    public static final String TEMPLATE = "Vorlage";
    
    public static final String BIRTHDAY = "Geburtstag";
    public static final String BIRTHMONTH = "Geburtsmonat";
    public static final String BIRTHYEAR = "Jahrgang";
    
    public static final String EDIT = "bearbeiten";
    public static final String SAVE = "speichern";
    public static final String ABORT = "abbrechen";
    public static final String NEW = "neu";
    public static final String SHOW = "anzeigen";
    public static final String SHOW_VERSIONS = "Versionsverlauf anzeigen";
    public static final String OPEN_FILE = "Öffne Datei";
    public static final String CONFIRM = "bestätigen";
    public static final String DELETE = "löschen";
    public static final String MARK = "markieren";
    public static final String IS_MARKED = "markiert";
    
    public static final String CHANGE_STATE = "Status ändern";
    
    public static final String SHOW_PDF = "PDF anzeigen";
    public static final String GENERATE_PDF = "PDF generieren";
    public static final String PRINT_PDF = "PDF drucken";
    
    public static final String DIFFERENT_DATA = "<diff>";
    
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
    public static String getTitleForCreateBill(){
        return "Rechnung erstellen";
    }
    
    public static String getTitleForListFinancialYears(){
        return "Vereinsjahre";
    }
    public static String getTitleForCreateFinancialYear(){
        return "neues Vereinsjahr efassen";
    }
    public static String getTitleForEditFinancialYear(GUIFinancialYear year){
        return String.format("Vereinsjahr \"%s\" bearbeiten", year.getName().get());
    }
    
    public static String getTitle_editBill(GUIBill bill){
        return String.format("Rechnung %s-%d bearbeiten", bill.getYear().getPrefix().get(), bill.getDb_id().get());
    }
    
    public static String getHintTxt_artMarked(GUIArticle art){
        return String.format("Artikel \"%s; %s\" markiert", art.getTitle().get(), art.getDescription().get());
    }
    public static String getHintTxt_roleMarked(GUIRole role){
        return String.format("Rolle \"%s\" markiert", role.getName().get());
    }
}
