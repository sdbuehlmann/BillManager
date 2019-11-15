package ch.buhls.billmanager.framework.jfxRenderer;

import ch.buhls.billmanager.framework.propertyDescription.PropertyDescriptor;
import ch.buhls.billmanager.gui.data.properties.IPropertyData;
import ch.buhls.billmanager.gui.data.properties.IntegerAdapterProperty;
import ch.buhls.billmanager.gui.data.properties.ObjectAdapterProperty;
import ch.buhls.billmanager.gui.data.properties.StringAdapterProperty;
import ch.buhls.billmanager.gui.view.elements.NumberField;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ObservablePropertyAdapterFactory {

    public static StringAdapterProperty createStringAdapter(Object owner, PropertyDescriptor property) {
        StringAdapterProperty adapter = new StringAdapterProperty(new IPropertyData<String>()
        {
            @Override
            public String get() {
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

        return adapter;
    }

    public static IntegerAdapterProperty createIntegerAdapter(Object owner, PropertyDescriptor property) {
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

        return adapter;
    }

    public static ObjectAdapterProperty<LocalDate> createLocalDateAdapter(Object owner, PropertyDescriptor property) {
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

        return adapter;
    }
}
