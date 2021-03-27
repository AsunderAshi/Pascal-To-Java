package Pascal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import Main.Language;
import Main.LanguageTranslator;
import Main.MultiToken;
import Main.Token;
import Types.*;

public class PascalTranslateRules implements LanguageTranslator{

    public MultiToken translateOperatorIf(MultiToken token, Language current, Language necessary)
    {
        MultiToken finalToken = new MultiToken(HigherTypes.OPERATOR_IF);
        ArrayList<Token> tokens = changeOperators(token, HigherTypes.OPERATOR_IF,current, necessary).getTokens();
        finalToken.addToken(tokens.get(0));
        finalToken.addToken(tokens.get(1));
        finalToken.addToken(new Token(SeparatorTypes.OPEN_BRACKET,
                getValue(necessary.getSeparators(),SeparatorTypes.OPEN_BRACKET)));
        int i = 2;
        while (i+1 < tokens.size() && !(Objects.equals(tokens.get(i+1).getType(), KeywordsTypes.KEYWORD_IF_END)))
        {
            finalToken.addToken(tokens.get(i));
            i++;
        }
        finalToken.addToken(new Token(SeparatorTypes.CLOSE_BRACKET,
                getValue(necessary.getSeparators(),SeparatorTypes.CLOSE_BRACKET)));
        return finalToken;
    }

    public ArrayList<Token> initTranslate(ArrayList<Token> tokens, Language current, Language necessary)
    {
        Token progr = new Token(KeywordsTypes.KEYWORD_FUNCTION,
                getValue(necessary.getSpecificKeywords(), KeywordsTypes.KEYWORD_FUNCTION));
        ArrayList<Token> finalTokens = new ArrayList<>();
        finalTokens.add(progr);
        finalTokens.add(new Token(SeparatorTypes.BLOCK_START,
                getValue(necessary.getSeparators(), SeparatorTypes.BLOCK_START)));
        finalTokens.add(new Token(PrimaryTypes.WHITESPACE, "\n"));
        for (int i = 0; i < tokens.size(); i++)
            finalTokens.add(tokens.get(i));
        finalTokens.add(new Token(SeparatorTypes.BLOCK_END,
                getValue(necessary.getSeparators(), SeparatorTypes.BLOCK_END)));
        return finalTokens;
    }

    public MultiToken translateCycleFor(MultiToken token, Language current, Language necessary)
    {
        MultiToken finalToken = new MultiToken(HigherTypes.CYCLE_FOR);
        ArrayList<Token> tokens = changeOperators(token, HigherTypes.CYCLE_FOR,current, necessary).getTokens();
        finalToken.addToken(tokens.get(0));
        finalToken.addToken(tokens.get(1));
        finalToken.addToken(new Token(SeparatorTypes.OPEN_BRACKET,
                getValue(necessary.getSeparators(),SeparatorTypes.OPEN_BRACKET)));
        finalToken.addToken(new Token(KeywordsTypes.KEYWORD_INTEGER,
                getValue(necessary.getSpecificKeywords(), KeywordsTypes.KEYWORD_INTEGER)));
        finalToken.addToken(new Token(PrimaryTypes.WHITESPACE, " "));
        Token variable = tokens.get(4);
        int i = 4;
        while (i + 1 < tokens.size() && (!(Objects.equals(tokens.get(i+1).getType(), OperatorTypes.OPERATOR_DECREMENT))
        && (!(Objects.equals(tokens.get(i+1).getType(), OperatorTypes.OPERATOR_INCREMENT)))))
        {
            finalToken.addToken(tokens.get(i));
            i++;
        }
        finalToken.addToken(new Token(SeparatorTypes.END_STATEMENT,
                getValue(necessary.getSeparators(),SeparatorTypes.END_STATEMENT)));
        finalToken.addToken(new Token(PrimaryTypes.WHITESPACE, " "));
        finalToken.addToken(variable);
        finalToken.addToken(new Token(PrimaryTypes.WHITESPACE, " "));
        TokenType type = null;
        if (Objects.equals(tokens.get(i+1).getType(), OperatorTypes.OPERATOR_INCREMENT))
        {
            type = OperatorTypes.OPERATOR_INCREMENT;
            finalToken.addToken(new Token(OperatorTypes.OPERATOR_LOWEST_OR_EQUAL,
                    getValue(necessary.getOperators(), OperatorTypes.OPERATOR_LOWEST_OR_EQUAL)));
        }
        if (Objects.equals(tokens.get(i+1).getType(), OperatorTypes.OPERATOR_DECREMENT))
        {
            type = OperatorTypes.OPERATOR_DECREMENT;
            finalToken.addToken(new Token(OperatorTypes.OPERATOR_GREATER_OR_EQUAL,
                    getValue(necessary.getOperators(), OperatorTypes.OPERATOR_GREATER_OR_EQUAL)));
        }
        finalToken.addToken(new Token(PrimaryTypes.WHITESPACE, " "));
        finalToken.addToken(tokens.get(i+3));
        finalToken.addToken(new Token(SeparatorTypes.END_STATEMENT,
                getValue(necessary.getSeparators(),SeparatorTypes.END_STATEMENT)));
        finalToken.addToken(new Token(PrimaryTypes.WHITESPACE, " "));
        finalToken.addToken(variable);
        finalToken.addToken(new Token(type, getValue(necessary.getOperators(), type)));
        finalToken.addToken(new Token(SeparatorTypes.CLOSE_BRACKET,
                getValue(necessary.getSeparators(),SeparatorTypes.CLOSE_BRACKET)));
        return finalToken;
    }

    public MultiToken changeOperators(MultiToken token, TokenType type, Language current, Language necessary)
    {
        ArrayList<Token> tokens = token.getTokens();
        MultiToken finalToken = new MultiToken(type);
        for (Token currentToken : tokens)
        {
            if (isOperator(currentToken))
            {
                finalToken.addToken(new Token(currentToken.getType(),
                        getValue(necessary.getOperators(), currentToken.getType())));
            }
            else finalToken.addToken(currentToken);
        }
        return finalToken;
    }

    public static boolean isOperator(Token object) {
        for (OperatorTypes type : OperatorTypes.values())
        {
            if (type.equals(object.getType()))
            {
                return true;
            }
        }
        return false;
    }

    public MultiToken translateCycleWhile(MultiToken token, Language current, Language necessary)
    {
        MultiToken finalToken = new MultiToken(HigherTypes.OPERATOR_IF);
        ArrayList<Token> tokens = changeOperators(token, HigherTypes.CYCLE_WHILE,current, necessary).getTokens();
        finalToken.addToken(tokens.get(0));
        finalToken.addToken(tokens.get(1));
        finalToken.addToken(new Token(SeparatorTypes.OPEN_BRACKET,
                getValue(necessary.getSeparators(),SeparatorTypes.OPEN_BRACKET)));
        int i = 2;
        while (i+1 < tokens.size() && !(Objects.equals(tokens.get(i+1).getType(), KeywordsTypes.KEYWORD_WHILE_END)))
        {
            finalToken.addToken(tokens.get(i));
            i++;
        }
        finalToken.addToken(new Token(SeparatorTypes.CLOSE_BRACKET,
                getValue(necessary.getSeparators(),SeparatorTypes.CLOSE_BRACKET)));
        return finalToken;
    }

    public String getValue(HashMap<String, TokenType> hashmap, TokenType type)
    {
        for (String key : hashmap.keySet())
            if (Objects.equals(type, hashmap.get(key)))
                return key;
        return null;
    }

    public MultiToken addVariable (MultiToken token, Language current, Language necessary)
    {
        MultiToken finalToken = new MultiToken(HigherTypes.VARIABLE);
        ArrayList<Token> tokens = token.getTokens();
        Token variable = tokens.get(0);
        String type = getValue(necessary.getSpecificKeywords(), tokens.get(tokens.size() - 2).getType());
        finalToken.addToken(new Token(tokens.get(tokens.size() - 2).getType(), type));
        finalToken.addToken(new Token(PrimaryTypes.WHITESPACE, " "));
        finalToken.addToken(variable);
        finalToken.addToken(new Token(SeparatorTypes.ITERATOR_FOR,
                getValue(necessary.getSeparators(), SeparatorTypes.END_STATEMENT)));
        return finalToken;
    }

    public ArrayList<MultiToken> translateVariables(ArrayList<MultiToken> token, Language current, Language necessary)
    {
        ArrayList<Token> tokens = token.get(0).getTokens();
        for (int i = 0; i < 2; i++)
            tokens.remove(0);
        ArrayList<MultiToken> finalTokens = new ArrayList<>();
        int counter = 0;
        MultiToken variable = new MultiToken(HigherTypes.VARIABLE);
        while (counter + 7 < tokens.size())
        {
            for (int i = 0; i < 6; i++)
            {
                variable.addToken(tokens.get(i + counter));
            }
            variable = addVariable(variable, current, necessary);
            variable.addToken(new Token(PrimaryTypes.WHITESPACE, "\n"));
            finalTokens.add(variable);
            counter += 7;
            variable = new MultiToken(HigherTypes.VARIABLE);
        }
        return finalTokens;
    }
}

