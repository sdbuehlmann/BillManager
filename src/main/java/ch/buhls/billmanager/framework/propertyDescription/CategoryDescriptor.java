package ch.buhls.billmanager.framework.propertyDescription;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author buhls
 */
public class CategoryDescriptor
{
    private final String key;
    
    private final List<PropertyDescriptor> properties;

    public CategoryDescriptor(String key)
    {
        this.key = key;
        
        this.properties = new ArrayList<>();
    }

    public String getKey()
    {
        return key;
    }

    public List<PropertyDescriptor> getProperties()
    {
        return properties;
    }
    
    
}
