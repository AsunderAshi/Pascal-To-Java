package Main;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import Types.*;
import translators.*;

public class Translator {

    private ArrayList<Token> tokens;
    private ArrayList<Token> result = new ArrayList<>();
    private HashMap<String, Language> languages = new HashMap<>();
    private HashMap<TokenType, TokenTransformator> conformity = new HashMap<>();

    public Translator(ArrayList<Token> tokens) {
        this.tokens = tokens;
        setConformity();
    }

    public void register(String key, Language language)
    {
        languages.put(key, language);
    }

    private void setConformity() {
        conformity.put(PrimaryTypes.STRING, new StringTransformator());
        conformity.put(PrimaryTypes.CHAR, new CharTransformator());
        conformity.put(PrimaryTypes.LINE_COMMENT, new LineCommentTransformator());
        conformity.put(PrimaryTypes.BLOCK_COMMENT, new BlockCommentTransformator());
        for (TokenType type : KeywordsTypes.values())
        {
            conformity.put(type, new KeywordsTransformator());
        }
        for (TokenType type : HigherTypes.values())
        {
            conformity.put(type, new HigherTypesTransformator());
        }
        for (TokenType type : SeparatorTypes.values()) {
            conformity.put(type, new SeparatorTransformator());
        }
        for (TokenType type : OperatorTypes.values()) {
            conformity.put(type, new OperatorTransformator());
        }
    }

    public ArrayList<Token> getTokens()
    {
        return result;
    }

    public void translate(String curLang, String necLang)
    {
        Language current = languages.get(curLang);
        Language necessary = languages.get(necLang);
        handleVariables(current, necessary);
        for (Token token : tokens)
        {
            if (conformity.containsKey(token.getType()))
            {
                TokenTransformator transformator = conformity.get(token.getType());
                Token resultToken = transformator.transformateToken(token, current, necessary);

                if (resultToken != null)
                    result.add(resultToken);
            }
            else
                result.add(token);
        }
        result = current.getTranslatorRules().initTranslate(result, current, necessary);
    }

    public void handleVariables(Language current, Language necessary)
    {
        ArrayList<MultiToken> variables = new ArrayList<>();
        int index = 0;
        for (int i = 0; i < tokens.size(); i++)
        {
            if (Objects.equals(tokens.get(i).getType(), HigherTypes.VARIABLE))
            {
                if (index == 0)
                    index = i;
                variables.add((MultiToken) tokens.get(i));
            }
        }
        if (variables.size() != 0)
        {
            for (int i = index; i < index + (variables.size() * 2); i++)
            {
                tokens.remove(index);
            }
            ArrayList<MultiToken> variablesInit = current.getTranslatorRules().translateVariables(variables, current, necessary);
            for (Token token : variablesInit)
                result.add(token);
        }
    }
}
