package by.javacourse.task4.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.javacourse.task4.entity.Letter;
import by.javacourse.task4.entity.TextComponent;

public class LetterParser implements AbstractTextParser {

	static Logger logger = LogManager.getLogger();

	@Override
	public void parse(TextComponent component, String data) {
		char[] letters = data.toCharArray();

		for (char letter : letters) {
			component.add(new Letter(letter));
		}
	}

}
