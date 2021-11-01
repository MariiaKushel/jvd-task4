package by.javacourse.task4.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.javacourse.task4.entity.Punctuation;
import by.javacourse.task4.entity.TextComponent;
import by.javacourse.task4.entity.TextComponentType;
import by.javacourse.task4.entity.TextComposite;

public class WordAndPunctuationParser implements AbstractTextParser {

	static Logger logger = LogManager.getLogger();

	private static final String WORD_REGEX = "[a-zA-Zа-яА-ЯёЁ]+";

	private static final String WORD_OR_PUNCTUATION_REGEX = "([a-zA-Zа-яА-ЯёЁ]+)|(\\p{Punct})";

	@Override
	public void parse(TextComponent component, String data) {

		Pattern pattern = Pattern.compile(WORD_OR_PUNCTUATION_REGEX);
		Matcher matcher = pattern.matcher(data);

		while (matcher.find()) {
			String part = matcher.group();

			Pattern wordPattren = Pattern.compile(WORD_REGEX);
			Matcher wordMatcher = wordPattren.matcher(part);

			if (wordMatcher.matches()) {
				TextComponent wordComponent = new TextComposite(TextComponentType.WORD);
				component.add(wordComponent);
				LetterParser parser = new LetterParser();
				parser.parse(wordComponent, part);
			} else {
				TextComponent punctuationComponent = new Punctuation(part.charAt(0));
				component.add(punctuationComponent);
			}
		}
	}

}
