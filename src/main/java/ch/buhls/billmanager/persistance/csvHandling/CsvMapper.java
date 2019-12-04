package ch.buhls.billmanager.persistance.csvHandling;

import ch.buhls.billmanager.persistance.csvHandling.mapper.DateMapper;
import ch.buhls.billmanager.persistance.csvHandling.mapper.IFieldMapper;
import ch.buhls.billmanager.persistance.csvHandling.mapper.MappingException;
import ch.buhls.billmanager.persistance.csvHandling.reader.CsvReader;
import ch.buhls.billmanager.persistance.csvHandling.reader.Line;
import ch.buhls.billmanager.utils.IPropertiesSet;
import ch.buhls.billmanager.utils.IPropertyDescriptor;
import ch.buhls.billmanager.utils.ISetter;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class CsvMapper<TDataContainer> {
	private final static Logger LOGGER = Logger.getLogger(CsvMapper.class.getName());
	private final Map<Class, IFieldMapper> mappers;

	private LineMappingPolicy mappingPolicy;

	public CsvMapper(LineMappingPolicy mappingPolicy) {
		this.mappingPolicy = mappingPolicy;

		mappers = new HashMap<>();

		mappers.put(String.class, new StringMapper());
		mappers.put(Integer.class, new IntegerMapper());
		mappers.put(Date.class, new DateMapper());
	}

	public List<TDataContainer> map(List<Line> lines, IPropertiesSet propertiesSet){
		return lines.stream()
				.map(line -> lineToContainer(propertiesSet, line))
				.filter(container -> container != null)
				.collect(Collectors.toList());
	}

	public List<Line> map(IPropertiesSet propertiesSet, Collection<TDataContainer> containers){
		return containers.stream()
				.map(dataContainer -> containerToLine(propertiesSet, dataContainer))
				.collect(Collectors.toList());
	}

	public List<Line> mapWithHeader(IPropertiesSet propertiesSet, Collection<TDataContainer> containers){
		List<Line> lines = map(propertiesSet, containers);
		lines.add(0, propertiesSetToHeaderLine(propertiesSet));

		return lines;
	}

	// private methods

	private Line propertiesSetToHeaderLine(IPropertiesSet propertiesSet){
		Line line = new Line();

		propertiesSet.getProperties().stream()
				.forEach(propertyDescriptor -> line.getElements().add(propertyDescriptor.getKey()));

		if(line.getElements().size() > 0){
			// add comment identifier to first element
			line.getElements().set(0, "//" + line.getElements().get(0));
		}

		return line;
	}

	private Line containerToLine(IPropertiesSet propertiesSet, TDataContainer container) {
		Line line = new Line();

		propertiesSet.getProperties().stream()
				.forEach(propertyDescriptor -> line.getElements().add(propertyToString(propertyDescriptor, container)));

		return line;
	}

	private TDataContainer lineToContainer(IPropertiesSet propertiesSet, Line line) {
		TDataContainer container = createContainer(propertiesSet);
		Queue<String> fields = new ArrayDeque<>(line.getElements());

		int elementNr = 0;
		for (IPropertyDescriptor propertyDescriptor : propertiesSet.getProperties()){
			try {
				elementNr++;
				stringToProperty(propertyDescriptor, container, fields.poll());
			} catch (MappingException e) {
				switch(mappingPolicy){
					case SKIPP_FAULTY_FIELDS:
						LOGGER.log(Level.WARNING, String.format("Value nr. %d of line nr. %d is ignored because it's invalid", elementNr, line.getLineNumber()));
						break;
					case ACCEPT_ONLY_WELL_FORMATED:
						LOGGER.log(Level.WARNING, String.format("Value nr. %d of line nr. %d is invalid. The hole line is skipped.", elementNr, line.getLineNumber()));
						return null;
				}
			}
		}

		if(!fields.isEmpty() && mappingPolicy.equals(LineMappingPolicy.ACCEPT_ONLY_WELL_FORMATED)){
			LOGGER.log(Level.WARNING, "Too much values in line nr. %d. The hole line is skipped.", line.getLineNumber());
			return null;
		}

		return container;
	}

	private TDataContainer createContainer(IPropertiesSet propertiesSet){
		try {
			return (TDataContainer)propertiesSet.getTypeOwner().getConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(String.format("Can not create instance of %s", propertiesSet.getTypeOwner().getSimpleName()), e);
		}
	}

	private String propertyToString(IPropertyDescriptor propertyDescriptor, TDataContainer container) {
		IFieldMapper fieldMapper = mappers.get(propertyDescriptor.getType());

		if(fieldMapper != null){
			return fieldMapper.mapPropertyToString(propertyDescriptor.getGetter().Get(container));
		}

		throw new UnsupportedOperationException();
	}

	private void stringToProperty(IPropertyDescriptor propertyDescriptor, TDataContainer container, String value) throws MappingException {
		ISetter setter = propertyDescriptor
				.getSetter()
				.orElseThrow(() -> new UnsupportedOperationException(String.format("No setter for property %s",propertyDescriptor.getKey())));

		IFieldMapper fieldMapper = mappers.get(propertyDescriptor.getType());

		if(fieldMapper != null){
			setter.Set(container, fieldMapper.mapStringToProperty(value));
		}
		else{
			throw new MappingException(String.format("Can not convert String to property type %s", propertyDescriptor.getType().toString()));
		}
	}

	public enum LineMappingPolicy{
		SKIPP_FAULTY_FIELDS,
		ACCEPT_ONLY_WELL_FORMATED
	}

	private class IntegerMapper implements IFieldMapper<Integer>{
		@Override
		public String mapPropertyToString(Integer propertyValue) {
			return Integer.toString(propertyValue);
		}

		@Override
		public Integer mapStringToProperty(String value) throws MappingException {
			try{
				return Integer.parseInt(value);
			}
			catch(Exception ex){
				throw new MappingException(String.format("%s is not a valid integer", value));
			}

		}
	}

	private class StringMapper implements IFieldMapper<String>{

		@Override
		public String mapPropertyToString(String propertyValue) {
			return propertyValue;
		}

		@Override
		public String mapStringToProperty(String value) {
			return value;
		}
	}
}
