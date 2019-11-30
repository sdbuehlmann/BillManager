package ch.buhls.billmanager.utils;

public class PropertiesSetBuilder<TOwner> {

    private final PropertiesSet propertiesSet;

    public PropertiesSetBuilder(String key, Class typeOwner) {
        propertiesSet = new PropertiesSet(key, typeOwner);
    }

    public PropertiesSetBuilder(Class typeOwner) {
        propertiesSet = new PropertiesSet(typeOwner.getSimpleName(), typeOwner);
    }

    public <TProperty> PropertiesSetBuilder<TOwner> addProperty(String key, Class typeProperty, IGetter<TOwner,TProperty> getter, ISetter<TOwner,TProperty> setter){
        propertiesSet.getProperties().add(new PropertyDescriptor(typeProperty, key, getter, setter));
        return this;
    }

    public <TProperty> PropertiesSetBuilder<TOwner> addProperty(String key, Class typeProperty, IGetter<TOwner,TProperty> getter){
        propertiesSet.getProperties().add(new PropertyDescriptor(typeProperty, key, getter));
        return this;
    }

    public IPropertiesSet getPropertiesSet(){
        return propertiesSet;
    }
}
