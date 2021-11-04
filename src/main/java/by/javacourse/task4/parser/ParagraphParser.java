package by.javacourse.task4.parser;

import by.javacourse.task4.entity.TextComponent;
import by.javacourse.task4.entity.TextComponentType;
import by.javacourse.task4.entity.TextComposite;

public class ParagraphParser extends AbstractTextParser {

	private static final String PARAGRAPH_SPLITTER_REGEX = "(?m)(?=^((\\t)|(\\s{4})))";

	public ParagraphParser() {
		this.nextParser = new SentenceParser();
	}

	@Override
	public void parse(TextComponent component, String data) {
		String[] paragraphs = data.split(PARAGRAPH_SPLITTER_REGEX);

		for (String paragraph : paragraphs) {
			TextComponent paragraphComponent = new TextComposite(TextComponentType.PARAGRAPH);
			component.add(paragraphComponent);
			nextParser.parse(paragraphComponent, paragraph);
		}
	}

}
