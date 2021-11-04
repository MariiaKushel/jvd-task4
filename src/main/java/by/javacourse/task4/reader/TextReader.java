package by.javacourse.task4.reader;

import by.javacourse.task4.exception.TextException;

public interface TextReader {

	String read(String pathToFile) throws TextException;

}
