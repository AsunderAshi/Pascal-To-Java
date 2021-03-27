package Main;

import java.util.ArrayList;

public interface LanguageTranslator {

    MultiToken translateOperatorIf(MultiToken token, Language current, Language necessary);
    MultiToken translateCycleFor(MultiToken token, Language current, Language necessary);
    MultiToken translateCycleWhile(MultiToken token, Language current, Language necessary);
    ArrayList<MultiToken> translateVariables(ArrayList<MultiToken> token, Language current, Language necessary);
    ArrayList<Token> initTranslate(ArrayList<Token> tokens, Language current, Language necessary);
}
