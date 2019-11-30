package ch.buhls.billmanager.persistance.csvHandling;

import ch.buhls.billmanager.persistance.csvHandling.reader.CsvReader;
import ch.buhls.billmanager.persistance.csvHandling.reader.Line;
import ch.buhls.billmanager.utils.IPropertiesSet;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author sdb
 */
public class CsvManager<TDataContainer> {

	private CsvReader reader;
	private CsvMapper<TDataContainer> mapper;

	public CsvManager() {
		reader = new CsvReader();
		mapper = new CsvMapper<>(CsvMapper.LineMappingPolicy.SKIPP_FAULTY_FIELDS);
	}

	public CsvManager(CsvMapper.LineMappingPolicy lineMappingPolicy) {
		reader = new CsvReader();
		mapper = new CsvMapper<>(lineMappingPolicy);
	}

	// read methods

    public List<TDataContainer> read(InputStream in, IPropertiesSet propertiesSet) throws IOException {
        List<Line> lines = reader.read(in);
        return mapper.map(lines, propertiesSet);
    }

	public List<TDataContainer> read(File file, IPropertiesSet propertiesSet) throws IOException {
		Logger.getLogger(CsvReader.class.getName()).log(Level.INFO, "Read CSV file: {0}", file.getAbsolutePath());
		return read(new FileInputStream(file), propertiesSet);
	}


	// write methods

	public void write(OutputStream out, IPropertiesSet propertiesSet, Collection<TDataContainer> containers) throws IOException {
		List<Line> lines = mapper.map(propertiesSet, containers);
		reader.write(out, lines);
	}

	public void write(File file, IPropertiesSet propertiesSet, Collection<TDataContainer> containers) throws IOException {
		Logger.getLogger(CsvReader.class.getName()).log(Level.INFO, "Write CSV file: {0}", file.getAbsolutePath());
		write(new FileOutputStream(file), propertiesSet, containers);
	}
}
