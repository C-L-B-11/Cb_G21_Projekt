// Generated from /vol/fob-vol7/mi21/blumenca/Dokumente/Semester4/CpB/Projekt/src/main/java/org/SPLGrammar/grammar/SPLGrammar.g4 by ANTLR 4.12.0
package org.SPLGrammar.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link SPLGrammarParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface SPLGrammarVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link SPLGrammarParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(SPLGrammarParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link SPLGrammarParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(SPLGrammarParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link SPLGrammarParser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(SPLGrammarParser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link SPLGrammarParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(SPLGrammarParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link SPLGrammarParser#exprStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprStmt(SPLGrammarParser.ExprStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SPLGrammarParser#ifStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStmt(SPLGrammarParser.IfStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SPLGrammarParser#printStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintStmt(SPLGrammarParser.PrintStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SPLGrammarParser#whileStmt}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStmt(SPLGrammarParser.WhileStmtContext ctx);
	/**
	 * Visit a parse tree produced by {@link SPLGrammarParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(SPLGrammarParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link SPLGrammarParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(SPLGrammarParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link SPLGrammarParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(SPLGrammarParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link SPLGrammarParser#logic_or}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogic_or(SPLGrammarParser.Logic_orContext ctx);
	/**
	 * Visit a parse tree produced by {@link SPLGrammarParser#logic_and}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogic_and(SPLGrammarParser.Logic_andContext ctx);
	/**
	 * Visit a parse tree produced by {@link SPLGrammarParser#equality}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEquality(SPLGrammarParser.EqualityContext ctx);
	/**
	 * Visit a parse tree produced by {@link SPLGrammarParser#comparison}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComparison(SPLGrammarParser.ComparisonContext ctx);
	/**
	 * Visit a parse tree produced by {@link SPLGrammarParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(SPLGrammarParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link SPLGrammarParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(SPLGrammarParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link SPLGrammarParser#unary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnary(SPLGrammarParser.UnaryContext ctx);
	/**
	 * Visit a parse tree produced by {@link SPLGrammarParser#primary}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimary(SPLGrammarParser.PrimaryContext ctx);
}