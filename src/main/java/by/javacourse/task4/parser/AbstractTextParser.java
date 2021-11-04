package by.javacourse.task4.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.javacourse.task4.entity.TextComponent;

public abstract class AbstractTextParser {

	static Logger logger = LogManager.getLogger();

	protected AbstractTextParser nextParser = DefaultTextParser.getDefaultParser();

	public AbstractTextParser() {

	}

	public abstract void parse(TextComponent component, String data);

	private static class DefaultTextParser extends AbstractTextParser {

		private static DefaultTextParser defaultParser = new DefaultTextParser();

		private DefaultTextParser() {

		}

		public static DefaultTextParser getDefaultParser() {
			return defaultParser;
		}

		@Override
		public void parse(TextComponent component, String data) {
			logger.info("End of parsers chain.");
		}

	}

}
