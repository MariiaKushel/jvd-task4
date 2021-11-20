package by.javacourse.task4.entity;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Punctuation implements TextComponent {

	static Logger logger = LogManager.getLogger();

	private char punct;

	public Punctuation(char punctuation) {
		this.punct = punctuation;
	}

	public boolean add(TextComponent component) {
		logger.error("Not available operation to this component");
		throw new UnsupportedOperationException("Not available operation to this component");
	}

	public boolean remove(TextComponent component) {
		logger.error("Not available operation to this component");
		throw new UnsupportedOperationException("Not available operation to this component");
	}

	@Override
	public List<TextComponent> getChildren() {
		logger.error("Not available operation to this component");
		throw new UnsupportedOperationException("Not available operation to this component");
	}

	@Override
	public TextComponent getChildByIndex(int index) {
		logger.error("Not available operation to this component");
		throw new UnsupportedOperationException("Not available operation to this component");
	}

	@Override
	public String toString() {
		return String.valueOf(punct);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + punct;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Punctuation other = (Punctuation) obj;
		if (punct != other.punct)
			return false;
		return true;
	}

}
