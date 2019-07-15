
package ch.buhls.billmanager.framework.propertyDescription;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author buhls
 * @param <TOwner>
 */
public class PropertiesView<TOwner>
{
    private final String key;
    private final Class typeHolder;
    
    private final List<CategoryDescriptor> categories;
    private CategoryDescriptor currentCategory;

    /**
     * 
     * @param key
     * @param typeHolder The generic type TOwner will be erased by compiler. But
     * this type will be used to render GUI elements, so the type needs to be 
     * defined again.
     */
    public PropertiesView(String key, Class typeHolder)
    {
        this.key = key;
        this.typeHolder = typeHolder;
        
        this.categories = new ArrayList<>();
    }
    
    public void addCategory(String key){
        currentCategory = new CategoryDescriptor(key);
        categories.add(currentCategory);
    }
    
    public <TProperty> void addProperty(String key, Class typeProperty, IGetter<TOwner,TProperty> getter, ISetter<TOwner,TProperty> setter){
        PropertyDescriptor desc = new PropertyDescriptor(typeHolder, typeProperty, key, getter, setter);
        currentCategory.getProperties().add(desc);
    }
    
    public <TProperty> void addProperty(String key, Class typeProperty, IGetter<TOwner,TProperty> getter){
        PropertyDescriptor desc = new PropertyDescriptor(typeHolder, typeProperty, key, getter, null);
        currentCategory.getProperties().add(desc);
    }
    
    // getter

    public List<CategoryDescriptor> getCategories()
    {
        return categories;
    }
    
}
