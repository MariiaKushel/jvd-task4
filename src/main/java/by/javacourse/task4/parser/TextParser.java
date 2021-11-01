package by.javacourse.task4.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.javacourse.task4.entity.TextComponent;

//конкретно этот класс по факту не нужен
public class TextParser implements AbstractTextParser {

	static Logger logger = LogManager.getLogger();

	@Override
	public void parse(TextComponent component, String data) {
		ParagraphParser parser = new ParagraphParser();
		parser.parse(component, data);
	}
}
