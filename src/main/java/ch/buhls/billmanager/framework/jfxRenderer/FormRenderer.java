package ch.buhls.billmanager.framework.jfxRenderer;

import ch.buhls.billmanager.framework.propertyDescription.CategoryDescriptor;
import ch.buhls.billmanager.framework.propertyDescription.PropertiesView;
import ch.buhls.billmanager.framework.propertyDescription.PropertyDescriptor;
import ch.buhls.billmanager.gui.data.properties.IPropertyData;
import ch.buhls.billmanager.gui.data.properties.StringAdapterProperty;
import java.util.List;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author buhls
 */
public class FormRenderer
{

    public static <TOwner> GridPane render(PropertiesView<TOwner> properties, TOwner owner)
    {
        FormGridContainer gridContainer = new FormGridContainer();

        List<CategoryDescriptor> categories = properties.getCategories();
        for (CategoryDescriptor category : categories) {
            gridContainer.addCategoryRow(category.getKey());

            for (PropertyDescriptor property : category.getProperties()) {
                if (property.getTypeProperty() == String.class) {
                    gridContainer.addPropertyRow(property.getKey(), createTextField(owner, property));
                }
            }
        }

        return gridContainer.getGrid();
    }

    private static TextField createTextField(Object owner, PropertyDescriptor property)
    {
        StringAdapterProperty adapter = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get()
            {
                return (String) property.getGetter().Get(owner);
            }

            @Override
            public void set(String set)
            {
                property.getSetter().Set(owner, set);
            }

            @Override
            public Object getBean()
            {
                return owner;
            }

            @Override
            public String getName()
            {
                return property.getKey();
            }
        });

        TextField textfield = new TextField();
        textfield.textProperty().bindBidirectional(adapter);

        return textfield;
    }
}
