package test.javacourse.task4.parser;

import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.javacourse.task4.entity.TextComponent;
import by.javacourse.task4.entity.TextComponentType;
import by.javacourse.task4.entity.TextComposite;
import by.javacourse.task4.exception.TextException;
import by.javacourse.task4.parser.AbstractTextParser;
import by.javacourse.task4.parser.ParagraphParser;
import by.javacourse.task4.reader.TextReader;

public class ParserTestMockito {

	private TextReader readerMock;
	private TextReader readerSpy;
	private AbstractTextParser parser;
	private TextComponent textComposite;

	@BeforeMethod
	public void initialize() {
		readerMock = Mockito.mock(TextReader.class);
		readerSpy = Mockito.spy(TextReader.class);
		parser = new ParagraphParser();
		textComposite = new TextComposite(TextComponentType.TEXT);
	}

	@Test
	public void parseMockWhen() throws TextException {

		String text = "\tIt has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the \"Динамо\" (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum! \n"
				+ "\tIt is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English? \n"
				+ "\tIt is a established fact that a reader will be of a page when looking at its layout\u2026 \n"
				+ "\tBye бандерлоги. \n";

		Mockito.when(readerMock.read(Mockito.anyString())).thenReturn(text);
		parser.parse(textComposite, readerMock.read(Mockito.anyString()));

		String actual = textComposite.toString();
		String exspected = text;

		Assert.assertEquals(actual, exspected);
	}


	@Test
	public void parseMockDoReturn() throws TextException {

		String text = "\tIt has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the \"Динамо\" (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum! \n"
				+ "\tIt is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English? \n"
				+ "\tIt is a established fact that a reader will be of a page when looking at its layout\u2026 \n"
				+ "\tBye бандерлоги. \n";

		
		Mockito.doReturn(text).when(readerMock).read(Mockito.anyString());
		parser.parse(textComposite, readerMock.read(Mockito.anyString()));

		String actual = textComposite.toString();
		String exspected = text;

		Assert.assertEquals(actual, exspected);
	}
	
	@Test
	public void parseSpy() throws TextException {

		String text = "\tIt has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the \"Динамо\" (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum! \n"
				+ "\tIt is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English? \n"
				+ "\tIt is a established fact that a reader will be of a page when looking at its layout\u2026 \n"
				+ "\tBye бандерлоги. \n";

		Mockito.when(readerSpy.read(Mockito.anyString())).thenReturn(text);
		parser.parse(textComposite, readerSpy.read(Mockito.anyString()));

		String actual = textComposite.toString();
		String exspected = text;

		Assert.assertEquals(actual, exspected);
	}
	
	@AfterMethod
	public void clean () {
		readerMock = null;
		readerSpy = null;
		parser = null;
		textComposite = null;
	}
	
}
