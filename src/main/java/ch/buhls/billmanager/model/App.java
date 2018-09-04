
package ch.buhls.billmanager.model;

import ch.buhls.billmanager.persistance.files.History;
import ch.buhls.billmanager.persistance.files.XMLHandler;
import java.io.File;
import java.util.List;

/**
 *
 * @author simon
 */
public class App
{
    public final static App INSTANCE = new App();
    
    private int lastPaymentDeadlineInDays;
    private String lastLocation;
    
    private XMLHandler xmlHandler;
    private History history;
    
    private App(){
        xmlHandler = new XMLHandler();
        history = xmlHandler.load();
    }

    public File getInkscapePath() {
        return new File(history.getInkscapeExe());
    }

    public int getLastPaymentDeadlineInDays() {
        return lastPaymentDeadlineInDays;
    }

    public String getLastLocation() {
        return lastLocation;
    }
    
    public List<String> getRecentOpenedProjects(){
        return history.getRecentProjects();
    }
    
    public void removeFromRecentOpenedProjects(String path){
        history.getRecentProjects().remove(path);
    }
    
    public void addRecentOpenedProject(String path){
        // handle, if the path is already in the recent projects
        if(history.getRecentProjects().contains(path)){
            history.getRecentProjects().remove(path);
        }
        
        // add to recent projects
        history.getRecentProjects().add(0, path);
        
        // handle, if too mouch projects are in the list
        if(history.getRecentProjects().size() > 5){
            for(int cnt = history.getRecentProjects().size() - 1; cnt < 5; cnt--){
                history.getRecentProjects().remove(cnt);
            }
        }
        
        xmlHandler.store(history);
    }
    
    public File getLastPath(){
        try{
            return new File(history.getLastPath());
            
        }
        catch (Exception ex)
        {
            return new File("user.home");
        }
    }
    
    public void setLastPath(File lastPath){
        history.setLastPath(lastPath.getPath());
        xmlHandler.store(history);
    }
}
