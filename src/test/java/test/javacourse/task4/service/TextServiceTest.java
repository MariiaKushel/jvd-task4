package test.javacourse.task4.service;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import by.javacourse.task4.entity.TextComponent;
import by.javacourse.task4.entity.TextComponentType;
import by.javacourse.task4.entity.TextComposite;
import by.javacourse.task4.parser.AbstractTextParser;
import by.javacourse.task4.parser.ParagraphParser;
import by.javacourse.task4.service.TextService;
import by.javacourse.task4.service.impl.TextServiceImpl;

public class TextServiceTest {

	private TextService service;

	@BeforeTest
	public void initialize() {
		service = new TextServiceImpl();
	}

	@Test
	public void testsortParagraph() {

		String textAsString = "    Mama. Mama.\n    Papa.\n    I am. I am. I am.\n";
		
		TextComponent text = new TextComposite(TextComponentType.TEXT);
		AbstractTextParser parser = new ParagraphParser();		
		parser.parse(text, textAsString);
		
		List <TextComponent> actual = service.sortParagraph(text);
		List<TextComponent> expected = new ArrayList<TextComponent> ();
		expected.add(text.getChildByIndex(1));
		expected.add(text.getChildByIndex(0));
		expected.add(text.getChildByIndex(2));
		
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void testCountVowel() {

		String textAsString = "    Mama mila ramu.\n    Papa mil stolЯ.\n";
		
		TextComponent text = new TextComposite(TextComponentType.TEXT);
		AbstractTextParser parser = new ParagraphParser();		
		parser.parse(text, textAsString);
		
		long actual = service.countVowel(text);
		long expected = 11L;
		
		Assert.assertEquals(actual, expected);
	}
	
	@Test
	public void testCountConsonant() {

		String textAsString = "    Mama mila ramu.\n    Papa mil stolФ.\n";
		
		TextComponent text = new TextComposite(TextComponentType.TEXT);
		AbstractTextParser parser = new ParagraphParser();		
		parser.parse(text, textAsString);
		
		long actual = service.countConsonant(text);
		long expected = 14L;
		
		Assert.assertEquals(actual, expected);
	}
	
	

	@AfterTest
	public void clean() {
		service = null;
	}

}
