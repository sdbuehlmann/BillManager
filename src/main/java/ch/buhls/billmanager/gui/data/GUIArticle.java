
package ch.buhls.billmanager.gui.data;

import ch.buhls.billmanager.gui.data.properties.IPropertyData;
import ch.buhls.billmanager.gui.data.properties.IntegerAdapterProperty;
import ch.buhls.billmanager.gui.data.properties.StringAdapterProperty;
import ch.buhls.billmanager.persistance.database.entities.Article;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;


/**
 *
 * @author simon
 */
public class GUIArticle extends AGUITrackedData<Article>
{
    private final StringAdapterProperty title, description, internalCategorie;
    private final IntegerAdapterProperty costs;
    
    public GUIArticle(Article t) {
        super(t);
        
        title = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get() {
                return getData().getTitle();
            }

            @Override
            public void set(String set) {
                getData().setTitle(set);
            }

            @Override
            public Object getBean() {
                return getData();
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
                return getData().getDescription();
            }

            @Override
            public void set(String set) {
                getData().setDescription(set);
            }

            @Override
            public Object getBean() {
                return getData();
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
                return getData().getInternalCategorie();
            }

            @Override
            public void set(String set) {
                getData().setInternalCategorie(set);
            }

            @Override
            public Object getBean() {
                return getData();
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
                return getData().getCosts();
            }

            @Override
            public void set(Integer set) {
                getData().setCosts(set);
            }

            @Override
            public Object getBean() {
                return getData();
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

    @Override
    public void informBounded() {
        title.markInvalid();
        description.markInvalid();
        internalCategorie.markInvalid();
        costs.markInvalid();
    }
}
