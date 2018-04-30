package ch.buhls.billmanager.gui.framework;

import ch.buhls.billmanager.gui.view.container.SplitScreen;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author simon
 */
public class GUIFramework implements IGUIFramework
{

    public static GUIFramework INSTANCE;

    private final Stage mainStage;
    private SplitScreen splitScreen;

    public GUIFramework(Stage mainStage) {
        this.mainStage = mainStage;
        INSTANCE = this;
    }

    @Override
    public File openPathChooser(String title, File lastDirectory) {
        DirectoryChooser chooser = new DirectoryChooser();
        if (lastDirectory != null) {
            chooser.setInitialDirectory(lastDirectory);
        }
        chooser.setTitle(title);

        return chooser.showDialog(mainStage);
    }

    @Override
    public File openFileChooser(String title, File lastDirectory) {
        FileChooser chooser = new FileChooser();

        if (lastDirectory != null) {
            if (!lastDirectory.isDirectory()) {
                lastDirectory = lastDirectory.getParentFile();
            }

            chooser.setInitialDirectory(lastDirectory);
        }
        chooser.setTitle(title);

        return chooser.showOpenDialog(mainStage);
    }

    @Override
    public File openFileSaveDialoque(String title, File lastDirectory) {
        FileChooser chooser = new FileChooser();
        if (lastDirectory != null) {
            if (!lastDirectory.isDirectory()) {
                lastDirectory = lastDirectory.getParentFile();
            }

            chooser.setInitialDirectory(lastDirectory);
        }
        chooser.setTitle(title);

        return chooser.showSaveDialog(mainStage);
    }

    @Override
    public void showExceptionDialoque(Exception ex) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Fehler aufgetreten");
        alert.setHeaderText("Während dem Ausführen des Programms ist ein Fehler aufgetreten.");
        alert.setContentText("Details:");

        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        pw.append(ex.getMessage());
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(textArea, 0, 0);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait();
    }

    public void setTitle(String title) {
        this.mainStage.setTitle(title);
    }

    @Override
    public ITabHandle displayMask(Node node, String title, Area area) {
        TabPane tabPane = splitScreen.getMainTabPane(); // default

        if (area == Area.RIGHT) {
            tabPane = splitScreen.getDetailTabPane();
        }

        Tab tab = new Tab(title, node);
        tabPane.getTabs().add(tab);
        tabPane.requestFocus();

        return new TabContainer(tabPane, tab);
    }

    @Override
    public boolean showConfirmationDialoque(String title, String header, String content) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    @Override
    public void displayMainMask(Parent parent, SplitScreen splitScreen) {
        this.splitScreen = splitScreen;

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Scene scene = new Scene(parent, 500, 500);
        mainStage.setTitle("HBC Münsingen Manager");
        mainStage.setScene(scene);
        
        mainStage.show();
    }

    @Override
    public boolean confirmToStore() {
        return showConfirmationDialoque("Bestätigung erforderlich", "Bestätigung erforderlich", "Sind Sie sicher, dass Sie Speichern wollen?");
    }

    @Override
    public boolean confirmToAddRole() {
        return showConfirmationDialoque(
                "Bestätigung erforderlich", 
                "Rolle hinzufügen", 
                "Sind Sie sicher, dass Sie die markierte Rolle hinzufügen wollen? (Die Änderungen werden direkt gespeichert.)");
    }

    @Override
    public boolean confirmToRemoveRole() {
        return showConfirmationDialoque(
                "Bestätigung erforderlich", 
                "Rolle entfernen", 
                "Sind Sie sicher, dass Sie die Rolle entfernen wollen? (Die Änderungen werden direkt gespeichert.)");
    }

    @Override
    public boolean confirmToAddArticle() {
        return showConfirmationDialoque(
                "Bestätigung erforderlich", 
                "Artikel hinzufügen", 
                "Sind Sie sicher, dass Sie den Artikel hinzufügen wollen? (Die Änderungen werden direkt gespeichert.)");
    }
}
