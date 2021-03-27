package readers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Main.Language;
import Main.Token;
import Types.PrimaryTypes;

public class CommentReader implements TokenReader{
    private String[] comments;

    public void setComments(Language language){this.comments = language.getComments();}

    public Token readToken(String input, Language language)
    {
        this.setComments(language);
        String oneStringComment = comments[0];
        String multipleStringComment = "";
        if(comments.length > 1)
            multipleStringComment = comments[1];
        Pattern singleStringComment = Pattern.compile(oneStringComment);
        Matcher match = singleStringComment.matcher(input);
        if(match.find())
        {
            String comment = match.group();
            return new Token(PrimaryTypes.LINE_COMMENT, comment.substring(0, comment.length() - 2));
        }
        if(!multipleStringComment.isEmpty())
        {
            Pattern multiStringComment = Pattern.compile(multipleStringComment);
            match = multiStringComment.matcher(input);
            if(match.find())
                return new Token(PrimaryTypes.BLOCK_COMMENT, match.group());
        }
        return new Token(PrimaryTypes.BLOCK_COMMENT, "");
    }
}
