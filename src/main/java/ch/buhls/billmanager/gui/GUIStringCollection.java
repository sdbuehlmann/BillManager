
package ch.buhls.billmanager.gui;

import ch.buhls.billmanager.gui.data.GUIArticle;
import ch.buhls.billmanager.gui.data.GUIBill;
import ch.buhls.billmanager.gui.data.GUIFinancialYear;
import ch.buhls.billmanager.gui.data.GUIRole;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author simon
 */
public class GUIStringCollection
{
    public final static String APPLICATION_TITLE = "Club Manager 0.3";
    
    public static final String DB_ID = "DB_ID";
    public static final String DB_VERSION = "DB_V";
    
    public static final String ID = "ID";
    public static final String TRACKED_ENRITY_VERSION_NR = "V";
    public static final String TRACKED_ENRITY_CHANGE_TXT = "Änderungstext";
    public static final String TRACKED_ENRITY_DATE = "Erstelldatum";
    
    public static final String PERSON_BASE_DATA_DB_ID = "Daten DB_ID";
    public static final String PERSON_BASE_DATA_DB_VERSION = "Daten DB_Version";
    public static final String PERSONAL_ID = "ID";
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
    
    public static final String PERSON_NR_OF_ART = "WK";
    public static final String PERSON_NR_OF_ROLES = "Ro.";
    public static final String PERSON_NR_OF_BILLS = "Re.";
    
    public static final String PERSON_BILL = "Rechnung eröffnen";
    public static final String PERSON_NEW_BILL = "Rechnung eröffnen";
    public static final String PERSON_REGISTER_BILL = "Rechnung nacherfassen";
    public static final String PERSON_SHOW_BILL = "Rechnung anzeigen";
    public static final String PERSON_ADD_ART_TO_BILL = "Markierter Artikel hinzufügen";
    public static final String PERSON_ADD_ROLE = "Markierte Rolle hinzufügen";
    public static final String PERSON_REMOVE_ROLE = "Rolle entfernen";
    public static final String PERSON_ADD_1 = "1 hinzufügen";
    public static final String PERSON_ADD_2 = "2 hinzufügen";
    public static final String PERSON_ADD_3 = "3 hinzufügen";
    public static final String PERSON_ADD_4 = "4 hinzufügen";
    public static final String PERSON_ADD_5 = "5 hinzufügen";
    public static final String PERSON_ADD_CUSTOM = "Andere Anzahl...";
    public static final String PERSON_SHOW_BUSKET = "Warenkorb anzeigen";
    public static final String PERSON_SHOW_ROLES = "Rollen anzeigen";
    
    public static final String PERSON_FILTER = "Filtern";
    public static final String PERSON_ROLE_FILTER = "Rollenfilter";
    public static final String PERSON_ADD_FILTER_SHOW_ROLE_MEMBER = "Rolleninhaber anzeigen";
    public static final String PERSON_ADD_FILTER_HIDE_ROLE_MEMBER = "Rolleninhaber ausblenden";
    public static final String PERSON_AGE_FILTER = "Altersfilter";
    public static final String PERSON_AGE_FILTER_OLDER_EQUAL = "Älter oder gleich...";
    public static final String PERSON_AGE_FILTER_OLDER = "Älter...";
    public static final String PERSON_AGE_FILTER_YOUNGER_EQUAL = "Jünger oder gleich...";
    public static final String PERSON_AGE_FILTER_YOUNGER = "Jünger...";
    public static final String PERSON_AGE_FILTER_EQUAL = "Gleich...";
    
    
    public static final String POSITION_AMOUNT = "Menge";
    public static final String POSITION_POS = "Position";
    public static final String POSITION_REMOVE_POSITION = "Pos. entfernen";
    public static final String POSITION_EDIT_POSITION = "Pos. bearbeiten";
    public static final String POSITION_SAVE_POSITION = "Pos. speichern";
    public static final String POSITION_ADD_MARKED_POSITION = "Markierte Pos. hinzufügen";
    
    public static final String BILLS = "Rechnungen";
    public static final String BILL_STATUS = "Status";
    public static final String BILL_STATUS_FILTER = "Status Filter";
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
    public static final String BILL_PATH_TO_FILE = "Pfad zu Datei";
    public static final String BILL_SELECT_BILL_TO_REGISTER = "Datei zum nacherfassen auswählen";
    public static final String BILL_ROLE_FILTER = "Rollenfilter";
    public static final String BILL_SHOW_BILLS_FROM_ROLE_MEMBERS = "Rechnungen anzeigen";
    
    public static final String ART_TITLE = "Titel";
    public static final String ART_DESCRIPTION = "Beschrieb";
    public static final String ART_CATEGORIE = "Kategorie";
    public static final String ART_PRICE = "Preis [Rp]";
    
    public static final String ROLE_NAME = "Bezeichnung";
    
    public static final String TEMPLATE_NAME = "Bezeichnung";
    public static final String TEMPLATE_MAX_POS = "Max. Anzahl Positionen";
    
    public static final String YEAR_NAME = "Bezeichnung";
    public static final String YEAR_PREFIX = "Präfix";
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
    public static final String FILTER = "Filtern";
    public static final String CHANGE_STATE_TO_PAID = "bezahlt am %s";
    
    public static final String DIFFERENT_DATA = "<diff>";
    
    public static final String IMPORT_PERS_READ_FILE = "CSV-Datei einlesen";
    public static final String IMPORT_PERS_STORE_DATA = "Daten übernehmen";
    public static final String IMPORT_PERSONS_TAB_NAME = "Personen von CSV-Datei importieren";
    public static final String IMPORT_PERSONS_FILE_CHOOSER_TXT = "CSV-Datei auswählen";
    public static final String IMPORT_PERSONS_FILE_FORMAT = "Name;Vornamen;Firma;Geburtstag;Geburtsmonat;Geburtsjahr;Strasse;PLZ;Ort;TelP;TelM;E-Mail;Begrüssung;Titel";
    
    public static final String APP_SETTINGS_INKSCAPE_PATH = "Pfad zu Inkscape";
    public static final String APP_SETTINGS_SHOW_DB_INFOS = "Datenbank Informationen anzeigen";
    
    
    public static String getTitleForCreateBill(){
        return "Rechnung erstellen";
    }
    public static String getTitleForEditAppSettings(){
        return "App Einstellungen bearbeiten";
    }
    
    public static String getItemText_setStateToPaid(LocalDate date){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        return String.format(CHANGE_STATE_TO_PAID, dtf.format(date));
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
    
    public static String getTabTitle_listArticles(){
        return "Artikel";
    }
    
    public static String getHintTxt_yearMarked(GUIFinancialYear year){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        return String.format("Saison \"%s\" (%s bis %s) markiert", year.getName().get(), dtf.format(year.getFirstDay().get()), dtf.format(year.getLastDay().get()));
    }
    public static String getHintTxt_artMarked(GUIArticle art){
        return String.format("Artikel \"%s; %s\" markiert", art.getTitle().get(), art.getDescription().get());
    }
    public static String getHintTxt_roleMarked(GUIRole role){
        return String.format("Rolle \"%s\" markiert", role.getName().get());
    }
    public static String getHintTxt_showRoleFilter(GUIRole role){
        return String.format("Inhaber der Rolle \"%s\" angezeigt", role.getName().get());
    }
    public static String getHintTxt_hideRoleFilter(GUIRole role){
        return String.format("Inhaber der Rolle \"%s\" ausgeblendet", role.getName().get());
    }
    public static String getHintTxt_showBillStatus(GUIBill.GUIBillStatus status){
        return String.format("Rechnungen mit Status \"%s\" angezeigt", status);
    }
    
    public static String getHintTxt_roleAdded(GUIRole role, int nrAdded){
        return String.format("Rolle \"%s\" zu %d Personen hinzugefügt", role.getName().get(), nrAdded);
    }
    public static String getHintTxt_artAdded(GUIArticle art, int nrPers, int nrArt){
        return String.format("Artikel \"%s; %s\" zu %d Personen jeweils %d mal hinzugefügt", art.getTitle().get(), art.getDescription().get(), nrPers, nrArt);
    }
    public static String getHintTxt_welcome(){
        return "Willkommen beim ClubManager. Erzeuge ein Projekt oder öffne ein Existierendes.";
    }
    public static String getHintTxt_projectOpened(File projectFile){
        return String.format("Projekt \"%s\" geöffnet", projectFile.getPath());
    }
    public static String getHintTxt_appSettingsStored(){
        return "App Einstellungen gespeichert";
    }
    
    
    public static String getFilterHintTxt_OlderOrEqual(GUIFinancialYear year, int age){
        return String.format("Personen, die in Saison \"%s\" ein Alter von >= %d Jahre haben werden angezeigt", year.getName().get(), age);
    }
    public static String getFilterHintTxt_Older(GUIFinancialYear year, int age){
        return String.format("Personen, die in Saison \"%s\" ein Alter von > %d Jahre haben werden angezeigt", year.getName().get(), age);
    }
    public static String getFilterHintTxt_YoungerOrEqual(GUIFinancialYear year, int age){
        return String.format("Personen, die in Saison \"%s\" ein Alter von <= %d Jahre haben werden angezeigt", year.getName().get(), age);
    }
    public static String getFilterHintTxt_Younger(GUIFinancialYear year, int age){
        return String.format("Personen, die in Saison \"%s\" ein Alter von < %d Jahre haben werden angezeigt", year.getName().get(), age);
    }
    public static String getFilterHintTxt_Equal(GUIFinancialYear year, int age){
        return String.format("Personen, die in Saison \"%s\" ein Alter von == %d Jahre haben werden angezeigt", year.getName().get(), age);
    }
}
