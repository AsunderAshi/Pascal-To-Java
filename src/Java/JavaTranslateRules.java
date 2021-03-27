package Java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import Main.Language;
import Main.LanguageTranslator;
import Main.MultiToken;
import Main.Token;
import Types.*;

public class JavaTranslateRules implements LanguageTranslator {

    public MultiToken translateOperatorIf(MultiToken token, Language current, Language necessary)
    {
        MultiToken finalToken = new MultiToken(HigherTypes.OPERATOR_IF);
        ArrayList<Token> tokens = changeOperators(token, HigherTypes.OPERATOR_IF,current, necessary).getTokens();
        Normalization(current, tokens, SeparatorTypes.OPEN_BRACKET);
        Normalization(current, tokens, SeparatorTypes.CLOSE_BRACKET);
        HashMap<String, TokenType> keywords = necessary.getSpecificKeywords();
        tokens.add(new Token(PrimaryTypes.WHITESPACE, " "));
        tokens.add(new Token(necessary.getIfStatement()[1], getValue(keywords, necessary.getIfStatement()[1])));
        for (Token newToken : tokens)
            finalToken.addToken(newToken);
        return finalToken;
    }

    public MultiToken createEternalCycle(Language necessary)
    {
        MultiToken finalToken = new MultiToken(HigherTypes.CYCLE_WHILE);
        finalToken.addToken(new Token(KeywordsTypes.KEYWORD_CYCLE_WHILE,
                getValue(necessary.getSpecificKeywords(), KeywordsTypes.KEYWORD_CYCLE_WHILE)));
        finalToken.addToken(new Token(PrimaryTypes.WHITESPACE, " "));
        finalToken.addToken(new Token(OperatorTypes.OPERATOR_TRUE,
                getValue(necessary.getOperators(), OperatorTypes.OPERATOR_TRUE)));
        finalToken.addToken(new Token(PrimaryTypes.WHITESPACE, " "));
        finalToken.addToken(new Token(KeywordsTypes.KEYWORD_WHILE_END,
                getValue(necessary.getSpecificKeywords(), KeywordsTypes.KEYWORD_WHILE_END)));
        return finalToken;
    }

    public MultiToken translateCycleFor(MultiToken token, Language current, Language necessary)
    {
        MultiToken finalToken = new MultiToken(HigherTypes.CYCLE_FOR);
        if (token.getTokens().size() == 5)
        {
            return createEternalCycle(necessary);
        }
        ArrayList<Token> tokens = changeOperators(token, HigherTypes.CYCLE_FOR,current, necessary).getTokens();
        tokens = Normalization(current, tokens, SeparatorTypes.OPEN_BRACKET);
        tokens = Normalization(current, tokens, SeparatorTypes.CLOSE_BRACKET);
        tokens.set(2, new Token(KeywordsTypes.KEYWORD_TYPE,
                getValue(necessary.getSpecificKeywords(), KeywordsTypes.KEYWORD_TYPE)));
        HashMap<String, TokenType> separators = necessary.getSeparators();
        int index = getIndex(tokens, SeparatorTypes.END_STATEMENT, separators);
        for (int i = index; i < index + 3; i++)
            tokens.remove(index);
        TokenType type = tokens.get(tokens.size() - 1).getText().contains(
                getValue(necessary.getOperators(), OperatorTypes.OPERATOR_INCREMENT)) ?
                OperatorTypes.OPERATOR_INCREMENT : OperatorTypes.OPERATOR_DECREMENT;
        tokens.set(index + 1, new Token(type, getValue(necessary.getOperators(), type)));
        HashMap<String, TokenType> keywords = necessary.getSpecificKeywords();
        tokens.add(new Token(PrimaryTypes.WHITESPACE, " "));
        tokens.add(new Token(necessary.getForStatement()[1], getValue(keywords, necessary.getWhileStatement()[1])));
        index = getIndex(tokens, SeparatorTypes.END_STATEMENT, separators);
        for (int i = index; i < index + 4; i++)
            tokens.remove(index);
        for (Token newToken : tokens)
            finalToken.addToken(newToken);
        return finalToken;
    }

    public ArrayList<Token> Normalization(Language current, ArrayList<Token> tokens, TokenType type)
    {
        HashMap<String, TokenType> separators = current.getSeparators();
        for (String separator : separators.keySet()) {
            if (Objects.equals(separators.get(separator), type))
            {
                Token deleteToken = new Token(null, null);
                for (Token currentToken : tokens)
                {
                    if (currentToken.getText().equals(separator)) {
                        deleteToken = currentToken;
                        break;
                    }
                }
                tokens.remove(deleteToken);
                break;
            }
        }
        return tokens;
    }

    public ArrayList<Token> initTranslate(ArrayList<Token> tokens, Language current, Language necessary)
    {
        tokens.set(tokens.size() - 1, new Token(SeparatorTypes.PROGRAM_END,
                getValue(necessary.getSeparators(), SeparatorTypes.PROGRAM_END)));
        return tokens;
    }

    public MultiToken changeOperators(MultiToken token, TokenType type, Language current, Language necessary)
    {
        ArrayList<Token> tokens = token.getTokens();
        MultiToken finalToken = new MultiToken(type);
        for (Token currentToken : tokens)
        {
            if (isOperator(currentToken))
            {
                finalToken.addToken(new Token(currentToken.getType(), getValue(necessary.getOperators(), currentToken.getType())));
            }
            else finalToken.addToken(currentToken);
        }
        return finalToken;
    }

    public static boolean isOperator(Token object) {
        for (OperatorTypes type : OperatorTypes.values()) {
            if (type.equals(object.getType())) {
                return true;
            }
        }
        return false;
    }

    public MultiToken translateCycleWhile(MultiToken token, Language current, Language necessary)
    {
        MultiToken finalToken = new MultiToken(HigherTypes.CYCLE_WHILE);
        ArrayList<Token> tokens = changeOperators(token, HigherTypes.CYCLE_WHILE,current, necessary).getTokens();
        Normalization(current, tokens, SeparatorTypes.OPEN_BRACKET);
        Normalization(current, tokens, SeparatorTypes.CLOSE_BRACKET);
        HashMap<String, TokenType> keywords = necessary.getSpecificKeywords();
        tokens.add(new Token(PrimaryTypes.WHITESPACE, " "));
        tokens.add(new Token(necessary.getWhileStatement()[1], getValue(keywords, necessary.getWhileStatement()[1])));
        for (Token newToken : tokens)
            finalToken.addToken(newToken);
        return finalToken;
    }

    public String getValue(HashMap<String, TokenType> hashmap, TokenType type)
    {
        for (String key : hashmap.keySet())
            if (Objects.equals(type, hashmap.get(key)))
                return key;
        return null;
    }

    public int getIndex(ArrayList<Token> tokens, TokenType type, HashMap<String, TokenType> hashmap)
    {
        for (int i = 0; i < tokens.size(); i++)
        {
            if (Objects.equals(type, hashmap.get(tokens.get(i).getText())))
                return i;
        }
        return -1;
    }

    public MultiToken addVariable (MultiToken token, Language current, Language necessary)
    {
        MultiToken finalToken = new MultiToken(HigherTypes.VARIABLE);
        ArrayList<Token> tokens = token.getTokens();
        TokenType type = current.getSpecificKeywords().get(tokens.get(0).getText());
        finalToken.addToken(new Token(PrimaryTypes.WHITESPACE, "\t"));
        finalToken.addToken(tokens.get(2));
        finalToken.addToken(new Token(PrimaryTypes.WHITESPACE, " "));
        finalToken.addToken(new Token(SeparatorTypes.ITERATOR_FOR,
                getValue(necessary.getSeparators(), SeparatorTypes.ITERATOR_FOR)));
        finalToken.addToken(new Token(PrimaryTypes.WHITESPACE, " "));
        finalToken.addToken(new Token(type, getValue(necessary.getSpecificKeywords(), type)));
        finalToken.addToken(new Token(SeparatorTypes.END_STATEMENT, getValue(necessary.getSeparators(),
                SeparatorTypes.END_STATEMENT)));
        finalToken.addToken(new Token(PrimaryTypes.WHITESPACE, "\n"));
        return finalToken;
    }

    public ArrayList<MultiToken> translateVariables(ArrayList<MultiToken> tokens, Language current, Language necessary)
    {
        ArrayList<MultiToken> finalTokens = new ArrayList<>();
        MultiToken initVariables = new MultiToken(KeywordsTypes.KEYWORD_TYPE);
        initVariables.addToken(new Token(KeywordsTypes.KEYWORD_TYPE,
                getValue(necessary.getSpecificKeywords(), KeywordsTypes.KEYWORD_TYPE)));
        initVariables.addToken(new Token(PrimaryTypes.WHITESPACE, "\n"));
        finalTokens.add(initVariables);
        for (MultiToken token : tokens) {
            finalTokens.add(addVariable(token, current, necessary));
        }
        return finalTokens;
    }
}

