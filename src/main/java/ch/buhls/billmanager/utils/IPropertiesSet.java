package ch.buhls.billmanager.utils;

import java.util.List;

public interface IPropertiesSet {
    String getKey();

    Class getTypeOwner();

    List<IPropertyDescriptor> getProperties();
}
