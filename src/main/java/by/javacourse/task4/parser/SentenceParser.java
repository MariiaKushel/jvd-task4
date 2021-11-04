package by.javacourse.task4.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.javacourse.task4.entity.TextComponent;
import by.javacourse.task4.entity.TextComponentType;
import by.javacourse.task4.entity.TextComposite;

public class SentenceParser extends AbstractTextParser {

	private static final String SENTENCE_REGEX = "(\\p{Upper}|[А-ЯЁ]).+?(\\.|\\!|\\?|\\u2026)(\\s|$)"; // \\u2026 =
																										// '...'

	public SentenceParser() {
		this.nextParser = new LexemeParser();
	}

	@Override
	public void parse(TextComponent component, String data) {

		Pattern pattern = Pattern.compile(SENTENCE_REGEX);
		Matcher matcher = pattern.matcher(data);

		while (matcher.find()) {
			String sentence = matcher.group();
			TextComponent sentenceComponent = new TextComposite(TextComponentType.SENTENCE);
			component.add(sentenceComponent);
			nextParser.parse(sentenceComponent, sentence);
		}

	}

}
