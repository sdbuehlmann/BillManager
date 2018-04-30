package ch.buhls.billmanager.persistance.database.entities;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import org.eclipse.persistence.annotations.OptimisticLocking;

/**
 *
 * @author sdb
 * @param <T>
 */
@MappedSuperclass
@OptimisticLocking
public abstract class AEntity <T extends AEntity> implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Version
    protected int version;

    public AEntity()
    {

    }

    // getter & setter

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public int getVersion()
    {
        return version;
    }

    public void setVersion(int version)
    {
        this.version = version;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o == null)
        {
            return false;
        }

        if (!(o instanceof AEntity))
        {
            return false;
        }

        AEntity other = (AEntity) o;
//        return (other.getId() == this.getId() &&
//                other.getVersion() == this.getVersion());
        return (other.getId() == this.getId());
    }
    
    /**
     * Copies the data of this entity into the handed entity
     * @param other 
     */
    public void copyData(T other){
        other.id = id;
        other.version = version;
    }
}
