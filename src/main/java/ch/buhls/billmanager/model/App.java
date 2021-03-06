
package ch.buhls.billmanager.model;

import ch.buhls.billmanager.persistance.files.History;
import ch.buhls.billmanager.persistance.files.XMLHandler;
import java.io.File;
import java.time.LocalDate;
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
    private LocalDate lastUsedDate;
    
    private final XMLHandler xmlHandler;
    private final History history;
    
    private App(){
        xmlHandler = new XMLHandler();
        history = xmlHandler.load();
        lastUsedDate = LocalDate.now();
    }
    
    public boolean isShowDBInfos(){
        return history.isShowDBInfos();
    }
    
    public void setShowDBInfos(boolean showDBInfos){
        history.setShowDBInfos(showDBInfos);
        xmlHandler.store(history);
    }
    
    public File getInkscapePath() throws AppException {
        String tempPath = history.getInkscapeExe();
        if(tempPath != null){
            File tempFile = new File(tempPath);
            if(tempFile.exists() &&
                    tempFile.canExecute()){
                return tempFile;
            }
        }
        
        throw new AppException("No valid Inkscape-path in history.xml file");
    }
    
    public void setInkscapePath(String path){
        history.setInkscapeExe(path);
        xmlHandler.store(history);
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
        xmlHandler.store(history);
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

    public LocalDate getLastUsedDate() {
        return lastUsedDate;
    }

    public void setLastUsedDate(LocalDate lastUsedDate) {
        this.lastUsedDate = lastUsedDate;
    }
    
    
}
