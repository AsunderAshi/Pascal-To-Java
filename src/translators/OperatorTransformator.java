package translators;

import java.util.HashMap;
import java.util.Objects;

import Main.Language;
import Main.Token;
import Types.*;

public class OperatorTransformator implements TokenTransformator {

    private static TokenType type = null;

    public Token transformateToken(Token token, Language current, Language necessary) {
        type = current.getOperators().get(token.getText());
        HashMap<String, TokenType> operators = necessary.getOperators();
        for (String operator : operators.keySet())
        {
            if (Objects.equals(type, operators.get(operator)))
                return new Token(type, operator);
        }
        return null;
    }
}
