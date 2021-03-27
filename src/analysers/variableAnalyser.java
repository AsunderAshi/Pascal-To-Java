package analysers;

import java.util.ArrayList;
import java.util.Objects;

import Main.Language;
import Main.MultiToken;
import Main.Token;
import Types.*;

public class variableAnalyser implements TokensAnalyser {

    public MultiToken analyseTokens(ArrayList<Token> tokens, Language language)
    {
        TokenType[][] types = language.getVarStatement();
        MultiToken finalToken = new MultiToken(HigherTypes.VARIABLE);
        ArrayList<Token> result = new ArrayList<>();
        boolean correctType = false;

        for (int i = 0; i < types[0].length; i++)
        {
            if ((Objects.equals(types[0][i], tokens.get(0).getType())))
                correctType = true;
        }
        if (!correctType)
            return null;

        int i = 0;
        while (!(Objects.equals(tokens.get(i).getType(), types[1][0])))
        {
            result.add(tokens.get(i));
            i++;
        }
        result.add(tokens.get(i));

        for (Token token : result)
            finalToken.addToken(token);
        return finalToken;
    }
}
