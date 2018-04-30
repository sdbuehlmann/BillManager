
package ch.buhls.billmanager.gui.data;

import ch.buhls.billmanager.gui.data.properties.IPropertyData;
import ch.buhls.billmanager.gui.data.properties.IntegerAdapterProperty;
import ch.buhls.billmanager.persistance.database.entities.AEntity;

/**
 *
 * @author simon
 * @param <T>
 */
public abstract class AGUIData<T extends AEntity>
{
    protected final T data;
    
    protected final IntegerAdapterProperty db_id;
    protected final IntegerAdapterProperty db_version;

    public AGUIData(T t)
    {
        this.data = t;
        
        db_id = new IntegerAdapterProperty(new IPropertyData<Integer>()
        {
            @Override
            public Integer get() {
                return data.getId();
            }

            @Override
            public void set(Integer set) {
                data.setId(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return "DB_ID";
            }
        });
        
        db_version = new IntegerAdapterProperty(new IPropertyData<Integer>()
        {
            @Override
            public Integer get() {
                return data.getVersion();
            }

            @Override
            public void set(Integer set) {
                data.setVersion(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return "DB_Version";
            }
        });
    }

    public T getData() {
        return data;
    }

    public IntegerAdapterProperty getDb_id() {
        return db_id;
    }

    public IntegerAdapterProperty getDb_version() {
        return db_version;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || 
                !(obj instanceof AGUIData)){
            return false;
        }
            
        AGUIData other = (AGUIData)obj;
        
        return this.getData().getId() == other.getData().getId() 
                && this.getData().getVersion()== other.getData().getVersion();
    }
    
    
}
