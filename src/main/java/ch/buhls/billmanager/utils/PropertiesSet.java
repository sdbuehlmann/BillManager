package ch.buhls.billmanager.utils;

import java.util.ArrayList;
import java.util.List;

public class PropertiesSet implements IPropertiesSet{
    private final String key;
    private final Class typeOwner;

    private final List<IPropertyDescriptor> properties;

    public PropertiesSet(String key, Class typeOwner) {
        this.key = key;
        this.typeOwner = typeOwner;
        this.properties = new ArrayList<>();
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Class getTypeOwner() {
        return typeOwner;
    }

    @Override
    public List<IPropertyDescriptor> getProperties() {
        return properties;
    }
}
