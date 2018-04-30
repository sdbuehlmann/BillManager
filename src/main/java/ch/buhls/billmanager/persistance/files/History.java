
package ch.buhls.billmanager.persistance.files;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sdb
 */
@XmlRootElement
public class History
{
    private String lastOpenFilePath;
    private String lastImportFilePath;
    
    private String inkscapeExe;

    @XmlElement
    public String getLastOpenFilePath()
    {
        return lastOpenFilePath;
    }

    public void setLastOpenFilePath(String lastOpenFilePath)
    {
        this.lastOpenFilePath = lastOpenFilePath;
    }

    @XmlElement
    public String getLastImportFilePath()
    {
        return lastImportFilePath;
    }

    public void setLastImportFilePath(String lastImportFilePath)
    {
        this.lastImportFilePath = lastImportFilePath;
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
    
    
    
}
