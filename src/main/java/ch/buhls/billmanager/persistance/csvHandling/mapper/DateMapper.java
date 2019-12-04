package ch.buhls.billmanager.persistance.csvHandling.mapper;

import java.util.Date;

public class DateMapper implements IFieldMapper<Date> {

	@Override
	public String mapPropertyToString(Date propertyValue) {
		return propertyValue.toString();
	}

	@Override
	public Date mapStringToProperty(String value) throws MappingException {
		throw new MappingException("Not supported yet.");
	}
}
