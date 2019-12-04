package ch.buhls.billmanager.persistance.csvHandling.mapper;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateMapper implements IFieldMapper<Date> {

	@Override
	public String mapPropertyToString(Date propertyValue) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return simpleDateFormat.format(propertyValue);
	}

	@Override
	public Date mapStringToProperty(String value) throws MappingException {
		throw new MappingException("Not supported yet.");
	}
}
