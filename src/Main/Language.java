package Main;
import java.util.HashMap;

import Types.TokenType;

public interface Language {
    HashMap<String, TokenType> getOperators();
    HashMap<String, TokenType> getSeparators();
    HashMap<String, TokenType> getSpecificKeywords();
    char getStringType();
    char getCharType();
    String[] getComments();
    String getStringReg();
    String getCharReg();
    char[] getLineCommentType();
    char[] getBlockCommentStart();
    char[] getBlockCommentEnd();
    TokenType[] getIfStatement();
    TokenType[] getWhileStatement();
    TokenType[][] getVarStatement();
    TokenType[] getForStatement();
    LanguageTranslator getTranslatorRules();

}
