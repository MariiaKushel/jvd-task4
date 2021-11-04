package test.javacourse.task4.reader;

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
		String text = reader.read(pathToFile);
		System.out.println(text);
	}
	
	@Test
	public void testReadEmpty() throws TextException {
		String pathToFile = "src\\test\\resources\\testData\\textTestEmpty.txt";
		String text = reader.read(pathToFile);
		System.out.println(text);
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
