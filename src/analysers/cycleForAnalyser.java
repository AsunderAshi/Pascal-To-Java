package analysers;

import java.util.ArrayList;
import java.util.Objects;

import Main.Language;
import Main.MultiToken;
import Main.Token;
import Types.HigherTypes;

public class cycleForAnalyser implements TokensAnalyser {

    public MultiToken analyseTokens(ArrayList<Token> tokens, Language language)
    {
        MultiToken finalToken = new MultiToken(HigherTypes.CYCLE_FOR);
        ArrayList<Token> result = new ArrayList<>();

        if ((tokens.size() == 0) || !(Objects.equals(tokens.get(0).getType(), language.getForStatement()[0])))
            return null;
        int i = 0;

        while (!(Objects.equals(tokens.get(i).getType(), language.getForStatement()[1])))
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