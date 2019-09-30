package ch.buhls.billmanager.framework.jfxRenderer;

import ch.buhls.billmanager.framework.propertyDescription.CategoryDescriptor;
import ch.buhls.billmanager.framework.propertyDescription.PropertiesView;
import ch.buhls.billmanager.framework.propertyDescription.PropertyDescriptor;
import ch.buhls.billmanager.gui.data.properties.IPropertyData;
import ch.buhls.billmanager.gui.data.properties.IntegerAdapterProperty;
import ch.buhls.billmanager.gui.data.properties.ObjectAdapterProperty;
import ch.buhls.billmanager.gui.data.properties.StringAdapterProperty;
import ch.buhls.billmanager.gui.view.elements.NumberField;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javafx.scene.control.DatePicker;
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
                else if(property.getTypeProperty() == Integer.class){
                    gridContainer.addPropertyRow(property.getKey(), createNumberField(owner, property));
                }
                else if(property.getTypeProperty() == Date.class){
                    gridContainer.addPropertyRow(property.getKey(), createDatePicker(owner, property));
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
        
        if(property.getSetter() == null){
            textfield.setDisable(true);
        }

        return textfield;
    }
    
    private static NumberField createNumberField(Object owner, PropertyDescriptor property)
    {
        IntegerAdapterProperty adapter = new IntegerAdapterProperty(new IPropertyData<Integer>()
        {
            @Override
            public Integer get()
            {
                return (Integer) property.getGetter().Get(owner);
            }

            @Override
            public void set(Integer set)
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
        
        NumberField numberfield = new NumberField();
        numberfield.getValueProperty().bindBidirectional(adapter);
        
        if(property.getSetter() == null){
            numberfield.setDisable(true);
        }
        
        return numberfield;
    }
    
    private static DatePicker createDatePicker(Object owner, PropertyDescriptor property)
    {
        if(property.getTypeProperty() != Date.class){
            throw new RuntimeException("Illegal property descriptor type for a date picker (needs a Date.type)");
        }
        
        ObjectAdapterProperty<LocalDate> adapter = new ObjectAdapterProperty<>(new IPropertyData<LocalDate>()
        {
            @Override
            public LocalDate get()
            {
                if (property.getGetter().Get(owner) == null) {
                    return null;
                }

                Date temp = (Date)property.getGetter().Get(owner);
                return temp.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            }

            @Override
            public void set(LocalDate set)
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
        
        DatePicker datepicker = new DatePicker();
        datepicker.valueProperty().bindBidirectional(adapter);
        
        if(property.getSetter() == null){
            datepicker.setDisable(true);
        }
        
        return datepicker;
    }
}
