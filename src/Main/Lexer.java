package Main;

import java.util.ArrayList;

import readers.*;

public class Lexer {

	private String program;
	private TokenReader[] readers = new TokenReader[]{
			new OperatorReader(),
			new SeparationReader(),
			new CharReader(),
			new FloatReader(),
			new IntReader(),
			new KeywordsReader(),
			new IdentifierReader(),
			new StringReader(),
			new WhitespaceReader(),
			new CommentReader()
	};
	private ArrayList<Token> tokens = new ArrayList<>();
	private Language language;

	public Lexer(String program) {
		this.program = program;
	}

	public void setLanguage(Language language)
	{
		this.language = language;
	}

	public void readNextToken() throws Exception{
		int lengthMax = 0;
		Token maxToken = null;
		for (TokenReader reader : readers){
			Token currentToken = reader.readToken(program, language);
			if(currentToken != null){
				if(currentToken.getText().length() > lengthMax){
					lengthMax = currentToken.getText().length();
					maxToken = currentToken;
				}
			}
		}
		if (lengthMax != 0) {
			program = program.substring(lengthMax);
			tokens.add(maxToken);
		}
		else { throw new Exception("Unknown token"); }
	}

	public ArrayList<Token> getTokens() { return tokens; }

	public boolean hasNextToken() {
		return (program.length() != 0);
	}

}
