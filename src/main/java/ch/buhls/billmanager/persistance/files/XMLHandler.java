package ch.buhls.billmanager.persistance.files;

import java.io.File;
import javax.xml.bind.JAXB;

/**
 *
 * @author simon
 */
public class XMLHandler
{
    public static XMLHandler INSTANCE = new XMLHandler();
    private History history;

    private XMLHandler()
    {
        history = load();
    }
    
    public static File loadFile(String path)
    {
        try
        {
            return new File(path);
            
        }
        catch (Exception ex)
        {
            return new File("user.home");
        }
    }
    
    public void store()
    {
       JAXB.marshal(history, new File(this.getClass().getClassLoader().getResource("META-INF/history.xml").getPath()));
    }
    
    public History load()
    {
        History history = JAXB.unmarshal(this.getClass().getClassLoader().getResource("META-INF/history.xml"), History.class );
        return history;
    }
    
    // getter and setter
    public History getHistory()
    {
        return history;
    }
    
    public ProjectInfo loadProjectInfo(File file){
        return JAXB.unmarshal(file, ProjectInfo.class );
    }
    
    public void storeProjectInfo(File file, ProjectInfo projectInfo){
        JAXB.marshal(projectInfo, file);
    }
}
