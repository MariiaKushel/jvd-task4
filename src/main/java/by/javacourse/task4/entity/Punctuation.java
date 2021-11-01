package by.javacourse.task4.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Punctuation implements TextComponent{

	static Logger logger = LogManager.getLogger();
	
	private char punctuation;
	
	public Punctuation (char punctuation) {
		this.punctuation = punctuation;
	}
	
	public String createViewForm() {
		return String.valueOf(punctuation);
	}

	public boolean add(TextComponent component) {
		logger.info("Not available to this component");
		return false;
	}

	public boolean remove(TextComponent component) {
		logger.info("Not available to this component");
		return false;
	}

	public String toString () {
		return new StringBuilder()
				.append("[punctuation=")
				.append(punctuation)
				.append("]")
				.toString();
	}
	
}
