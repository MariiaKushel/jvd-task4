package by.javacourse.task4.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import by.javacourse.task4.entity.Punctuation;
import by.javacourse.task4.entity.TextComponent;
import by.javacourse.task4.entity.TextComponentType;
import by.javacourse.task4.entity.TextComposite;

public class WordAndPunctuationParser extends AbstractTextParser {

	private static final String WORD_REGEX = "[\\wа-яА-ЯёЁ]+";
	private static final String WORD_OR_PUNCTUATION_REGEX = "([\\wа-яА-ЯёЁ]+)|(\\p{Punct})";

	public WordAndPunctuationParser() {
		this.nextParser = new LetterParser();
	}

	@Override
	public void parse(TextComponent component, String data) {

		Pattern pattern = Pattern.compile(WORD_OR_PUNCTUATION_REGEX);
		Matcher matcher = pattern.matcher(data);

		while (matcher.find()) {
			String group = matcher.group();

			Pattern wordPattren = Pattern.compile(WORD_REGEX);
			Matcher wordMatcher = wordPattren.matcher(group);

			if (wordMatcher.matches()) {
				TextComponent wordComponent = new TextComposite(TextComponentType.WORD);
				component.add(wordComponent);
				nextParser.parse(wordComponent, group);
			} else {
				TextComponent punctuationComponent = new Punctuation(group.charAt(0));
				component.add(punctuationComponent);
			}
		}
	}

}
