package ch.buhls.billmanager.persistance.csvHandling.reader;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author sdb
 */
public class CsvReader {
	//private static final String ENCODING = "UTF-8"; // Linux
	private static final String ENCODING = "Cp1252"; // ANSI encoding for windows --> http://stackoverflow.com/questions/18556104/read-and-write-text-in-ansi-format

	public static final String SEPERATOR = ";";
	public static final String COMMENT = "//";

	private LineParser parser;

	public CsvReader() {
		parser = new LineParser();
	}


	public List<Line> read(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(in, ENCODING));

		List<Line> lines = new ArrayList<>();

		for (int cntLines = 1; true; cntLines++) {
			String origLine = reader.readLine();

			if (origLine == null) {
				// end of stream reached
				Logger.getLogger(CsvReader.class.getName()).log(Level.INFO, "{0} lines have been read", cntLines);
				break;
			}

			Line line = parser.parse(origLine, cntLines);
			lines.add(line);
		}

		return lines;
	}

	public void write(OutputStream out, List<Line> lines) throws IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, ENCODING));

		for (Line line : lines) {
			writeLine(writer, line.getElements());
		}

		writer.flush();
		writer.close();

		Logger.getLogger(CsvReader.class.getName()).log(Level.INFO, "{0} lines have been written", lines.size());
	}


	private void writeLine(Writer w, List<String> values) throws IOException // Source: https://www.mkyong.com/java/how-to-export-data-to-csv-file-java/
	{
		boolean first = true;

		StringBuilder sb = new StringBuilder();
		for (String value : values) {
			if (!first) {
				sb.append(SEPERATOR);
			}

			sb.append(value);

			first = false;
		}
		sb.append("\n");
		w.append(sb.toString());
	}

}
