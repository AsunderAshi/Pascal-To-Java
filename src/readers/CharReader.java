package readers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Main.Language;
import Main.Token;
import Types.*;

public class CharReader implements TokenReader {
	private String charSign;

	private void setCharSign(Language language){this.charSign = language.getCharReg();}

	public Token readToken(String input, Language language)
	{
		this.setCharSign(language);
		Pattern string = Pattern.compile(charSign);
		Matcher match = string.matcher(input);
		if(match.find())
			return new Token(PrimaryTypes.CHAR, match.group());
		return new Token(PrimaryTypes.CHAR, "");
	}
}
