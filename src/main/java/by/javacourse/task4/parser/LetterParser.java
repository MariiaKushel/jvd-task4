package by.javacourse.task4.parser;

import by.javacourse.task4.entity.Letter;
import by.javacourse.task4.entity.TextComponent;

public class LetterParser extends AbstractTextParser {

	@Override
	public void parse(TextComponent component, String data) {
		
		char[] letters = data.toCharArray();

		for (char letter : letters) {
			component.add(new Letter(letter));
		}
	}

}
