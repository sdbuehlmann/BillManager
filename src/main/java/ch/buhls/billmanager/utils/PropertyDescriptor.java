package ch.buhls.billmanager.utils;

import java.util.Optional;

public class PropertyDescriptor implements IPropertyDescriptor {

    private final Class typeProperty;
    private final String key;
    private final IGetter getter;
    private final ISetter setter;

    public PropertyDescriptor(Class typeProperty, String key, IGetter getter, ISetter setter)
    {
        this.typeProperty = typeProperty;
        this.key = key;
        this.getter = getter;
        this.setter = setter;
    }

    public PropertyDescriptor(Class typeProperty, String key, IGetter getter)
    {
        this.typeProperty = typeProperty;
        this.key = key;
        this.getter = getter;
        this.setter = null;
    }

    @Override
    public Class getType() {
        return typeProperty;
    }

    @Override
    public String getKey()
    {
        return key;
    }

    @Override
    public IGetter getGetter()
    {
        return getter;
    }

    @Override
    public Optional<ISetter> getSetter()
    {
        if(setter != null){
            return Optional.of(setter);
        }

        return Optional.empty();
    }

    @Override
    public AccessType getAccessType() {
        if(setter != null){
            return AccessType.READ;
        }

        return AccessType.READ_WRITE;
    }
}
