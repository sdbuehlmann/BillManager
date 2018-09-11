package ch.buhls.billmanager;

import ch.buhls.billmanager.gui.framework.GUIFramework;
import ch.buhls.billmanager.gui.framework.IGUIFramework;
import ch.buhls.billmanager.gui.controller.MainMaskController;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;


public class MainApp extends Application {

    private static final Logger LOG = Logger.getLogger(MainApp.class.getName());

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        /*
        Handler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.FINER);
        Logger.getAnonymousLogger().addHandler(consoleHandler);*/

        this.primaryStage = primaryStage;
        
        IGUIFramework framework = new GUIFramework(primaryStage);

        try {
            new MainMaskController(framework);
        }
        catch (Exception ex) {
            framework.showExceptionDialoque(ex);
            throw ex;
        }

        Thread.setDefaultUncaughtExceptionHandler((thread, ex) -> {
            framework.showExceptionDialoque(new Exception(ex));
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
