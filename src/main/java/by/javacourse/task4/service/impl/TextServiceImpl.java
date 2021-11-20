package by.javacourse.task4.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.javacourse.task4.entity.Letter;
import by.javacourse.task4.entity.Punctuation;
import by.javacourse.task4.entity.TextComponent;
import by.javacourse.task4.entity.TextComponentType;
import by.javacourse.task4.entity.TextComposite;
import by.javacourse.task4.exception.TextException;
import by.javacourse.task4.service.TextService;

public class TextServiceImpl implements TextService {

	static Logger logger = LogManager.getLogger();

	private static final String VOWEL_REGEX = "(?iu)[aeiouyаеёионыэюя]";
	private static final String CONSONANT_REGEX = "(?iu)[a-zа-я&&[^aeiouyаеёионыэюя]]";

	@Override
	public List<TextComponent> sortParagraphsByNumberOfSentences(TextComponent text) throws TextException {

		// suitable type of component is TEXT
		if (!validateParameter(text)) {
			throw new TextException("Unable type of TextComponent.");
		}

		List<TextComponent> paragraphs = text.getChildren();

		paragraphs.sort(new Comparator<TextComponent>() {
			public int compare(TextComponent one, TextComponent other) {
				Integer sizeOne = one.getChildren().size();
				Integer sizeOther = other.getChildren().size();
				return sizeOne.compareTo(sizeOther);
			}
		});
				
		return paragraphs;
	}

	@Override
	public List<TextComponent> findSentencesWithLongestWorld(TextComponent text) throws TextException {

		// suitable type of component is TEXT
		if (!validateParameter(text)) {
			throw new TextException("Unable type of TextComponent.");
		}

		// get list of all sentences
		List<TextComponent> allSentences = text.getChildren().stream()
				.flatMap(p -> p.getChildren().stream())
				.collect(Collectors.toList());

		// find the max length of word
		OptionalInt maxLenghtOpt = allSentences.stream()
				.flatMap(s -> s.getChildren().stream())
				.flatMap(l -> l.getChildren().stream())
				.filter(w -> !(w instanceof Punctuation))
				.mapToInt(w -> w.getChildren().size())
				.max();

		maxLenghtOpt.orElseThrow(() -> new TextException("Unable lenght of word."));
		int maxLenght = maxLenghtOpt.getAsInt();

		// find sentences with words, which have max length
		List<TextComponent> suitableSentences = allSentences.stream()
				.filter(s -> s.getChildren().stream()
						.anyMatch(l -> l.getChildren().stream()
								.filter(w -> !(w instanceof Punctuation))
								.anyMatch(w -> w.getChildren().size() == maxLenght)))
				.peek(e -> logger.info("sentence to collect >> " + e))
				.collect(Collectors.toList());
		
		return suitableSentences;
	}

	@Override
	public void removeSentencesWithWordsLessThan(TextComponent text, int min) throws TextException{

		// suitable type of component is TEXT
		if (!validateParameter(text)) {
			throw new TextException("Unable type of TextComponent.");
		}
		
		for (TextComponent paragraph : text.getChildren()) {
			for (TextComponent sentence : paragraph.getChildren()) {
				int numberOfWord = 0;
				for (TextComponent lexeme : sentence.getChildren()) {
					if (!(lexeme instanceof Punctuation)) {
						numberOfWord++;
					}
				}
				if (numberOfWord < min) {
					paragraph.remove(sentence);
				}
			}
		}
	}

	public Map<String, Integer> findAndCountSameWords(TextComponent text) throws TextException{

		// suitable type of component is TEXT
		if (!validateParameter(text)) {
			throw new TextException("Unable type of TextComponent.");
		}

		Map<String, Integer> sameWords = text.getChildren().stream()
				.flatMap(p -> p.getChildren().stream())
				.flatMap(s -> s.getChildren().stream())
				.flatMap(lx -> lx.getChildren().stream())
				.filter(w -> !(w instanceof Punctuation))
				.map(w -> w.toString().toLowerCase())
				.collect(Collectors.toMap(str -> str, i -> 1, (i1, i2) -> i1 + i2));

		sameWords.values().removeIf(i -> i == 1);

		return sameWords;
	}
	
	@Override
	public long countConsonant(TextComponent text) throws TextException{
		
		// suitable type of component is TEXT
		if (!validateParameter(text)) {
			throw new TextException("Unable type of TextComponent.");
		}

		Pattern pattern = Pattern.compile(CONSONANT_REGEX);

		long counter = text.getChildren().stream()
				.flatMap(p -> p.getChildren().stream())
				.flatMap(s -> s.getChildren().stream())
				.flatMap(lx -> lx.getChildren().stream())
				.filter(w -> !(w instanceof Punctuation))
				.flatMap(w -> w.getChildren().stream())
				.map(l -> l.toString())
				.filter(l -> pattern.matcher(l).matches())
				.count();

		return counter;
	}
	
	@Override
	public long countVowel(TextComponent text) throws TextException{

		// suitable type of component is TEXT
		
		if (!validateParameter(text)) {
			throw new TextException("Unable type of TextComponent.");
		}

		Pattern pattern = Pattern.compile(VOWEL_REGEX);
				
		long counter = text.getChildren().stream()
				.flatMap(p -> p.getChildren().stream())
				.flatMap(s -> s.getChildren().stream())
				.flatMap(lx -> lx.getChildren().stream())
				.filter(w -> !(w instanceof Punctuation))
				.flatMap(w -> w.getChildren().stream())
				.map(l -> l.toString())
				.filter(l -> pattern.matcher(l).matches())
				.count();
		
		return counter;
	}
	
	public long countVowel2(TextComponent text) throws TextException{

		// work with any type of text composite
		if (!(text instanceof TextComposite)) {
			throw new TextException("Unable type of TextComponent.");
		}
		
		Pattern pattern = Pattern.compile(VOWEL_REGEX);
		long counter = 0L;

		TextComponent parent = text;
		List<TextComponent> children = parent.getChildren();

		for (TextComponent child : children) {
			if (child instanceof Letter) {
				Matcher matcher = pattern.matcher(child.toString());
				if (matcher.matches()) {
					counter++;
				}
			} else if (!(child instanceof Punctuation)) {
				parent = child;
				counter = counter + countVowel2(parent);
			}
		}

		return counter;
	}

	public long countVowel3(TextComponent text) throws TextException{

		// work with any type of text composite
		if (!(text instanceof TextComposite)) {
			throw new TextException("Unable type of TextComponent.");
		}
		
		Pattern pattern = Pattern.compile(VOWEL_REGEX);
		long counter = 0L;

		List<TextComponent> components = new ArrayList<TextComponent>();
		components.add(text);
		TextComponent component;

		while (components.size() > 0) {
			component = components.get(0);
			if (component instanceof Letter) {
				Matcher matcher = pattern.matcher(component.toString());
				if (matcher.matches()) {
					counter++;
				}
			} else if (!(component instanceof Punctuation)) {
				List<TextComponent> children = component.getChildren();
				components.addAll(children);
			}
			components.remove(0);
		}

		return counter;
	}

	private boolean validateParameter(TextComponent component) {

		if (!(component instanceof TextComposite)) {
			return false;
		}

		TextComposite composite = (TextComposite) component;
		if (composite.getType() != TextComponentType.TEXT) {
			return false;
		}

		return true;
	}

}
