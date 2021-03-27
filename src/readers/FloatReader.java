package readers;

import Main.Language;
import Main.Token;
import Types.PrimaryTypes;

public class FloatReader implements TokenReader {

	public Token readToken(String input, Language language)
	{
		int i = 0;
		if(input.charAt(0) == '-')
			i++;
		if(input.charAt(i) == '.')
			return new Token(PrimaryTypes.FLOAT, "");
		boolean isFractional = false;
		while(i < input.length() && (Character.isDigit(input.charAt(i)) || (input.charAt(i) == '.' &&  !isFractional)))
		{
			if(input.charAt(i) == '.')
				isFractional = true;
			i++;
		}
		return new Token(PrimaryTypes.FLOAT, input.substring(0, i));
	}
}
