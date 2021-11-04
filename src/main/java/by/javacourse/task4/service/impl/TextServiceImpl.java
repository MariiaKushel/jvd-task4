package by.javacourse.task4.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.javacourse.task4.entity.Letter;
import by.javacourse.task4.entity.Punctuation;
import by.javacourse.task4.entity.TextComponent;
import by.javacourse.task4.entity.TextComposite;
import by.javacourse.task4.service.TextService;

public class TextServiceImpl implements TextService {

	static Logger logger = LogManager.getLogger();

	private static final String VOWEL_REGEX = "(?iu)[aeiouyаеёионыэюя]";
	private static final String CONSONANT_REGEX = "(?iu)[a-zа-я&&[^aeiouyаеёионыэюя]]";

	@Override
	public TextComponent sortParagraph(TextComponent text) {

		return null;
	}

	@Override
	public List<TextComponent> findSentences(TextComponent text) {

		return null;
	}

	@Override
	public TextComposite removeSentences(TextComponent text, int numberOfWord) {

		return null;
	}

	@Override
	public long countSameWords() {

		return 0;
	}

	@Override
	public long countVowel(TextComponent text) {
		long counter = 0L;

		TextComponent parent = text;
		List<TextComponent> children = parent.getChildren();

		for (TextComponent child : children) {
			if (child instanceof Letter) {
				Pattern pattern = Pattern.compile(VOWEL_REGEX);
				Matcher matcher = pattern.matcher(child.toString());
				if (matcher.matches()) {
					logger.info("vowel -> "+ matcher.group());
					counter++;
				}
			} else if (!(child instanceof Punctuation)) {
				parent = child;
				counter = counter + countVowel(parent);
			}
		}

		// Other variant without recursion
		//
		// long counter = 0L;
		// TextComponent component = text;
		// List<TextComponent> components = new ArrayList<TextComponent>();
		// components.add(component);
		//
		// while (components.size() > 0) {
		// 		component = components.get(0);
		// 		if (component instanceof Letter) {
		// 			Pattern pattern = Pattern.compile(VOWEL_REGEX);
		// 			Matcher matcher = pattern.matcher(component.toString());
		// 			if (matcher.matches()) {
		// 				counter++;
		// 			}
		// 		} else if (!(component instanceof Punctuation)) {
		// 			List<TextComponent> cildren = component.getChildren();
		// 			components.addAll(cildren);
		// 		}
		// 		components.remove(0);
		// }
		//

		return counter;
	}

	@Override
	public long countConsonant(TextComponent text) {
		long counter = 0L;

		TextComponent parent = text;
		List<TextComponent> children = parent.getChildren();

		for (TextComponent child : children) {
			if (child instanceof Letter) {
				Pattern pattern = Pattern.compile(CONSONANT_REGEX);
				Matcher matcher = pattern.matcher(child.toString());
				if (matcher.matches()) {
					logger.info("consonant -> "+ matcher.group());
					counter++;
				}
			} else if (!(child instanceof Punctuation)) {
				parent = child;
				counter = counter + countConsonant(parent);
			}
		}
		return counter;
	}

}
