package ch.buhls.billmanager.persistance.csvHandling;

import ch.buhls.billmanager.persistance.csvHandling.reader.CsvReader;
import ch.buhls.billmanager.persistance.csvHandling.reader.Line;
import ch.buhls.billmanager.utils.IPropertiesSet;
import ch.buhls.billmanager.utils.IPropertyDescriptor;
import ch.buhls.billmanager.utils.ISetter;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class CsvMapper<TDataContainer> {

	public List<TDataContainer> map(List<Line> lines, IPropertiesSet propertiesSet){
		return lines.stream()
				.map(line -> lineToContainer(propertiesSet, line))
				.collect(Collectors.toList());
	}

	public List<Line> map(IPropertiesSet propertiesSet, Collection<TDataContainer> containers){
		return containers.stream()
				.map(dataContainer -> containerToLine(propertiesSet, dataContainer))
				.collect(Collectors.toList());
	}

	// private methods

	private Line containerToLine(IPropertiesSet propertiesSet, TDataContainer container) {
		Line line = new Line();

		propertiesSet.getProperties().stream()
				.forEach(propertyDescriptor -> line.getElements().add(propertyToString(propertyDescriptor, container)));

		return line;
	}

	private TDataContainer lineToContainer(IPropertiesSet propertiesSet, Line line) {
		try {
			final TDataContainer container = (TDataContainer)propertiesSet.getTypeOwner().getConstructor().newInstance();
			final Queue<String> fields = new ArrayDeque<>(line.getElements());

			propertiesSet.getProperties().stream()
					.forEach(propertyDescriptor -> stringToProperty(propertyDescriptor, container, fields.poll()));

			return container;
		} catch (Exception e) {
			throw new RuntimeException(String.format("Can not create instance of %s", propertiesSet.getTypeOwner().getSimpleName()), e);
		}
	}

	private String propertyToString(IPropertyDescriptor propertyDescriptor, TDataContainer container) {
		if (propertyDescriptor.getType().equals(Integer.class)) {
			return Integer.toString((int) propertyDescriptor.getGetter().Get(container));
		} else if (propertyDescriptor.getType().equals(String.class)) {
			return (String) propertyDescriptor.getGetter().Get(container);
		}

		throw new UnsupportedOperationException();
	}

	private void stringToProperty(IPropertyDescriptor propertyDescriptor, TDataContainer container, String value){
		ISetter setter = propertyDescriptor
				.getSetter()
				.orElseThrow(() -> new UnsupportedOperationException(String.format("No setter for property %s",propertyDescriptor.getKey())));

		if (propertyDescriptor.getType().equals(Integer.class)) {
			setter.Set(container, Integer.parseInt(value));
		} else if (propertyDescriptor.getType().equals(String.class)) {
			setter.Set(container, value);
		}
		else{
			throw new UnsupportedOperationException(String.format("Can not convert String to property type %s", propertyDescriptor.getType().toString()));
		}
	}
}
