package translators;

import Main.Language;
import Main.Token;
import Types.*;

public class CharTransformator implements TokenTransformator {
    private final static TokenType type = PrimaryTypes.CHAR;

    public Token transformateToken(Token token, Language current, Language necessary) {
        return new Token(type, token.getText().replace(current.getCharType(), necessary.getCharType()));
    }
}
