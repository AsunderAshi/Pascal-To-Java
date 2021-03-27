package readers;

import Main.Language;
import Main.Token;

public interface TokenReader {

	Token readToken(String input, Language language);

}
