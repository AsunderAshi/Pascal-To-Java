package translators;

import Main.Language;
import Main.Token;
import Types.*;

public class BlockCommentTransformator implements TokenTransformator {

    private final static TokenType type = PrimaryTypes.BLOCK_COMMENT;

    public Token transformateToken(Token token, Language current, Language necessary) {
        String text = token.getText();
        String[] textForReplace = getCommentText(current.getBlockCommentStart(),
                necessary.getBlockCommentStart(), current, necessary);
        text = text.replace(text.substring(0, textForReplace[0].length()), textForReplace[1]);
        textForReplace = getCommentText(current.getBlockCommentEnd(),
                necessary.getBlockCommentEnd(), current, necessary);
        text = text.replace(text.substring(text.length() - textForReplace[0].length(), text.length()), textForReplace[1]);
        return new Token(type, text);
    }

    public String[] getCommentText(char[] oldCom, char[] newCom, Language current, Language necessary)
    {
        String oldComment = "";
        String newComment = "";
        for (int i = 0; i < oldCom.length; i++) {
            oldComment += current.getBlockCommentStart()[i];
        }
        for (int i = 0; i < newCom.length; i++) {
            newComment += necessary.getBlockCommentStart()[i];
        }
        return new String[]{oldComment, newComment};
    }
}
