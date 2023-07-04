package org.SPLGrammar;
import org.SPLGrammar.grammar.SPLGrammarLexer;
import org.SPLGrammar.grammar.SPLGrammarParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        Path filePath = Path.of("/vol/fob-vol7/mi21/blumenca/Dokumente/Semester4/CpB/Projekt/src/main/java/org/SPLGrammar/Tests/sample.spl");

        String expression = Files.readString(filePath);
        SPLGrammarLexer lexer = new SPLGrammarLexer(CharStreams.fromString(expression));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SPLGrammarParser parser = new SPLGrammarParser(tokens);
        ParseTree tree = parser.program();

        System.out.println("Parse tree: " + tree.toStringTree(parser));
    }
}
