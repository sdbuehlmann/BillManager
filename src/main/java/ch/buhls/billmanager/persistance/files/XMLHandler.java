package ch.buhls.billmanager.persistance.files;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXB;

/**
 *
 * @author simon
 */
public class XMLHandler
{

    private static final Logger LOG = Logger.getLogger(XMLHandler.class.getName());

    public XMLHandler() {
    }

    public void store(History history) {
        File historyFile = getHistoryFile();
        try {
            if (!historyFile.exists()) {
                historyFile.createNewFile();
                LOG.log(Level.INFO, "New history.xml file created");
            }
            JAXB.marshal(history, historyFile);
            LOG.log(Level.INFO, "history.xml file changed");
        }
        catch (Exception ex) {
            LOG.log(Level.INFO, null, ex);
        }
    }

    public History load() {
        File historyFile = getHistoryFile();
        try {
            if (historyFile.exists()) {
                History temp = JAXB.unmarshal(historyFile, History.class);
                LOG.log(Level.INFO, "history.xml succesfully loaded");
                return temp;
            }
        }
        catch (Exception ex) {
            Logger.getLogger(XMLHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        LOG.log(Level.INFO, "Can not load history.xml");
        return new History();
    }

    public File getHistoryFile() {
        File jarFile = new File(XMLHandler.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        //File historyFile = new File(jarFile.getParent() + "/history.xml");
        File historyFile = new File(jarFile.getParentFile().getParent() + "/history.xml");
        
        return historyFile;
    }
}
