package test.javacourse.task4.reader;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import by.javacourse.task4.exception.TextException;
import by.javacourse.task4.reader.TextReader;
import by.javacourse.task4.reader.impl.TextReaderImpl;

public class TextReaderTest {
	
	private TextReader reader;
	
	@BeforeTest
	public void initialize () {
		reader = new TextReaderImpl();
	}
	
	@Test
	public void testReadPositive() throws TextException {
		String pathToFile = "src\\test\\resources\\testData\\textTest.txt";
		String actual = reader.read(pathToFile);
		String expected = "	It has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the “Динамо” (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum!\r\n"
				+ "	It is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English?\r\n"
				+ "	It is a established fact that a reader will be of a page when looking at its layout...\r\n"
				+ "	Bye бандерлоги.";
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void testReadEmpty() throws TextException {
		String pathToFile = "src\\test\\resources\\testData\\textTestEmpty.txt";
		String actual = reader.read(pathToFile);
		String expected = "";
		Assert.assertEquals(actual, expected);
	}
	
	@Test (expectedExceptions = TextException.class, expectedExceptionsMessageRegExp = ".*does not exsist.*")
	public void testReadException() throws TextException {
		String pathToFile = "src\\test\\resources\\testData\\textTestEmptyN.txt";
		reader.read(pathToFile);
	}

	@AfterTest
	public void clean () {
		reader = null;
	}
}
