package Main;

import java.util.ArrayList;

import Types.*;

public class MultiToken extends Token{
    private ArrayList<Token> tokens = new ArrayList<>();
    private TokenType type;

    public MultiToken(TokenType type) {
        super(type, null);
        this.type = type;
    }
    public void addToken(Token token)
    {
        tokens.add(token);
    }

    @Override
    public String getText() {
        StringBuilder builder = new StringBuilder();
        for (Token token : tokens)
            builder.append(token.getText());
        return builder.toString();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.type + "\n");
        builder.append("\t" + "[" + tokens.get(0).getType() + "\n");
        for (int i = 1; i < tokens.size() - 1; i++)
        {
            builder.append("\t" + tokens.get(i).getType() + "\n");
        }
        builder.append("\t" + tokens.get(tokens.size() - 1).getType() + "]");
        return builder.toString();
    }

    public ArrayList<Token> getTokens()
    {
        return tokens;
    }
}
