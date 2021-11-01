package by.javacourse.task4.entity;

public enum TextComponentType {

	TEXT("", ""), 
	PARAGRAPH("\u0009", "\n"), 
	SENTENCE("", ""),
	LEXSEM("", "\u0020"), 
	WORD("", "");

	private final String prefix;
	private final String postfix;

	private TextComponentType(String prefix, String postfix) {
		this.prefix = prefix;
		this.postfix = postfix;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getPostfix() {
		return postfix;
	}

}
