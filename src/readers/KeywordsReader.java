package readers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Main.Language;
import Main.Token;
import Types.*;

public class KeywordsReader implements TokenReader {

    private static HashMap<String, TokenType> keywords;
    private static TokenType type = null;

    public Token readToken(String program, Language language) {
        keywords = language.getSpecificKeywords();
        List<String> listKeywords = new ArrayList<>(keywords.keySet());
        int maxLength = 0;
        boolean contain = false;
        int index = 0;
        for (String separator : listKeywords)
        {
            if (separator.length() > maxLength) maxLength = separator.length();
        }
        for (int i = 1; i < maxLength + 1; i++)
        {
            if (program.length() >= i && listKeywords.contains(program.substring(0, i))) {
                contain = true;
                index = i;
            }
        }
        if (!contain) return null;
        type = keywords.get(program.substring(0, index));
        return new Token(type, program.substring(0, index));
    }
}
