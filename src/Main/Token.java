package Main;
import Types.TokenType;

public class Token {
	private final String text;
	private final TokenType type;

	public Token(TokenType type, String text) {
		this.type = type;
		this.text = text;
	}

	public TokenType getType() {
		return type;
	}

	public String getText() { return text; }

	@Override
	public String toString() {
		return type + " [" + text + "]";
	}

	@Override
	public boolean equals(Object obj) {
		Token other = (Token)obj;
		return type.equals(other.type) && text.equals(other.text);
	}
}
