package readers;

import java.util.*;

import Main.Language;
import Main.Token;
import Types.*;

public class OperatorReader implements TokenReader {

    private static HashMap<String, TokenType> operators;
    private static TokenType type = null;

    public Token readToken(String input, Language language) {
        operators = language.getOperators();
        List<String> listSeparators = new ArrayList<>(operators.keySet());
        int maxLength = 0;
        boolean contain = false;
        int index = 0;
        for (String separator : listSeparators)
        {
            if (separator.length() > maxLength) maxLength = separator.length();
        }
        for (int i = 1; i < maxLength + 1; i++)
        {
            if (input.length() >= i && listSeparators.contains(input.substring(0, i))) {
                contain = true;
                index = i;
            }
        }
        if (!contain) return null;
        type = operators.get(input.substring(0, index));
        return new Token(type, input.substring(0, index));
    }
}
