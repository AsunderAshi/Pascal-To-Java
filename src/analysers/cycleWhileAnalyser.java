package analysers;

import java.util.ArrayList;
import java.util.Objects;

import Main.Language;
import Main.MultiToken;
import Main.Token;
import Types.HigherTypes;

public class cycleWhileAnalyser implements TokensAnalyser {

    public MultiToken analyseTokens(ArrayList<Token> tokens, Language language)
    {
        MultiToken finalToken = new MultiToken(HigherTypes.CYCLE_WHILE);
        ArrayList<Token> result = new ArrayList<>();

        if ((tokens.size() == 0) || (!(Objects.equals(tokens.get(0).getType(), language.getWhileStatement()[0]))))
            return null;
        int i = 0;
        while (!(Objects.equals(tokens.get(i).getType(), language.getWhileStatement()[1])))
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
