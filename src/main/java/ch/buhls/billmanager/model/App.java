
package ch.buhls.billmanager.model;

import ch.buhls.billmanager.persistance.files.XMLHandler;
import java.io.File;

/**
 *
 * @author simon
 */
public class App
{
    public final static App INSTANCE = new App();
    
    private File inkscapePath;
    
    private int lastPaymentDeadlineInDays;
    private String lastLocation;
    
    private App(){
        XMLHandler.INSTANCE.load();
        inkscapePath = new File(XMLHandler.INSTANCE.getHistory().getInkscapeExe());
    }

    public File getInkscapePath() {
        return inkscapePath;
    }

    public int getLastPaymentDeadlineInDays() {
        return lastPaymentDeadlineInDays;
    }

    public String getLastLocation() {
        return lastLocation;
    }
}
