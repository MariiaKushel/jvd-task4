package test.javacourse.task4.entity;

import org.testng.annotations.Test;

import by.javacourse.task4.entity.Letter;
import by.javacourse.task4.entity.Punctuation;
import by.javacourse.task4.entity.TextComponentType;
import by.javacourse.task4.entity.TextComposite;

public class CompisiteTest {

	@Test
	public void testMethod() {

		Letter letterW = new Letter('w');
		Letter letterO = new Letter('o');
		Letter letterR = new Letter('r');
		Letter letterD = new Letter('d');

		Punctuation punctuation = new Punctuation('.');

		TextComposite word1 = new TextComposite(TextComponentType.WORD);
		word1.add(letterW);
		word1.add(letterO);
		word1.add(letterR);
		word1.add(letterD);
		
		TextComposite lexsem1 = new TextComposite(TextComponentType.LEXSEM);
		lexsem1.add(word1);

		TextComposite lexsem2 = new TextComposite(TextComponentType.LEXSEM);
		lexsem2.add(word1);
		lexsem2.add(punctuation);

		TextComposite sentense1 = new TextComposite(TextComponentType.SENTENCE);
		sentense1.add(lexsem1);
		sentense1.add(lexsem2);

		TextComposite sentense2 = new TextComposite(TextComponentType.SENTENCE);
		sentense2.add(lexsem1);
		sentense2.add(lexsem1);
		sentense2.add(lexsem2);

		TextComposite paragraph1 = new TextComposite(TextComponentType.PARAGRAPH);
		paragraph1.add(sentense1);
		paragraph1.add(sentense2);

		TextComposite paragraph2 = new TextComposite(TextComponentType.PARAGRAPH);
		paragraph2.add(sentense1);
		paragraph2.add(sentense1);
		paragraph2.add(sentense2);

		TextComposite text = new TextComposite(TextComponentType.TEXT);
		text.add(paragraph1);
		text.add(paragraph2);
		text.add(paragraph1);

		String viewForm = text.createViewForm();
		System.out.println(viewForm);

	}

}
