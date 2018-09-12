
package ch.buhls.billmanager.gui.data;

import ch.buhls.billmanager.gui.data.properties.IPropertyData;
import ch.buhls.billmanager.gui.data.properties.IntegerAdapterProperty;
import ch.buhls.billmanager.gui.data.properties.StringAdapterProperty;
import ch.buhls.billmanager.persistance.database.entities.Article;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * @author simon
 */
public class GUIArticle extends AGUITrackedData<Article>
{
    private final StringProperty title, description, internalCategorie;
    private final IntegerProperty costs;
    
    public GUIArticle(Article t) {
        super(t);
        
        title = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get() {
                return data.getTitle();
            }

            @Override
            public void set(String set) {
                data.setTitle(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return "Titel";
            }
        });
        
        description = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get() {
                return data.getDescription();
            }

            @Override
            public void set(String set) {
                data.setDescription(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return "Beschrieb";
            }
        });
        
        internalCategorie = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get() {
                return data.getInternalCategorie();
            }

            @Override
            public void set(String set) {
                data.setInternalCategorie(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return "Kategorie";
            }
        });
        
        costs = new IntegerAdapterProperty(new IPropertyData<Integer>()
        {
            @Override
            public Integer get() {
                return data.getCosts();
            }

            @Override
            public void set(Integer set) {
                data.setCosts(set);
            }

            @Override
            public Object getBean() {
                return data;
            }

            @Override
            public String getName() {
                return "Kosten";
            }
        });
    }

    public StringProperty getTitle() {
        return title;
    }

    public StringProperty getDescription() {
        return description;
    }

    public StringProperty getInternalCategorie() {
        return internalCategorie;
    }

    public IntegerProperty getCosts() {
        return costs;
    }
}
