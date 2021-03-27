package translators;

import java.util.HashMap;
import java.util.Objects;

import Main.Language;
import Main.Token;
import Types.*;

public class SeparatorTransformator implements TokenTransformator{
    private static TokenType type = null;

    public Token transformateToken(Token token, Language current, Language necessary) {
        type = current.getSeparators().get(token.getText());
        HashMap<String, TokenType> separators = necessary.getSeparators();
        for (String separator : separators.keySet())
        {
            if (Objects.equals(type, separators.get(separator)))
                return new Token(type, separator);
        }
        return null;
    }
}
