package by.javacourse.task4.parser;

import by.javacourse.task4.entity.TextComponent;
import by.javacourse.task4.entity.TextComponentType;
import by.javacourse.task4.entity.TextComposite;

public class LexemeParser extends AbstractTextParser {

	private static final String LEXEME_SPLITTER_REGEX = "\\s";

	public LexemeParser() {
		this.nextParser = new WordAndPunctuationParser();
	}

	@Override
	public void parse(TextComponent component, String data) {
		String[] lexemes = data.split(LEXEME_SPLITTER_REGEX);

		for (String lexeme : lexemes) {
			TextComponent lexemeComponent = new TextComposite(TextComponentType.LEXSEM);
			component.add(lexemeComponent);
			nextParser.parse(lexemeComponent, lexeme);
		}

	}

}
