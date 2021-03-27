package Pascal;

import java.util.HashMap;

import Main.Language;
import Main.LanguageTranslator;
import Types.*;

public class Pascal implements Language {

    public Pascal()
    {
        setSpecificKeywords();
        setSeparators();
        setOperators();
    }


    private final static HashMap<String, TokenType> operators = new HashMap<>();

    private final static HashMap<String, TokenType> separators = new HashMap<>();

    private final static HashMap<String, TokenType> specificKeywords = new HashMap<>();

    public void setSpecificKeywords()
    {
        specificKeywords.put("", KeywordsTypes.KEYWORD_FUNCTION);
        specificKeywords.put("for", KeywordsTypes.KEYWORD_CYCLE_FOR);
        specificKeywords.put("while", KeywordsTypes.KEYWORD_CYCLE_WHILE);
        specificKeywords.put("integer", KeywordsTypes.KEYWORD_INTEGER);
        specificKeywords.put("real", KeywordsTypes.KEYWORD_FLOAT);
        specificKeywords.put("boolean", KeywordsTypes.KEYWORD_BOOL);
        specificKeywords.put("double", KeywordsTypes.KEYWORD_DOUBLE);
        specificKeywords.put("if", KeywordsTypes.KEYWORD_IF_START);
        specificKeywords.put("then", KeywordsTypes.KEYWORD_IF_END);
        specificKeywords.put("var", KeywordsTypes.KEYWORD_TYPE);
        specificKeywords.put("do", KeywordsTypes.KEYWORD_WHILE_END);
    }

    public void setOperators()
    {
        operators.put(":=", OperatorTypes.OPERATOR_ASSIGNMENT);
        operators.put(">", OperatorTypes.OPERATOR_GREATER);
        operators.put("<", OperatorTypes.OPERATOR_LOWER);
        operators.put("downto", OperatorTypes.OPERATOR_DECREMENT);
        operators.put("to", OperatorTypes.OPERATOR_INCREMENT);
        operators.put("+", OperatorTypes.OPERATOR_SUM);
        operators.put("-", OperatorTypes.OPERATOR_MINUS);
        operators.put("/", OperatorTypes.OPERATOR_DIVISION);
        operators.put("*", OperatorTypes.OPERATOR_MULTIPLICATION);
        operators.put("=", OperatorTypes.OPERATOR_EQUAL);
        operators.put("<>", OperatorTypes.OPERATOR_NOT_EQUAL);
        operators.put("mod", OperatorTypes.OPERATOR_MODULE);
        operators.put("div", OperatorTypes.OPERATOR_INT_DIVISION);
        operators.put("and", OperatorTypes.OPERATOR_AND);
        operators.put("or", OperatorTypes.OPERATOR_OR);
        operators.put("true", OperatorTypes.OPERATOR_TRUE);
        operators.put(">=", OperatorTypes.OPERATOR_GREATER_OR_EQUAL);
        operators.put("<=", OperatorTypes.OPERATOR_LOWEST_OR_EQUAL);
    }

    public void setSeparators()
    {
        separators.put("begin", SeparatorTypes.BLOCK_START);
        separators.put("end.", SeparatorTypes.PROGRAM_END);
        separators.put("end;", SeparatorTypes.BLOCK_END);
        separators.put("(", SeparatorTypes.OPEN_BRACKET);
        separators.put(")", SeparatorTypes.CLOSE_BRACKET);
        separators.put("[", SeparatorTypes.ARRAY_START);
        separators.put("]", SeparatorTypes.ARRAY_END);
        separators.put(",", SeparatorTypes.SEPARATOR_IDENTIFIERS);
        separators.put(".", SeparatorTypes.SEPARATOR_SUB_ELEMENTS);
        separators.put(":", SeparatorTypes.ITERATOR_FOR);
        separators.put(";", SeparatorTypes.END_STATEMENT);
    }

    private String[] comments = {"^//.*\r\n", "^\\{.*\\}"};
    private String stringReg = "^\".*\"";
    private String charReg = "\'^.{1}\'$";

    public String getCharReg()
    {
        return this.charReg;
    }

    public String[] getComments()
    {
        return this.comments;
    }
    public String getStringReg()
    {
        return this.stringReg;
    }

    private final static char string = '\'';

    private final static char[] lineComment = {'/', '/'};

    private final static char[] blockCommentStart = {'{'};

    private final static char[] blockCommentEnd = {'}'};

    public HashMap<String, TokenType> getSpecificKeywords() { return specificKeywords; }

    public HashMap<String, TokenType> getSeparators()
    {
        return separators;
    }

    public HashMap<String, TokenType> getOperators()
    {
        return operators;
    }

    public char getStringType() { return string; }

    public char getCharType() { return string; }

    public char[] getLineCommentType() { return lineComment; }

    public char[] getBlockCommentStart() { return blockCommentStart; }

    public char[] getBlockCommentEnd() { return blockCommentEnd; }

    public TokenType[] getIfStatement() { return new TokenType[] {KeywordsTypes.KEYWORD_IF_START,
                                                                  KeywordsTypes.KEYWORD_IF_END };}

    public TokenType[] getWhileStatement() { return new TokenType[] {KeywordsTypes.KEYWORD_CYCLE_WHILE,
                                                                     KeywordsTypes.KEYWORD_WHILE_END};}

    public TokenType[][] getVarStatement() { return new TokenType[][] {
            new TokenType[]{KeywordsTypes.KEYWORD_TYPE},
            new TokenType[]{SeparatorTypes.BLOCK_START}};}

    public TokenType[] getForStatement() { return new TokenType[] {KeywordsTypes.KEYWORD_CYCLE_FOR,
                                                                   KeywordsTypes.KEYWORD_WHILE_END};}

    public LanguageTranslator getTranslatorRules() { return new PascalTranslateRules();}

}
