package Main;
import java.util.ArrayList;

import Java.Java;
import Pascal.Pascal;

import java.io.*;

public class Main {
	
	public static void main(String[] args)
    {
        String progr = null;
		try {
			progr = readFile("src\\Main\\Java.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        Lexer lexer = new Lexer(progr);
        Language java = new Java();
        Language pascal = new Pascal();
        lexer.setLanguage(java);
        while(lexer.hasNextToken()) {
            try {
				lexer.readNextToken();
			} catch (Exception e) {
				e.printStackTrace();
			}
        }
        ArrayList<Token> tokens = lexer.getTokens();
        Analyser analyser = new Analyser(tokens, java);
        analyser.analyse();
        ArrayList<Token> resultTokens = analyser.getResult();
        Translator translator = new Translator(resultTokens);
        translator.register("java", java);
        translator.register("pascal", pascal);
        translator.translate("java", "pascal");
        ArrayList<Token> result = translator.getTokens();
        for (int i = 0; i < result.size(); i++)
        {
            System.out.println(result.get(i));
        }
        try {
			writeFile("src\\Main\\result.txt", result);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private static String readFile(String source) throws IOException {
        FileReader in = new FileReader(source);
        StringBuilder contents = new StringBuilder();
        char[] buffer = new char[4096];
        int read = 0;
        do {
            contents.append(buffer, 0, read);
            read = in.read(buffer);
        } while (read >= 0);
        in.close();
        return contents.toString();
    }

    private static void writeFile(String source, ArrayList<Token> tokens) throws IOException {
        FileWriter out = new FileWriter(source);
        StringBuilder contents = new StringBuilder();
        for (Token token : tokens) {
            contents.append(token.getText());
        }
        out.write(contents.toString());
        out.flush();
        out.close();
    }

}
