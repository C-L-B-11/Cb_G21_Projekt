package org.SPLGrammar.grammar;// Generated from /vol/fob-vol7/mi21/blumenca/Dokumente/Semester4/CpB/Projekt/src/main/java/org/SPLGrammar/grammar/SPLGrammar.g4 by ANTLR 4.12.0

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;

/**
 * This class provides an empty implementation of {@link SPLGrammarVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 * 
 * Attribut errors Sammlung aller unterwgs angetroffenen Fehler
 */
@SuppressWarnings("CheckReturnValue")
public class SPLGrammarBaseVisitor<T> extends AbstractParseTreeVisitor<T> implements SPLGrammarVisitor<T> {
    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    Symboltabelle tabelle = new Symboltabelle();
    private boolean parsedSuccessful = true;
    private String errors = "\n";
    @Override public T visitProgram(SPLGrammarParser.ProgramContext ctx) throws Exception {
        T ret=visitChildren(ctx);
        //System.out.println(tabelle);
        if(!parsedSuccessful) // Exeption, wenn mindesten ein Fehler angetroffen
        {
            throw new Exception(errors);
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitDeclaration(SPLGrammarParser.DeclarationContext ctx) { return visitChildren(ctx); }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitVarDecl(SPLGrammarParser.VarDeclContext ctx) {
        if(ctx.ASSIGN()==null) // wir hatten uns dazu entschieden Deklarationen nur zusammen mit Initialiesierungen zuzulasse, kein Null, kein Default
        {
            errors += ("Error: Variable " + ctx.IDENTIFIER().getText() + " is not beeing initalized" +" in Line: " + ctx.getStart().getLine());
            errors += "\n";
            parsedSuccessful=false;

        }
        int type=getType(ctx.expression());
        if(type!=-1) { //Korrekter Wert, Einfügen in Symboltsbelle
            if (tabelle.addVariable(ctx.IDENTIFIER().getText(), type) == -1) { // Ueberprüft ob Variable mit gleichem Namen im Gleichen oder uebergeordneten Block schonmal deklariert wurde, warnt vor ungewolltem Ueberschreiben
                System.out.println("Warning: Variable " + ctx.IDENTIFIER().getText() + " was defined before (Shadowing/Neudefinition)"+" in Line: " + ctx.getStart().getLine());
            }
        }
        else //Fehler bei unbestimmten Wert, keine definierte Semantik
        {
            errors += ("Error: Undefined Type in Line: " + ctx.getStart().getLine());
            errors += "\n";
            parsedSuccessful=false;
        }
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitStatement(SPLGrammarParser.StatementContext ctx) { return visitChildren(ctx); }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitExprStmt(SPLGrammarParser.ExprStmtContext ctx) { return visitChildren(ctx); }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitIfStmt(SPLGrammarParser.IfStmtContext ctx) {
        T ret=visitChildren(ctx);
        if(getTypeIF(ctx.expression())!=0) // Fehler falls die Expression in der IF-Verzweigung nicht von Typ Boolean ist
        {
            errors += ("Error: Expression is not a bool"+" in Line: " + ctx.getStart().getLine());
            errors += "\n";
            parsedSuccessful=false;
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitPrintStmt(SPLGrammarParser.PrintStmtContext ctx) { return visitChildren(ctx); }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitWhileStmt(SPLGrammarParser.WhileStmtContext ctx) {
        T ret=visitChildren(ctx);
        if(getTypeIF(ctx.expression())!=0) // Fehler falls die Expression in der While-Schleife nicht von Typ Boolean ist
        {
            {
                errors += ("Error: Expression is not a bool"+" in Line: " + ctx.getStart().getLine());
                errors += "\n";
                parsedSuccessful=false;
            }
        }
        return ret;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitBlock(SPLGrammarParser.BlockContext ctx) {
        tabelle.enterBlock(); // betritt aktuellen Block
        T ret =visitChildren(ctx);
        //System.out.println(tabelle);
        tabelle.leaveBlock(); // verlässt aktuellen Block
        return  ret;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitExpression(SPLGrammarParser.ExpressionContext ctx) { return visitChildren(ctx); }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitAssignment(SPLGrammarParser.AssignmentContext ctx) {
        if(ctx.IDENTIFIER()!=null) {
            int type = tabelle.getEintrag(ctx.IDENTIFIER().getText()).getType();
            if (type == -1) { // Fehler falls kein Eintrag in der Symboltabelle gefunden, zu der Variable, der ein Wert zugwiesen werden soll
                errors += ("Error: Can not find Variable " + ctx.IDENTIFIER().getText() + " (Scoping?)"+" in Line: " + ctx.getStart().getLine());
                errors += "\n";
                parsedSuccessful=false;
            } else if (type != getType(ctx.assignment())) // Fehler falls der Variable ein anderer Typ als bei der Dekleration zugewiesen wird (kein Typwechsel erlaubt)
            {
                errors += ("Error: Variable " + ctx.IDENTIFIER().getText() + " expects type " + typeToText(type)+" in Line: " + ctx.getStart().getLine());
                errors += "\n";
                parsedSuccessful=false;
            }
        }
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitLogic_or(SPLGrammarParser.Logic_orContext ctx) { return visitChildren(ctx); }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitLogic_and(SPLGrammarParser.Logic_andContext ctx) { return visitChildren(ctx); }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitEquality(SPLGrammarParser.EqualityContext ctx) { return visitChildren(ctx); }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitComparison(SPLGrammarParser.ComparisonContext ctx) { return visitChildren(ctx); }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitTerm(SPLGrammarParser.TermContext ctx) { return visitChildren(ctx); }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitFactor(SPLGrammarParser.FactorContext ctx) { return visitChildren(ctx); }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitUnary(SPLGrammarParser.UnaryContext ctx) { return visitChildren(ctx); }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public T visitPrimary(SPLGrammarParser.PrimaryContext ctx) {
        if(ctx.IDENTIFIER()!=null) {
            if (tabelle.getEintrag(ctx.IDENTIFIER().getText()).getType() == -1) { // Wenn Primary nur eine Variable ist, dann Fehler, wenn die Variable noch nicht deklariert wurde 
                errors += ("Errorr: Can not find Variable " + ctx.IDENTIFIER().getText() + " (Scoping?)" +" in Line: " + ctx.getStart().getLine());
                errors += "\n";
                parsedSuccessful=false;
            }
        }
        return visitChildren(ctx);
    }

    /**
     * Methodengruppe Typberechnung mit Asignments:
     * Berechnung des Typs zu einer gegebenen Expression - Assignments sind erlaubt
     * Der Baum der Expression wird durch Methoden der Methodengruppe rekursiv aufgebaut und der Typ wird ermittelt
     * @return -1 <=> inkonsistenter Typ, 0 <=> bool, 1 <=> int, 2 <=> String
     * 
     * Methode der Methodengruppe Typberechnung mit Asignments
     * Expression ist Anfang
     */
    private int getType(SPLGrammarParser.ExpressionContext ctx)        //-1:Error, 0:bool, 1:int, 2:String
    {
        return getType(ctx.assignment()); // Assignments ok
    }

    /**
     * Methodengruppe Typberechnung ohne Asignments:
     * Berechnung des Typs zu einer gegebenen Expression - Assignments führen zu Fehler
     * Der Baum der Expression wird durch Methoden der Methodengruppe rekursiv aufgebaut und der Typ wird ermittelt
     * @return -1 <=> inkonsistenter Typ, 0 <=> bool, 1 <=> int, 2 <=> String
     * 
     * Methode der Methodengruppe Typberechnung ohne Asignments
     * Expression ist Anfang
     */
    private int getTypeIF(SPLGrammarParser.ExpressionContext ctx)        //-1:Error, 0:bool, 1:int, 2:String
    {
        return getTypeIF(ctx.assignment()); // keine Assignments erlaubt
    }

    /**
     * Methode der Methodengruppe Typberechnung mit Asignments
     * Assignment
     */
    private int getType(SPLGrammarParser.AssignmentContext ctx)
    {
        if(ctx.logic_or()==null)
        {
            return getType(ctx.assignment()); // Multi-Assign ok! z.B a=b=c=5 => Rekursiv der Typ des letzten Assignments
        }
        else
        {
            return getType(ctx.logic_or());// Typ nach oben reichen
        }
    }

    /**
     * Methode der Methodengruppe Typberechnung ohne Asignments
     * Assignment
     */
    private int getTypeIF(SPLGrammarParser.AssignmentContext ctx)
    {
        if(ctx.logic_or()==null) // keine Assignments an bestimmten Stellen erlaubt {if, while, hinter Operator}
        {
            return -1;
        }
        else 
        {
            return getType(ctx.logic_or()); // Typ nach oben reichen
        }
    }

    /**
     * Methode der Methodengruppe Typberechnung
     * Logic_or
     */

    private int getType(SPLGrammarParser.Logic_orContext ctx)
    {
        if(ctx.logic_and().size()>1)
        {
            for(SPLGrammarParser.Logic_andContext lactx : ctx.logic_and()) // {||} => alles muss Boolean sein
            {
                if(getType(lactx)!=0)
                {return -1;}
            }
            return 0;
        }
        else
        {
            return getType(ctx.logic_and(0));// Typ nach oben reichen
        }
    }

    /**
     * Methode der Methodengruppe Typberechnung
     * Logic_and
     */

    private int getType(SPLGrammarParser.Logic_andContext ctx)
    {
        if(ctx.equality().size()>1)
        {
            for(SPLGrammarParser.EqualityContext ectx : ctx.equality()) // {&&} => alles muss Boolean sein
            {
                if(getType(ectx)!=0)
                {return -1;}
            }
            return 0;
        }
        else
        {
            return getType(ctx.equality(0)); // Typ nach oben reichen
        }
    }

    /**
     * Methode der Methodengruppe Typberechnung
     * Equality
     */

    private int getType(SPLGrammarParser.EqualityContext ctx)
    {
        if(ctx.comparison().size()>1)
        {
            int type = getType(ctx.comparison(0));
            for(SPLGrammarParser.ComparisonContext cctx : ctx.comparison()) // {!=,==} => alles muss gleichen Typ haben sonst Fehler
            {
                if(getType(cctx)!= type)
                {return -1;}
            }
            return 0;
        }
        else
        {
            return getType(ctx.comparison(0)); // Typ nach oben reichen
        }
    }

    /**
     * Methode der Methodengruppe Typberechnung
     * Comparison
     */

    private int getType(SPLGrammarParser.ComparisonContext ctx)
    {
        if(ctx.term().size()>1)
        {

            for(SPLGrammarParser.TermContext tctx : ctx.term()) // {<,>,<=,>=} => alles muss Number sein sonst Fehler
            {
                if(getType(tctx)!=1)
                {return -1;}
            }
            return 0;
        }
        else
        {

            return getType(ctx.term(0)); // Typ nach oben reichen
        }
    }

    /**
     * Methode der Methodengruppe Typberechnung
     * Term
     */

    private int getType(SPLGrammarParser.TermContext ctx)
    {
        if(ctx.factor().size()>1)
        {
            int type = getType(ctx.factor(0));
            for(SPLGrammarParser.FactorContext fctx : ctx.factor()) // {+,-} => alles muss gleichen Typ haben && nichts ist Boolean sonst Fehler
            {
                if(getType(fctx)!= type)
                {return -1;}
            }
            if(type==0) // kein Boolean erlaubt
            {return -1;}
            else if(type==2&&ctx.PLUS().size()!=ctx.factor().size()-1) // String => nur {+} erlaubt, sonst Fehler
            {
                return -1;
            }
            else
                return type;
        }
        else
        {
            return getType(ctx.factor(0)); // Typ nach oben reichen
        }
    }

    /**
     * Methode der Methodengruppe Typberechnung
     * Factor
     */

    private int getType(SPLGrammarParser.FactorContext ctx)
    {
        if(ctx.unary().size()>1)
        {
            for(SPLGrammarParser.UnaryContext uctx : ctx.unary()) // {/,*} => alles muss Number sein sonst Fehler
            {
                if(getType(uctx)!=1)
                {return -1;}
            }
            return 1;
        }
        else
        {
            return getType(ctx.unary(0)); // Typ nach oben reichen
        }
    }

    /**
     * Methode der Methodengruppe Typberechnung
     * Unary
     */

    private int getType(SPLGrammarParser.UnaryContext ctx)
    {
        if(ctx.NOT()!=null) // {!} => muss Boolean sein sonst fehler
        {
            if(getType(ctx.primary())==0)
            {return 0;}
            else
            {return -1;}
        }
        if(ctx.MINUS()!=null) // {-} =>  muss Number sein sonst Fehler
        {
            if(getType(ctx.primary())==1)
            {return 1;}
            else
            {return -1;}
        }
        return getType(ctx.primary()); // Typ nach oben reichen
    }

    /**
     * Methode der Methodengruppe Typberechnung
     * Primary ist Ende
     */
    private int getType(SPLGrammarParser.PrimaryContext ctx)
    {
        if(ctx.TRUE()!=null||ctx.FALSE()!=null)
        {return 0;} // Typ ist Bool
        if(ctx.NUMBER()!=null)
        {return 1;} // Typ ist Int
        if(ctx.STRING()!=null)
        {return 2;} // Typ ist String
        if(ctx.IDENTIFIER()!=null)
        {return tabelle.getEintrag(ctx.IDENTIFIER().getText()).getType();} // Typ der Varaiblen bei der Dekleration
        if(ctx.expression()!=null)
        {return getTypeIF(ctx.expression());} // Typ der Expression (in der kein Assignment erlaubt ist)
        return -1;
    }

    private String typeToText(int type)
    {
        return switch (type) {
            case 0 -> "Bool";
            case 1 -> "Num";
            case 2 -> "String";
            default -> "No Type";
        };

    }
}