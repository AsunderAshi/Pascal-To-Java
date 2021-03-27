package analysers;

import java.util.ArrayList;

import Main.Language;
import Main.MultiToken;
import Main.Token;

public interface TokensAnalyser {

    MultiToken analyseTokens(ArrayList<Token> tokens, Language language);
}
