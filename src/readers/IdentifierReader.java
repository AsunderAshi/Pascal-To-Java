package readers;

import java.util.HashMap;

import Main.Language;
import Main.Token;
import Types.*;

public class IdentifierReader implements TokenReader {
	private final static TokenType type = PrimaryTypes.IDENTIFIER;

	public Token readToken(String input, Language language) {
		int i = 0;
		int len = input.length();
		if (len > 0 && Character.isJavaIdentifierStart(input.charAt(i))) {
			i++;
			while (i < len && Character.isJavaIdentifierPart(input.charAt(i)))
				i++;
			HashMap<String, TokenType> specificKeywords = language.getSpecificKeywords();
            if (!(specificKeywords.keySet().contains(input.substring(0, i))))
            	return new Token(type, input.substring(0, i));
		}
		return null;
	}
}
