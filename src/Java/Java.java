package Java;

import java.util.HashMap;

import Main.Language;
import Main.LanguageTranslator;
import Types.*;

public class Java implements Language {

    public Java()
    {
        setKeywords();
        setSeparators();
        setOperators();
    }

    private final static HashMap<String, TokenType> operators = new HashMap<>();

    private final static HashMap<String, TokenType> separators = new HashMap<>();

    private final static HashMap<String, TokenType> specificKeywords = new HashMap<>();

    public void setKeywords()
    {
        specificKeywords.put("public static void main()\r\n", KeywordsTypes.KEYWORD_FUNCTION);
        specificKeywords.put("for", KeywordsTypes.KEYWORD_CYCLE_FOR);
        specificKeywords.put("while", KeywordsTypes.KEYWORD_CYCLE_WHILE);
        specificKeywords.put("int", KeywordsTypes.KEYWORD_INTEGER);
        specificKeywords.put("float", KeywordsTypes.KEYWORD_FLOAT);
        specificKeywords.put("double", KeywordsTypes.KEYWORD_DOUBLE);
        specificKeywords.put("if", KeywordsTypes.KEYWORD_IF_START);
        specificKeywords.put("int", KeywordsTypes.KEYWORD_INTEGER);
        specificKeywords.put("boolean", KeywordsTypes.KEYWORD_BOOL);
        specificKeywords.put("char", KeywordsTypes.KEYWORD_CHAR);
    }

    public void setOperators()
    {
        operators.put("=", OperatorTypes.OPERATOR_ASSIGNMENT);
        operators.put(">", OperatorTypes.OPERATOR_GREATER);
        operators.put(">=", OperatorTypes.OPERATOR_GREATER_OR_EQUAL);
        operators.put("<", OperatorTypes.OPERATOR_LOWER);
        operators.put("<=", OperatorTypes.OPERATOR_LOWEST_OR_EQUAL);
        operators.put("--", OperatorTypes.OPERATOR_DECREMENT);
        operators.put("++", OperatorTypes.OPERATOR_INCREMENT);
        operators.put("+", OperatorTypes.OPERATOR_SUM);
        operators.put("-", OperatorTypes.OPERATOR_MINUS);
        operators.put("/", OperatorTypes.OPERATOR_DIVISION);
        operators.put("*", OperatorTypes.OPERATOR_MULTIPLICATION);
        operators.put("==", OperatorTypes.OPERATOR_EQUAL);
        operators.put("!=", OperatorTypes.OPERATOR_NOT_EQUAL);
        operators.put("%", OperatorTypes.OPERATOR_MODULE);
        operators.put("/", OperatorTypes.OPERATOR_INT_DIVISION);
        operators.put("&&", OperatorTypes.OPERATOR_AND);
        operators.put("||", OperatorTypes.OPERATOR_OR);
        operators.put("true", OperatorTypes.OPERATOR_TRUE);
    }

    public void setSeparators()
    {
        separators.put("{", SeparatorTypes.BLOCK_START);
        separators.put("}", SeparatorTypes.PROGRAM_END);
        separators.put("}", SeparatorTypes.BLOCK_END);
        separators.put("(", SeparatorTypes.OPEN_BRACKET);
        separators.put(")", SeparatorTypes.CLOSE_BRACKET);
        separators.put("[", SeparatorTypes.ARRAY_START);
        separators.put("]", SeparatorTypes.ARRAY_END);
        separators.put(",", SeparatorTypes.SEPARATOR_IDENTIFIERS);
        separators.put(".", SeparatorTypes.SEPARATOR_SUB_ELEMENTS);
        separators.put(":", SeparatorTypes.ITERATOR_FOR);
        separators.put(";", SeparatorTypes.END_STATEMENT);
        separators.put("@", SeparatorTypes.OVERRIDE_FUNCTION);
    }

    private final static char string = '\"';

    private final static char character = '\'';

    private final static char[] lineComment = {'/', '/'};

    private String[] comments = {"^//.*\r\n", "/^.+/$"};

    private String stringReg = "^\".*\"";

    private String charReg = "\'^.{1}\'$";

    public String getCharReg()
    {
        return this.charReg;
    }

    public String getStringReg()
    {
        return this.stringReg;
    }

    public String[] getComments()
    {
        return this.comments;
    }

    private final char[] blockCommentStart = {'/', '*'};

    private final char[] blockCommentEnd = {'*', '/'};

    public HashMap<String, TokenType> getSpecificKeywords() { return specificKeywords; }

    public HashMap<String, TokenType> getSeparators()
    {
        return separators;
    }

    public HashMap<String, TokenType> getOperators() { return operators; }

    public char getStringType() { return string; }

    public char getCharType() { return character; }

    public char[] getLineCommentType() { return lineComment; }

    public char[] getBlockCommentStart() { return blockCommentStart; }

    public char[] getBlockCommentEnd() { return blockCommentEnd; }

    public TokenType[] getIfStatement() { return new TokenType[] {KeywordsTypes.KEYWORD_IF_START,
                                                                  SeparatorTypes.CLOSE_BRACKET};}

    public TokenType[] getWhileStatement() { return new TokenType[] {KeywordsTypes.KEYWORD_CYCLE_WHILE,
                                                                     SeparatorTypes.CLOSE_BRACKET};}

    public TokenType[][] getVarStatement() { return new TokenType[][] {
            new TokenType[]{KeywordsTypes.KEYWORD_BOOL,
                            KeywordsTypes.KEYWORD_FLOAT,
                            KeywordsTypes.KEYWORD_DOUBLE,
                            KeywordsTypes.KEYWORD_CHAR,
                            KeywordsTypes.KEYWORD_INTEGER},
            new TokenType[]{SeparatorTypes.END_STATEMENT}};}

    public TokenType[] getForStatement() { return new TokenType[] {KeywordsTypes.KEYWORD_CYCLE_FOR,
                                                                   SeparatorTypes.CLOSE_BRACKET};}

    public LanguageTranslator getTranslatorRules() { return new JavaTranslateRules();}
}
