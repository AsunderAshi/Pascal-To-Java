package translators;

import Main.Language;
import Main.MultiToken;
import Main.Token;
import Types.*;

public class HigherTypesTransformator implements TokenTransformator {

    public Token transformateToken(Token token, Language current, Language necessary) {
        TokenType type = token.getType();
        switch ((HigherTypes)type)
        {
            case CYCLE_FOR:
                return current.getTranslatorRules().translateCycleFor((MultiToken)token, current, necessary);
            case CYCLE_WHILE:
                return current.getTranslatorRules().translateCycleWhile((MultiToken)token, current, necessary);
            case OPERATOR_IF:
                return  current.getTranslatorRules().translateOperatorIf((MultiToken)token, current, necessary);
		default:
			return null;
        }
    }
}
