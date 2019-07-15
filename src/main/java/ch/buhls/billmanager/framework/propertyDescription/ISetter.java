package ch.buhls.billmanager.framework.propertyDescription;

/**
 * Functional interface, which can set a value of a property.
 * 
 * @param <TOwner> Type of the property owner
 * @param <TProperty> Type of the property value
 */
public interface ISetter<TOwner, TProperty>
{
     /**
     * Does set the value of a property
     * @param owner Owner of the property
     * @param value New value of the property
     */
    void Set(TOwner owner, TProperty value);
}
