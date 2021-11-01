package by.javacourse.task4.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.javacourse.task4.entity.TextComponent;
import by.javacourse.task4.entity.TextComponentType;
import by.javacourse.task4.entity.TextComposite;

public class ParagraphParser implements AbstractTextParser {

	static Logger logger = LogManager.getLogger();

	private static final String PARAGRAPH_SPLITTER_REGEX = "(\u0009|(\u0020{4}))";

	@Override
	public void parse(TextComponent component, String data) {
		String[] paragraphs = data.split(PARAGRAPH_SPLITTER_REGEX);

		for (String paragraph : paragraphs) {
			TextComponent paragraphComponent = new TextComposite(TextComponentType.PARAGRAPH);
			component.add(paragraphComponent);
			SentenceParser parser = new SentenceParser();
			parser.parse(paragraphComponent, paragraph);
		}
	}

}
