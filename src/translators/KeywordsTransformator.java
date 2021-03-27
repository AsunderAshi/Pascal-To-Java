package translators;

import java.util.HashMap;
import java.util.Objects;

import Main.Language;
import Main.Token;
import Types.*;

public class KeywordsTransformator implements TokenTransformator {

    private static TokenType type = null;

    public Token transformateToken(Token token, Language current, Language necessary)
    {
        type = current.getSpecificKeywords().get(token.getText());
        HashMap<String, TokenType> keywords = necessary.getSpecificKeywords();
        for (String keyword : keywords.keySet())
        {
            if (Objects.equals(type, keywords.get(keyword)))
                return new Token(type, keyword);
        }
        return null;
    }
}
