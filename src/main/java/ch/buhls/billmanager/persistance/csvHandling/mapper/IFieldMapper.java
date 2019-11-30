package ch.buhls.billmanager.persistance.csvHandling.mapper;

public interface IFieldMapper<TProperty> {
	String mapPropertyToString(TProperty propertyValue);
	TProperty mapStringToProperty(String value) throws MappingException;
}
