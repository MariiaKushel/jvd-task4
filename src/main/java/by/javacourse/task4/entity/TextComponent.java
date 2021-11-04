package by.javacourse.task4.entity;

import java.util.List;

public interface TextComponent {

	boolean add(TextComponent component);

	boolean remove(TextComponent component);
	
	List<TextComponent> getChildren();
	
	TextComponent getChildByIndex(int index);

	String toString(); // leading method
}
