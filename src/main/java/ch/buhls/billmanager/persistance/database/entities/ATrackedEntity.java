
package ch.buhls.billmanager.persistance.database.entities;

import java.util.Date;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author simon
 * @param <T>
 */
@MappedSuperclass
public abstract class ATrackedEntity <T extends ATrackedEntity> extends AEntity<T>
{
    protected String changeTxt;
    protected int versionNr;
    
    @Temporal(TemporalType.DATE)
    protected Date dateAdded;
    
    @OneToOne
    protected T previousVersion, followingVersion;

    public ATrackedEntity() {
    }

    public String getChangeTxt() {
        return changeTxt;
    }

    public void setChangeTxt(String changeTxt) {
        this.changeTxt = changeTxt;
    }

    public T getPreviousVersion() {
        return previousVersion;
    }

    public void setPreviousVersion(T previousVersion) {
        this.previousVersion = previousVersion;
    }

    public T getFollowingVersion() {
        return followingVersion;
    }

    public void setFollowingVersion(T followingVersion) {
        this.followingVersion = followingVersion;
    }

    public int getVersionNr() {
        return versionNr;
    }

    public void setVersionNr(int versionNr) {
        this.versionNr = versionNr;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
    
    @Override
    public void copyData(T other) {
        super.copyData(other);
        
        other.changeTxt = changeTxt;
        other.versionNr = versionNr;
        other.previousVersion = previousVersion;
        other.followingVersion = followingVersion;
        other.dateAdded = new Date(dateAdded.getTime());
    }
}
