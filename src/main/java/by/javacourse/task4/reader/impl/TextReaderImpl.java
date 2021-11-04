package by.javacourse.task4.reader.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.javacourse.task4.exception.TextException;
import by.javacourse.task4.reader.TextReader;

public class TextReaderImpl implements TextReader {

	static Logger logger = LogManager.getLogger();

	@Override
	public String read(String pathToFile) throws TextException {

		Path path = Paths.get(pathToFile);
		if (!Files.exists(path)) {
			logger.error("File " + pathToFile + " does not exsist or is not available.");
			throw new TextException("File " + pathToFile + " does not exsist or is not available.");
		}

		logger.info("Start reading");

		String text = "";

		try {
			text = Files.readString(path);
		} catch (IOException e) {
			logger.error("IO Exception during working with file " + pathToFile);
			throw new TextException("IO Exception during working with file " + pathToFile, e);
		}

		logger.info("Finish reading");

		return text;
	}

}
