package Main;


import java.util.ArrayList;

import analysers.*;


public class Analyser {
    private ArrayList<Token> tokens;
    private ArrayList<Token> result = new ArrayList<>();
    private Language language;
    private TokensAnalyser[] analysers = new TokensAnalyser[] {
            new operatorIfAnalyser(),
            new cycleWhileAnalyser(),
            new cycleForAnalyser(),
            new variableAnalyser()
    };

    public Analyser(ArrayList<Token> tokens, Language language)
    {
        this.language = language;
        this.tokens = tokens;
    }

    public void analyse()
    {
        boolean added = false;
        while (!allTokensHandled())
        {
            for (TokensAnalyser analyser : analysers)
            {
                added = false;
                MultiToken currentToken = analyser.analyseTokens(tokens, language);
                if (currentToken != null)
                {
                    for (int i = 0; i < currentToken.getTokens().size(); i++)
                    {
                        tokens.remove(0);
                    }
                    result.add(currentToken);
                    added = true;
                    break;
                }
            }
            if (!added)
            {
                result.add(tokens.get(0));
                tokens.remove(0);
            }
        }
    }

    public ArrayList<Token> getResult()
    {
        return result;
    }

    public boolean allTokensHandled()
    {
        return tokens.size() == 0;
    }
}