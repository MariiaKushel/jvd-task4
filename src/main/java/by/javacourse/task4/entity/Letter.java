package by.javacourse.task4.entity;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Letter implements TextComponent {

	static Logger logger = LogManager.getLogger();

	private char letter;

	public Letter(char letter) {
		this.letter = letter;
	}

	@Override
	public boolean add(TextComponent component) {
		logger.info("Not available operation to this component");
		throw new UnsupportedOperationException("Not available operation to this component");
	}

	@Override
	public boolean remove(TextComponent component) {
		logger.info("Not available operation to this component");
		throw new UnsupportedOperationException("Not available operation to this component");
	}

	@Override
	public List<TextComponent> getChildren() {
		logger.info("Not available operation to this component");
		throw new UnsupportedOperationException("Not available operation to this component");
	}

	@Override
	public TextComponent getChildByIndex(int index) {
		logger.info("Not available operation to this component");
		throw new UnsupportedOperationException("Not available operation to this component");
	}

	@Override
	public String toString() {
		return String.valueOf(letter);
	}

}
