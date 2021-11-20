package by.javacourse.task4.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextComposite implements TextComponent {

	static Logger logger = LogManager.getLogger();

	private TextComponentType type;
	private List<TextComponent> components = new ArrayList<TextComponent>();

	public TextComposite(TextComponentType type) {
		this.type = type;
	}

	@Override
	public boolean add(TextComponent component) {
		return components.add(component);
	}

	@Override
	public boolean remove(TextComponent component) {
		return components.remove(component);
	}

	@Override
	public List<TextComponent> getChildren() {
		return new ArrayList<TextComponent>(components);
	}

	@Override
	public TextComponent getChildByIndex(int index) {
		return components.get(index);
	}

	@Override
	public String toString() {

		StringBuilder demonstrativeForm = new StringBuilder();

		demonstrativeForm.append(type.getPrefix());

		components.forEach(c -> demonstrativeForm.append(c.toString()));

		demonstrativeForm.append(type.getPostfix());

		return demonstrativeForm.toString();
	}

	public TextComponentType getType() {
		return type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((components == null) ? 0 : components.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		TextComposite other = (TextComposite) obj;
		if (components == null) {
			if (other.components != null)
				return false;
		} else if (!components.equals(other.components))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
