
package ch.buhls.billmanager.framework.propertyDescription;

/**
 *
 * @author buhls
 */
public class PropertyDescriptor
{
    private final Class typeOwner;
    private final Class typeProperty; 
    private final String key;
    private final IGetter getter;
    private final ISetter setter;

    public PropertyDescriptor(Class typeOwner, Class typeProperty, String key, IGetter getter, ISetter setter)
    {
        this.typeOwner = typeOwner;
        this.typeProperty = typeProperty;
        this.key = key;
        this.getter = getter;
        this.setter = setter;
    }

    public Class getTypeOwner()
    {
        return typeOwner;
    }

    public Class getTypeProperty()
    {
        return typeProperty;
    }

    public String getKey()
    {
        return key;
    }

    public IGetter getGetter()
    {
        return getter;
    }

    public ISetter getSetter()
    {
        return setter;
    }
}
