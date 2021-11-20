package by.javacourse.task4.parser;

import java.util.List;
import java.util.stream.Stream;

import by.javacourse.task4.entity.TextComponent;
import by.javacourse.task4.entity.TextComponentType;
import by.javacourse.task4.entity.TextComposite;

public class ParagraphParser extends AbstractTextParser {

	// private static final String PARAGRAPH_SPLITTER_REGEX =
	// "(?m)(?=^(\\t|\\s{4}))";
	private static final String PARAGRAPH_SPLITTER_REGEX = "(^|\\n)(\\t|\\s{4})";

	public ParagraphParser() {
		this.nextParser = new SentenceParser();
	}

	@Override
	public void parse(TextComponent component, String data) {
		List<String> paragraphs = Stream.of(data.split(PARAGRAPH_SPLITTER_REGEX))
				.filter(p -> !p.isEmpty())
				.toList();

		for (String paragraph : paragraphs) {
			TextComponent paragraphComponent = new TextComposite(TextComponentType.PARAGRAPH);
			component.add(paragraphComponent);
			nextParser.parse(paragraphComponent, paragraph);
		}
	}

}
