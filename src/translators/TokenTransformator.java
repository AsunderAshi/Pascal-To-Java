package translators;

import Main.Language;
import Main.Token;

public interface TokenTransformator {
    Token transformateToken(Token token, Language current, Language necessary);
}
