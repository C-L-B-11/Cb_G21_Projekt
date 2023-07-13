// Generated from /vol/fob-vol7/mi21/blumenca/Dokumente/Semester4/CpB/Projekt/src/main/java/org/SPLGrammar/grammar/SPLGrammar.g4 by ANTLR 4.12.0
package org.SPLGrammar.grammar;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;


/**
 * This class provides an empty implementation of {@link SPLGrammarVisitor},
 * which can be extended to create a visitor which only needs to handle a subset
 * of the available methods.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
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
		if(!parsedSuccessful)
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
		//System.out.println(ctx.expression().getText());
		if(ctx.ASSIGN()==null)
		{
			errors += ("Error: Variable " + ctx.IDENTIFIER().getText() + " is not beeing initalized" +" in Line: " + ctx.getStart().getLine());
			errors += "\n";
			parsedSuccessful=false;

		}
		int type=getType(ctx.expression());
		if(type!=-1) {
			if (tabelle.addVariable(ctx.IDENTIFIER().getText(), type) == -1) {
				System.out.println("Warning: Variable " + ctx.IDENTIFIER().getText() + " was defined before (Shadowing/Neudefinition)"+" in Line: " + ctx.getStart().getLine());
			}
		}
		else
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
		if(getTypeIF(ctx.expression())!=0)
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
		if(getTypeIF(ctx.expression())!=0)
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
	@Override public T visitBlock(SPLGrammarParser.BlockContext ctx) {
		tabelle.enterBlock();
		T ret =visitChildren(ctx);
		//System.out.println(tabelle);
		tabelle.leaveBlock();
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
			if (type == -1) {
				errors += ("Error: Can not find Variable " + ctx.IDENTIFIER().getText() + " (Scoping?)"+" in Line: " + ctx.getStart().getLine());
				errors += "\n";
				parsedSuccessful=false;
			} else if (type != getType(ctx.assignment()))
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
			if (tabelle.getEintrag(ctx.IDENTIFIER().getText()).getType() == -1) {
				errors += ("Errorr: Can not find Variable " + ctx.IDENTIFIER().getText() + " (Scoping?)" +" in Line: " + ctx.getStart().getLine());
				errors += "\n";
				parsedSuccessful=false;
			}
		}
		return visitChildren(ctx);
	}

	private int getType(SPLGrammarParser.ExpressionContext ctx)		//-1:Error, 0:bool, 1:int, 2:String
	{
		return getType(ctx.assignment());
	}
	private int getTypeIF(SPLGrammarParser.ExpressionContext ctx)		//-1:Error, 0:bool, 1:int, 2:String
	{
		return getTypeIF(ctx.assignment());
	}
	private int getType(SPLGrammarParser.AssignmentContext ctx)
	{
		if(ctx.logic_or()==null)
		{
			return getType(ctx.assignment());
		}
		else
		{
			return getType(ctx.logic_or());
		}
	}
	private int getTypeIF(SPLGrammarParser.AssignmentContext ctx)
	{
		if(ctx.logic_or()==null)
		{
			return -1;
		}
		else
		{
			return getType(ctx.logic_or());
		}
	}
	private int getType(SPLGrammarParser.Logic_orContext ctx)
	{
		if(ctx.logic_and().size()>1)
		{
			for(SPLGrammarParser.Logic_andContext lactx : ctx.logic_and())
			{
				if(getType(lactx)!=0)
				{return -1;}
			}
			return 0;
		}
		else
		{
			return getType(ctx.logic_and(0));
		}
	}
	private int getType(SPLGrammarParser.Logic_andContext ctx)
	{
		if(ctx.equality().size()>1)
		{
			for(SPLGrammarParser.EqualityContext ectx : ctx.equality())
			{
				if(getType(ectx)!=0)
				{return -1;}
			}
			return 0;
		}
		else
		{
			return getType(ctx.equality(0));
		}
	}
	private int getType(SPLGrammarParser.EqualityContext ctx)
	{
		if(ctx.comparison().size()>1)
		{
			int type = getType(ctx.comparison(0));
			for(SPLGrammarParser.ComparisonContext cctx : ctx.comparison())
			{
				if(getType(cctx)!= type)
				{return -1;}
			}
				return 0;
		}
		else
		{
			return getType(ctx.comparison(0));
		}
	}
	private int getType(SPLGrammarParser.ComparisonContext ctx)
	{
		if(ctx.term().size()>1)
		{

			for(SPLGrammarParser.TermContext tctx : ctx.term())
			{
				if(getType(tctx)!=1)
				{return -1;}
			}
			return 0;
		}
		else
		{

			return getType(ctx.term(0));
		}
	}
	private int getType(SPLGrammarParser.TermContext ctx)
	{
		if(ctx.factor().size()>1)
		{
			int type = getType(ctx.factor(0));
			for(SPLGrammarParser.FactorContext fctx : ctx.factor())
			{
				if(getType(fctx)!= type)
				{return -1;}
			}
			if(type==0)
			{return -1;}
			else if(type==2&&ctx.PLUS().size()!=ctx.factor().size()-1)
			{
				return -1;
			}
			else
			return type;
		}
		else
		{
			return getType(ctx.factor(0));
		}
	}
	private int getType(SPLGrammarParser.FactorContext ctx)
	{
		if(ctx.unary().size()>1)
		{
			for(SPLGrammarParser.UnaryContext uctx : ctx.unary())
			{
				if(getType(uctx)!=1)
				{return -1;}
			}
			return 1;
		}
		else
		{
			return getType(ctx.unary(0));
		}
	}
	private int getType(SPLGrammarParser.UnaryContext ctx)
	{
		if(ctx.NOT()!=null)
		{
			if(getType(ctx.primary())==0)
			{return 0;}
			else
			{return -1;}
		}
		if(ctx.MINUS()!=null)
		{
			if(getType(ctx.primary())==1)
			{return 1;}
			else
			{return -1;}
		}
		return getType(ctx.primary());
	}
	private int getType(SPLGrammarParser.PrimaryContext ctx)
	{
		if(ctx.TRUE()!=null||ctx.FALSE()!=null)
		{return 0;}
		if(ctx.NUMBER()!=null)
		{return 1;}
		if(ctx.STRING()!=null)
		{return 2;}
		if(ctx.IDENTIFIER()!=null)
		{return tabelle.getEintrag(ctx.IDENTIFIER().getText()).getType();}
		if(ctx.expression()!=null)
		{return getTypeIF(ctx.expression());}
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