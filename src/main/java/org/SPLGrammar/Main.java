package org.SPLGrammar;

import org.SPLGrammar.grammar.*;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws Exception {
        String relativePath = "src/main/java/org/SPLGrammar/Tests/tests.spl";               //Pfad Generieren
        String currentDirectory = System.getProperty("user.dir");
        String absoluteFilePath = currentDirectory + "/" + relativePath;
        File file = new File(absoluteFilePath);
        if(file.exists()) {                                                                 //Datei Lesen
            String expression = Files.readString(Path.of(absoluteFilePath));
            SPLGrammarLexer lexer = new SPLGrammarLexer(CharStreams.fromString(expression));//lexer
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SPLGrammarParser parser = new SPLGrammarParser(tokens);                         //parser

            SPLGrammarParser.ProgramContext tree = parser.program();                                              //Syntaxbaum
            SPLGrammarVisitor visitor = new SPLGrammarBaseVisitor();                        //Semantische analyse
            try {
                visitor.visitProgram(tree);
            } catch (IOException ex) {
                throw new Exception(ex.getMessage());
            }


            //System.out.println("Parse tree: " + tree.toStringTree(parser));

            SPLGrammarBaseInterpreter interpreter = new SPLGrammarBaseInterpreter();        //interpreter
            interpreter.visitProgram(tree);
        }
        else
        {
            System.out.print("Can't find the file in ");
            System.out.println(absoluteFilePath);
        }
    }
}
