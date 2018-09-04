
package ch.buhls.billmanager.persistance.files;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sdb
 */
@XmlRootElement
public class History
{
    private String lastPath;
    private String inkscapeExe;
    
    private List<String> recentProjects;

    @XmlElement
    public String getLastPath()
    {
        return lastPath;
    }

    public void setLastPath(String lastPath)
    {
        this.lastPath = lastPath;
    }

    @XmlElement
    public String getInkscapeExe()
    {
        return inkscapeExe;
    }

    public void setInkscapeExe(String inkscapeExe)
    {
        this.inkscapeExe = inkscapeExe;
    }

    @XmlElement
    public List<String> getRecentProjects() {
        if(recentProjects == null){
            recentProjects = new ArrayList<>();
        }
        return recentProjects;
    }

    public void setRecentProjects(List<String> recentProjects) {
        this.recentProjects = recentProjects;
    }
}
