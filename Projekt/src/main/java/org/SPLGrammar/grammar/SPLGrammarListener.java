// Generated from /vol/fob-vol7/mi21/blumenca/Dokumente/Semester4/CpB/Projekt/src/main/java/org/SPLGrammar/grammar/SPLGrammar.g4 by ANTLR 4.12.0
package org.SPLGrammar.grammar;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SPLGrammarParser}.
 */
public interface SPLGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SPLGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(SPLGrammarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link SPLGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(SPLGrammarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link SPLGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(SPLGrammarParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link SPLGrammarParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(SPLGrammarParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link SPLGrammarParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(SPLGrammarParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link SPLGrammarParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(SPLGrammarParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link SPLGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(SPLGrammarParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link SPLGrammarParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(SPLGrammarParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link SPLGrammarParser#exprStmt}.
	 * @param ctx the parse tree
	 */
	void enterExprStmt(SPLGrammarParser.ExprStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SPLGrammarParser#exprStmt}.
	 * @param ctx the parse tree
	 */
	void exitExprStmt(SPLGrammarParser.ExprStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SPLGrammarParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void enterIfStmt(SPLGrammarParser.IfStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SPLGrammarParser#ifStmt}.
	 * @param ctx the parse tree
	 */
	void exitIfStmt(SPLGrammarParser.IfStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SPLGrammarParser#printStmt}.
	 * @param ctx the parse tree
	 */
	void enterPrintStmt(SPLGrammarParser.PrintStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SPLGrammarParser#printStmt}.
	 * @param ctx the parse tree
	 */
	void exitPrintStmt(SPLGrammarParser.PrintStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SPLGrammarParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void enterWhileStmt(SPLGrammarParser.WhileStmtContext ctx);
	/**
	 * Exit a parse tree produced by {@link SPLGrammarParser#whileStmt}.
	 * @param ctx the parse tree
	 */
	void exitWhileStmt(SPLGrammarParser.WhileStmtContext ctx);
	/**
	 * Enter a parse tree produced by {@link SPLGrammarParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(SPLGrammarParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link SPLGrammarParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(SPLGrammarParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link SPLGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(SPLGrammarParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SPLGrammarParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(SPLGrammarParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link SPLGrammarParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(SPLGrammarParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link SPLGrammarParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(SPLGrammarParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link SPLGrammarParser#logic_or}.
	 * @param ctx the parse tree
	 */
	void enterLogic_or(SPLGrammarParser.Logic_orContext ctx);
	/**
	 * Exit a parse tree produced by {@link SPLGrammarParser#logic_or}.
	 * @param ctx the parse tree
	 */
	void exitLogic_or(SPLGrammarParser.Logic_orContext ctx);
	/**
	 * Enter a parse tree produced by {@link SPLGrammarParser#logic_and}.
	 * @param ctx the parse tree
	 */
	void enterLogic_and(SPLGrammarParser.Logic_andContext ctx);
	/**
	 * Exit a parse tree produced by {@link SPLGrammarParser#logic_and}.
	 * @param ctx the parse tree
	 */
	void exitLogic_and(SPLGrammarParser.Logic_andContext ctx);
	/**
	 * Enter a parse tree produced by {@link SPLGrammarParser#equality}.
	 * @param ctx the parse tree
	 */
	void enterEquality(SPLGrammarParser.EqualityContext ctx);
	/**
	 * Exit a parse tree produced by {@link SPLGrammarParser#equality}.
	 * @param ctx the parse tree
	 */
	void exitEquality(SPLGrammarParser.EqualityContext ctx);
	/**
	 * Enter a parse tree produced by {@link SPLGrammarParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(SPLGrammarParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link SPLGrammarParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(SPLGrammarParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link SPLGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(SPLGrammarParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link SPLGrammarParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(SPLGrammarParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link SPLGrammarParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(SPLGrammarParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link SPLGrammarParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(SPLGrammarParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link SPLGrammarParser#unary}.
	 * @param ctx the parse tree
	 */
	void enterUnary(SPLGrammarParser.UnaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link SPLGrammarParser#unary}.
	 * @param ctx the parse tree
	 */
	void exitUnary(SPLGrammarParser.UnaryContext ctx);
	/**
	 * Enter a parse tree produced by {@link SPLGrammarParser#primary}.
	 * @param ctx the parse tree
	 */
	void enterPrimary(SPLGrammarParser.PrimaryContext ctx);
	/**
	 * Exit a parse tree produced by {@link SPLGrammarParser#primary}.
	 * @param ctx the parse tree
	 */
	void exitPrimary(SPLGrammarParser.PrimaryContext ctx);
}