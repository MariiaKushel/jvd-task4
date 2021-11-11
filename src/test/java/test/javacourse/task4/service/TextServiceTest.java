package test.javacourse.task4.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import by.javacourse.task4.entity.TextComponent;
import by.javacourse.task4.entity.TextComponentType;
import by.javacourse.task4.entity.TextComposite;
import by.javacourse.task4.exception.TextException;
import by.javacourse.task4.parser.AbstractTextParser;
import by.javacourse.task4.parser.ParagraphParser;
import by.javacourse.task4.service.TextService;
import by.javacourse.task4.service.impl.TextServiceImpl;

public class TextServiceTest {

	private TextService service;
	private TextComponent text;
	private AbstractTextParser parser;

	@BeforeTest
	public void initialize() {
		service = new TextServiceImpl();
		text = new TextComposite(TextComponentType.TEXT);
		parser = new ParagraphParser();
	}
	
	@Test
	public void testSortParagraphsByNumberOfSentences() throws TextException {

		String textAsString = "    Mama. Mama.\n    Papa.\n    I am. I am. I am.\n";
		parser.parse(text, textAsString);

		List<TextComponent> actual = service.sortParagraphsByNumberOfSentences(text);
		List<TextComponent> expected = new ArrayList<TextComponent>();
		expected.add(text.getChildByIndex(1));
		expected.add(text.getChildByIndex(0));
		expected.add(text.getChildByIndex(2));

		Assert.assertEquals(actual, expected);
	}

	@Test(expectedExceptions = TextException.class)
	public void testSortParagraphsByNumberOfSentencesException() throws TextException {

		String textAsString = "    Mama. Mama.\n    Papa.\n    I am. I am. I am.\n";
		parser.parse(text, textAsString);
		TextComponent paragraph = text.getChildByIndex(0);

		service.sortParagraphsByNumberOfSentences(paragraph);
	}

	@Test
	public void testFindSentencesWithLongestWorld() throws TextException {

		String textAsString = "    Mama washed the frame.\n    Papa washed windows.\n	I washed windows too.";
		parser.parse(text, textAsString);

		List<TextComponent> actual = service.findSentencesWithLongestWorld(text);
		List<TextComponent> expected = new ArrayList<TextComponent>();
		expected.add(text.getChildByIndex(1).getChildByIndex(0));
		expected.add(text.getChildByIndex(2).getChildByIndex(0));

		Assert.assertEquals(actual, expected);
	}

	@Test(expectedExceptions = TextException.class, expectedExceptionsMessageRegExp = ".*Unable type.*")
	public void testFindSentencesWithLongestWorldExceptionType() throws TextException {

		String textAsString = "    Mama washed the frame.\n    Papa washed windows.\n	I washed windows too.";
		parser.parse(text, textAsString);
		TextComponent paragraph = text.getChildByIndex(0);

		service.findSentencesWithLongestWorld(paragraph);
	}

	@Test(expectedExceptions = TextException.class, expectedExceptionsMessageRegExp = ".*Unable lenght.*")
	public void testFindSentencesWithLongestWorldExceptionLenght() throws TextException {

		String textAsString = "";
		parser.parse(text, textAsString);

		service.findSentencesWithLongestWorld(text);
	}

	@Test
	public void testRemoveSentencesWithWordsLessThan() throws TextException {

	}

	@Test
	public void testFindAndCountSameWords() throws TextException {
		String textAsString = "    Mama washed the frame.\n    Papa washed windows.\n	I Washed windows too.";
		parser.parse(text, textAsString);

		Map<String, Integer> actual = service.findAndCountSameWords(text);
		Map<String, Integer> expected = new HashMap<String, Integer>();
		expected.put("washed", 3);
		expected.put("windows", 2);

		Assert.assertEquals(actual, expected);
	}

	@Test(expectedExceptions = TextException.class)
	public void testFindAndCountSameWordsException() throws TextException {
		String textAsString = "    Mama washed the frame.\n    Papa washed windows.\n	I Washed windows too.";
		parser.parse(text, textAsString);
		TextComponent paragraph = text.getChildByIndex(0);
		
		service.findAndCountSameWords(paragraph);
	}

	@Test
	public void testCountConsonant() throws TextException {

		String textAsString = "    Mama washed the frame.\n    Papa washed windowsФ.";
		parser.parse(text, textAsString);

		long actual = service.countConsonant(text);
		long expected = 23L;

		Assert.assertEquals(actual, expected);
	}
	
	@Test(expectedExceptions = TextException.class)
	public void testCountConsonantException() throws TextException {

		String textAsString = "    Mama washed the frame.\n    Papa washed windowsФ.";
		parser.parse(text, textAsString);
		TextComponent paragraph = text.getChildByIndex(0);

		service.countConsonant(paragraph);
	}
	
	@Test
	public void testCountVowel() throws TextException {

		String textAsString = "    Mama washed the frame.\\n    Papa washed windowsЯ.";
		parser.parse(text, textAsString);

		long actual = service.countVowel(text);
		long expected = 14L;

		Assert.assertEquals(actual, expected);
	}

	@Test(expectedExceptions = TextException.class)
	public void testCountVowelException() throws TextException {

		String textAsString = "    Mama washed the frame.\\n    Papa washed windowsЯ.";
		parser.parse(text, textAsString);
		TextComponent paragraph = text.getChildByIndex(0);

		service.countVowel(paragraph);
	}
	
	@Test
	public void testCountVowel2() {

		String textAsString = "    Mama washed the frame.\\n    Papa washed windowsЯ.";
		parser.parse(text, textAsString);

		long actual = service.countVowel2(text);
		long expected = 14L;

		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void testCountVowel3() {

		String textAsString = "    Mama washed the frame.\\n    Papa washed windowsЯ.";
		parser.parse(text, textAsString);

		long actual = service.countVowel3(text);
		long expected = 14L;

		Assert.assertEquals(actual, expected);
	}

	

	@AfterTest
	public void clean() {
		service = null;
		text = null;
		parser = null;
	}

}
