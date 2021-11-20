package by.javacourse.task4.service;

import java.util.List;
import java.util.Map;

import by.javacourse.task4.entity.TextComponent;
import by.javacourse.task4.exception.TextException;

public interface TextService {

	List<TextComponent> sortParagraphsByNumberOfSentences(TextComponent text) throws TextException;

	List<TextComponent> findSentencesWithLongestWorld(TextComponent text) throws TextException;

	void removeSentencesWithWordsLessThan(TextComponent text, int min) throws TextException;

	Map<String, Integer> findAndCountSameWords(TextComponent text) throws TextException;

	long countConsonant(TextComponent text) throws TextException;

	long countVowel(TextComponent text) throws TextException;
	
	//Other version of method using recursion
	long countVowel2(TextComponent text) throws TextException; 
	
	//Other version of method without stream and recursion
	long countVowel3(TextComponent text) throws TextException;

}
