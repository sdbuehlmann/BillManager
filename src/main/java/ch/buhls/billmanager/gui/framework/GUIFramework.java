package ch.buhls.billmanager.gui.framework;

import ch.buhls.billmanager.gui.GUIStringCollection;
import ch.buhls.billmanager.gui.view.container.HintBarContainer;
import ch.buhls.billmanager.gui.view.container.HintContainer;
import ch.buhls.billmanager.gui.view.container.SplitScreen;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
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
    private Scene splitScreenScene;
    private SplitScreen splitScreen;

    private HintBarContainer hintBarContainer;
    private HintContainer infoHintContainer;
    
    private final StringCollections stringCollections;

    public GUIFramework(Stage mainStage) {
        this.mainStage = mainStage;
        INSTANCE = this;
        
        stringCollections = new StringCollections();
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
            if(!lastDirectory.isDirectory()) {
                lastDirectory = lastDirectory.getParentFile().getParentFile();
            }
            if(lastDirectory.exists()){
                chooser.setInitialDirectory(lastDirectory);
            }
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

    @Override
    public void setAppTitle(String title) {
        this.mainStage.setTitle(GUIStringCollection.APPLICATION_TITLE + " - " + title);
    }
    
    @Override
    public void setCursorType(CursorType cursorType){
        switch(cursorType){
            case DEFAULT:
                splitScreenScene.setCursor(Cursor.DEFAULT);
                break;
            case WAITING:
                splitScreenScene.setCursor(Cursor.WAIT);
                break;
            case HORIZONTAL_SCALING:
                splitScreenScene.setCursor(Cursor.H_RESIZE);
                break;
            case VERTICAL_SCALING:
                splitScreenScene.setCursor(Cursor.V_RESIZE);
                break;
        }
    }

    @Override
    public ITabHandle displayMask(Node node, String title, Area area) {
        TabPane tabPane = splitScreen.getMainTabPane(); // default

        if (area == Area.RIGHT) {
            tabPane = splitScreen.getDetailTabPane();
        }

        ScrollPane scrollPane = new ScrollPane(node);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        Tab tab = new Tab(title, scrollPane);
        tabPane.getTabs().add(tab);
        tabPane.requestFocus();

        return new TabContainer(tabPane, tab);
    }

    @Override
    public void closeAllMasks() {
        splitScreen.getMainTabPane().getTabs().clear();
    }

    @Override
    public boolean showConfirmDialoque(String header, String content) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setResizable(true);
        alert.setTitle(stringCollections.getAppStringCollection().getAppTitle());
        alert.setHeaderText(header);
        alert.setContentText(content);

        Optional<ButtonType> result = alert.showAndWait();
        return result.get() == ButtonType.OK;
    }

    @Override
    public boolean showConfirmDialoque(DialoquesStringsTO to) {
        return showConfirmDialoque(to.getHeader(), to.getText());
    }
    
    @Override
    public void displayMainMask(Parent parent, SplitScreen splitScreen, HintBarContainer hintBarContainer) {
        this.splitScreen = splitScreen;
        this.hintBarContainer = hintBarContainer;

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        splitScreenScene = new Scene(parent, 500, 500);
        splitScreenScene.getStylesheets().add("styles/Styles.css");
        splitScreenScene.getStylesheets().add("styles/PropertyGridStyles.css");

        mainStage.setTitle(GUIStringCollection.APPLICATION_TITLE);
        mainStage.setScene(splitScreenScene);

        mainStage.show();
    }

    @Override
    public boolean showConfirmToStoreDialoque(String content) {
        return showConfirmDialoque(stringCollections.getAppStringCollection().getConfirmToStoreDialoqueHeader(), content);
    }

    @Override
    public void showInfoDialoque(String header, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @Override
    public void showInfo_canNotOpenProject() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText("Öffnen fehlgeschlagen");
        alert.setContentText("Das Projekt kann nicht geöffnet werden.");
        alert.showAndWait();
    }

    @Override
    public void displayInfoHint(String text) {
        // handle old
        if (infoHintContainer != null) {
            hintBarContainer.removeHint(infoHintContainer);
            infoHintContainer = null;
        }

        // create new
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();

        infoHintContainer = new HintContainer(new Label(String.format("%s Uhr: %s", dtf.format(now), text)), () -> {
            hintBarContainer.removeHint(infoHintContainer);
            infoHintContainer = null;
        });
        infoHintContainer.getView().getStyleClass().add(GUIStyle.STYLECLASS_INFO_HINT);

        hintBarContainer.addHintOnTop(infoHintContainer);
    }

    @Override
    public String showTextInputDialoque(String headerTxt, String labelTextField) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle(stringCollections.getAppStringCollection().getAppTitle());
        dialog.setHeaderText(headerTxt);
        dialog.setContentText(labelTextField);

        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }
    
    @Override
    public String showTextInputDialoque(DialoquesStringsTO to) {
        return showTextInputDialoque(to.getHeader(), to.getText());
    }


    @Override
    public IHintHandle displayMarkedHint(String text, IHintListener listener) {
        // create new
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime now = LocalDateTime.now();

        HintContainer hintCont = new HintContainer(new Label(String.format("%s Uhr: %s", dtf.format(now), text)), listener);
        hintCont.getView().getStyleClass().add(GUIStyle.STYLECLASS_MARKED_HINT);

        hintBarContainer.addHint(hintCont);

        return () -> {
            hintBarContainer.removeHint(hintCont);
        };
    }

    @Override
    public StringCollections getStringCollections() {
        return stringCollections;
    }

    
    
}
