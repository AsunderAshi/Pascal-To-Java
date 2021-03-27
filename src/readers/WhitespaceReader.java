package readers;

import Main.Language;
import Main.Token;
import Types.*;

public class WhitespaceReader implements TokenReader {

	private final static TokenType type = PrimaryTypes.WHITESPACE;

	public Token readToken(String input, Language language) {
		int i = 0;
		int len = input.length();
		if (len > 0 && Character.isWhitespace(input.charAt(i))) {
			while (i < len && Character.isWhitespace(input.charAt(i)))
				i++;
			return new Token(type, input.substring(0, i));
		}
		return null;
	}
}
