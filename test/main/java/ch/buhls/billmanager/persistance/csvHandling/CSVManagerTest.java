package ch.buhls.billmanager.persistance.csvHandling;

import ch.buhls.billmanager.utils.PropertiesSetBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class CSVManagerTest {

	private static class TestData{
		private String testStringA;
		private String testStringB;

		private int testIntA;
		private int testIntB;

		public TestData() {
		}

		public String getTestStringA() {
			return testStringA;
		}

		public void setTestStringA(String testStringA) {
			this.testStringA = testStringA;
		}

		public String getTestStringB() {
			return testStringB;
		}

		public void setTestStringB(String testStringB) {
			this.testStringB = testStringB;
		}

		public int getTestIntA() {
			return testIntA;
		}

		public void setTestIntA(int testIntA) {
			this.testIntA = testIntA;
		}

		public int getTestIntB() {
			return testIntB;
		}

		public void setTestIntB(int testIntB) {
			this.testIntB = testIntB;
		}
	}

	private static PropertiesSetBuilder<TestData> propertiesSetBuilder = new PropertiesSetBuilder<TestData>(TestData.class)
			.addProperty("stringA", String.class, TestData::getTestStringA, TestData::setTestStringA)
			.addProperty("intA", Integer.class, TestData::getTestIntA, TestData::setTestIntA)
			.addProperty("stringB", String.class, TestData::getTestStringB, TestData::setTestStringB)
			.addProperty("intB", Integer.class, TestData::getTestIntB, TestData::setTestIntB);

	@Test
	public void testReadWrite() throws IOException {
		String inputString =
				"I am string A;1001;I am String B;1002\n" +
				"Again: I am string A;2001;Again: I am String B;2002\n" +
				"For the last time: I am string A;3001;For the last time: I am String B;3002\n";

		CsvManager<TestData> manager = new CsvManager<>();
		List<TestData> datas = manager.read(new ByteArrayInputStream(inputString.getBytes()), propertiesSetBuilder.getPropertiesSet());

		Assert.assertEquals(3, datas.size());

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		manager.write(out, propertiesSetBuilder.getPropertiesSet(), datas, false);

		String outputString = out.toString();

		Assert.assertEquals(inputString, outputString);
	}

	@Test
	public void readNotValidValue() throws IOException {
		String inputString = "I am string A;1001x;I am String B;1002"; // "1001x" is not a valid integer

		CsvManager<TestData> manager = new CsvManager<>(CsvMapper.LineMappingPolicy.SKIPP_FAULTY_FIELDS);
		List<TestData> datas = manager.read(new ByteArrayInputStream(inputString.getBytes()), propertiesSetBuilder.getPropertiesSet());

		Assert.assertEquals(1, datas.size());

		manager = new CsvManager<>(CsvMapper.LineMappingPolicy.ACCEPT_ONLY_WELL_FORMATED);
		datas = manager.read(new ByteArrayInputStream(inputString.getBytes()), propertiesSetBuilder.getPropertiesSet());

		Assert.assertEquals(0, datas.size());
	}

	@Test
	public void readNotEnoughValues() throws IOException {
		String inputString = "I am string A;1001;I am String B"; // last value is missing

		CsvManager<TestData> manager = new CsvManager<>();
		List<TestData> datas = manager.read(new ByteArrayInputStream(inputString.getBytes()), propertiesSetBuilder.getPropertiesSet());

		Assert.assertEquals(1, datas.size());

		manager = new CsvManager<>(CsvMapper.LineMappingPolicy.ACCEPT_ONLY_WELL_FORMATED);
		datas = manager.read(new ByteArrayInputStream(inputString.getBytes()), propertiesSetBuilder.getPropertiesSet());

		Assert.assertEquals(0, datas.size());
	}

	@Test
	public void readTooMuchValues() throws IOException {
		String inputString = "I am string A;1001;I am String B;1001;Additional value"; // last value is missing

		CsvManager<TestData> manager = new CsvManager<>();
		List<TestData> datas = manager.read(new ByteArrayInputStream(inputString.getBytes()), propertiesSetBuilder.getPropertiesSet());

		Assert.assertEquals(1, datas.size());

		manager = new CsvManager<>(CsvMapper.LineMappingPolicy.ACCEPT_ONLY_WELL_FORMATED);
		datas = manager.read(new ByteArrayInputStream(inputString.getBytes()), propertiesSetBuilder.getPropertiesSet());

		Assert.assertEquals(0, datas.size());
	}

}
