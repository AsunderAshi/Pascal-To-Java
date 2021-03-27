package readers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Main.Language;
import Main.Token;
import Types.PrimaryTypes;

public class StringReader implements TokenReader {
	private String stringSign;

	private void setStringSigns(Language language){this.stringSign = language.getStringReg();}

	public Token readToken(String input, Language language)
	{
		this.setStringSigns(language);
		Pattern string = Pattern.compile(stringSign);
		Matcher match = string.matcher(input);
		if(match.find())
			return new Token(PrimaryTypes.STRING, match.group());
		return new Token(PrimaryTypes.STRING, "");
	}
}
