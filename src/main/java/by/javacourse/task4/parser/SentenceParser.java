package by.javacourse.task4.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.javacourse.task4.entity.TextComponent;
import by.javacourse.task4.entity.TextComponentType;
import by.javacourse.task4.entity.TextComposite;

public class SentenceParser implements AbstractTextParser {

	static Logger logger = LogManager.getLogger();

	private static final String SENTENCE_REGEX = "\\p{Upper}.+(\\u002E|\\u0021|\\u003F|\\u2026)";

	@Override
	public void parse(TextComponent component, String data) {

		Pattern pattern = Pattern.compile(SENTENCE_REGEX);
		Matcher matcher = pattern.matcher(data);

		while (matcher.find()) {
			String sentence = matcher.group();
			TextComponent sentenceComponent = new TextComposite(TextComponentType.SENTENCE);
			component.add(sentenceComponent);
			LexemeParser parser = new LexemeParser();
			parser.parse(sentenceComponent, sentence);
		}

	}

}
