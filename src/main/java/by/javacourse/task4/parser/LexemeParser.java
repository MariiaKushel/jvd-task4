package by.javacourse.task4.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.javacourse.task4.entity.TextComponent;
import by.javacourse.task4.entity.TextComponentType;
import by.javacourse.task4.entity.TextComposite;

public class LexemeParser implements AbstractTextParser {

	static Logger logger = LogManager.getLogger();

	private static final String LEXEME_SPLITTER_REGEX = "\u0020";

	@Override
	public void parse(TextComponent component, String data) {
		String[] lexemes = data.split(LEXEME_SPLITTER_REGEX);

		for (String lexeme : lexemes) {
			TextComponent lexemeComponent = new TextComposite(TextComponentType.LEXSEM);
			component.add(lexemeComponent);
			WordAndPunctuationParser parser = new WordAndPunctuationParser();
			parser.parse(lexemeComponent, lexeme);
		}

	}

}
