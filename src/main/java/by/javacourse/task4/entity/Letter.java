package by.javacourse.task4.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Letter implements TextComponent{
	static Logger logger = LogManager.getLogger();

	private char letter;
	
	public Letter (char letter) {
		this.letter = letter;
	}
	
	public String createViewForm() {
		return String.valueOf(letter);
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
				.append("[letter=")
				.append(letter)
				.append("]")
				.toString();
	}

}
