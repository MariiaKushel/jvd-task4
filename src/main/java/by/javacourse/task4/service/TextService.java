package by.javacourse.task4.service;

import java.util.List;

import by.javacourse.task4.entity.TextComponent;
import by.javacourse.task4.entity.TextComposite;

public interface TextService {

	List<TextComponent> sortParagraph(TextComponent text);

	List<TextComponent> findSentences(TextComponent text);

	TextComposite removeSentences(TextComponent text, int numberOfWord);

	long countSameWords();

	long countVowel(TextComponent text);

	long countConsonant(TextComponent text);

}
