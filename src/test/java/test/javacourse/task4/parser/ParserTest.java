package test.javacourse.task4.parser;

import org.testng.Assert;
import org.testng.annotations.Test;

import by.javacourse.task4.entity.TextComponent;
import by.javacourse.task4.entity.TextComponentType;
import by.javacourse.task4.entity.TextComposite;
import by.javacourse.task4.parser.AbstractTextParser;
import by.javacourse.task4.parser.ParagraphParser;

public class ParserTest {
	
	@Test
	public void testParse () {
		
		String text ="\tIt has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the \"Динамо\" (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum!\n"
				+ "    It is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English?\n"
				+ "\tIt is a established fact that a reader will be of a page when looking at its layout\u2026\n"
				+ "\tBye бандерлоги."; 
		
		TextComponent component = new TextComposite(TextComponentType.TEXT);
		AbstractTextParser parser = new ParagraphParser();
		parser.parse(component, text);
		
		String actual = component.toString();
		String expected = "\tIt has survived - not only (five) centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the \"Динамо\" (Рига) with the release of Letraset sheets.toString() containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker Faclon9 including versions of Lorem Ipsum! \n" 
				+ "\tIt is a long a!=b established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Ipsum is that it has a more-or-less normal distribution ob.toString(a?b:c), as opposed to using (Content here), content here's, making it look like readable English? \n"
				+ "\tIt is a established fact that a reader will be of a page when looking at its layout\u2026 \n"
				+ "\tBye бандерлоги. \n";
	
	
		Assert.assertEquals(actual, expected);
	}

}
