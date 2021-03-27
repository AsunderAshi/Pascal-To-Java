package translators;

import Main.Language;
import Main.Token;
import Types.*;

public class StringTransformator implements TokenTransformator {

    private final static TokenType type = PrimaryTypes.STRING;

    public Token transformateToken(Token token, Language current, Language necessary) {
        return new Token(type, token.getText().replace(current.getStringType(), necessary.getStringType()));
    }
}
