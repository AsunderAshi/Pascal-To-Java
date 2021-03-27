package translators;

import Main.Language;
import Main.Token;
import Types.*;

public class LineCommentTransformator implements TokenTransformator {
    private final static TokenType type = PrimaryTypes.LINE_COMMENT;

    public Token transformateToken(Token token, Language current, Language necessary) {
        String text = token.getText();
        String oldComment = "";
        String newComment = "";
        for (int i = 0; i < current.getLineCommentType().length; i++)
        {
            oldComment += current.getLineCommentType()[i];
        }
        for (int i = 0; i < necessary.getLineCommentType().length; i++)
        {
            newComment += necessary.getLineCommentType()[i];
        }
        text = text.replace(text.substring(0, oldComment.length()), newComment);
        return new Token(type,  text);
    }

}
