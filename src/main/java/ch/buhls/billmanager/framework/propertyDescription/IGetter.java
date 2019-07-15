package ch.buhls.billmanager.framework.propertyDescription;

/**
 * Functional interface, which can return a value of a property.
 * @param <TOwner> Type of the property owner
 * @param <TProperty> Type of the property value
 */
@FunctionalInterface     
public interface IGetter<TOwner, TProperty>
{
    /**
     * Does return the value
     * @param owner Owner of the property
     * @return Value of the property
     */
    TProperty Get(TOwner owner);
}
