package ch.buhls.billmanager.utils;

import java.util.Optional;

/**
 * Does bundle all information about a property
 */
public interface IPropertyDescriptor {
    Class getType();

    String getKey();

    IGetter getGetter();

    Optional<ISetter> getSetter();

    AccessType getAccessType();

    enum AccessType{
        READ,
        READ_WRITE;
    }
}
