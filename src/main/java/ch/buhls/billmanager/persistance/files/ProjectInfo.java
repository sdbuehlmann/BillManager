
package ch.buhls.billmanager.persistance.files;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author simon
 */
@XmlRootElement
public class ProjectInfo
{
    private String version;

    @XmlElement
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
