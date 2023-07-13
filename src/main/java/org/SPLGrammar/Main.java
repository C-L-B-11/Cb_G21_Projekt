package org.SPLGrammar;
import org.SPLGrammar.grammar.*;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws Exception {
        Path filePath = Path.of("/vol/fob-vol7/mi21/blumenca/Dokumente/Semester4/CpB/Projekt/src/main/java/org/SPLGrammar/Tests/sample.spl");

        String expression = Files.readString(filePath);
        SPLGrammarLexer lexer = new SPLGrammarLexer(CharStreams.fromString(expression));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        SPLGrammarParser parser = new SPLGrammarParser(tokens);

        ParseTree tree = parser.program();
        SPLGrammarVisitor visitor = new SPLGrammarBaseVisitor();
        try {visitor.visitProgram((SPLGrammarParser.ProgramContext) tree);}
        catch (IOException ex){throw new Exception(ex.getMessage());}


        System.out.println("Parse tree: " + tree.toStringTree(parser));

        SPLGrammarBaseInterpreter interpreter = new SPLGrammarBaseInterpreter();
        interpreter.visitProgram((SPLGrammarParser.ProgramContext) tree);
    }
}
