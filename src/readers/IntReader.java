package readers;

import Main.Language;
import Main.Token;
import Types.PrimaryTypes;

public class IntReader implements TokenReader {

	public Token readToken(String input, Language language)
	{
		int i = 0;
		if(input.charAt(0) == '-')
			i++;
		while(i < input.length() && Character.isDigit(input.charAt(i)))
			i++;
		return new Token(PrimaryTypes.INT, input.substring(0, i));
	}
}
