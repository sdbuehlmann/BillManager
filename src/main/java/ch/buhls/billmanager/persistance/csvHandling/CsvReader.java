package ch.buhls.billmanager.persistance.csvHandling;

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
				Logger.getLogger(CsvReader.class.getName()).log(Level.INFO, "{0} lines have been read", cntLines);
				break;
			}

			Line line = parser.parse(origLine, cntLines);
			lines.add(line);
		}

		return lines;
	}

	public void write(OutputStream out, List<Line> lines) throws FileNotFoundException, IOException {
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, ENCODING));

		for (Line line : lines) {
			writeLine(writer, line.getElements());
		}

		writer.flush();
		writer.close();

		Logger.getLogger(CsvReader.class.getName()).log(Level.INFO, "{0} lines have been written", lines.size());
	}

	public Line splitLine(String data, int lineNumber) {
		Line line = new Line(lineNumber);
		String comment = null;

		String[] lineCommentSplited = data.split(COMMENT);

		if (lineCommentSplited.length > 1) {
			// comment found
			comment = "";

			// everything behind the comment sign is a comment
			for (int cntCommentElements = 1; cntCommentElements < lineCommentSplited.length; cntCommentElements++) {
				comment = comment + lineCommentSplited[cntCommentElements];
			}
		}

		String[] lineSeperatorSplited = lineCommentSplited[0].split(SEPERATOR);

		for (String dataElment : lineSeperatorSplited) {
			line.getElements().add(dataElment);
		}

		if (comment != null) {
			line.setComment(comment);
		}

		return line;
	}

	public void removeNonEssentialSpaces(Line line) {
		for (int cnt = 0; cnt < line.getElements().size(); cnt++) {
			String element = line.getElements().get(cnt);

			int nrLeadingSpaces = countLeadingSpaces(element);
			int nrSpasesAtTheEnd = countSpacesAtTheEnd(element);

			CharSequence subSequence = element.subSequence(nrLeadingSpaces, element.length() - nrSpasesAtTheEnd);

			line.getElements().set(cnt, subSequence.toString());
		}
	}

	public int countLeadingSpaces(String data) {
		int counter = 0;
		for (char dataChar : data.toCharArray()) {
			if (dataChar == ' ') {
				counter++;
			} else {
				break;
			}
		}

		return counter;
	}

	public int countSpacesAtTheEnd(String data) {
		int counter = 0;
		char[] dataAsArray = data.toCharArray();

		for (int posInArray = dataAsArray.length - 1; posInArray >= 0; posInArray--) {
			if (dataAsArray[posInArray] == ' ') {
				counter++;
			} else {
				break;
			}
		}

		return counter;
	}

	public void writeLine(Writer w, List<String> values) throws IOException // Source: https://www.mkyong.com/java/how-to-export-data-to-csv-file-java/
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
