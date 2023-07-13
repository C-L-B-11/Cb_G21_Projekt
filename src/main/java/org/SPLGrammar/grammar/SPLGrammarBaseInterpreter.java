// Generated from /vol/fob-vol7/mi21/blumenca/Dokumente/Semester4/CpB/Projekt/src/main/java/org/SPLGrammar/grammar/SPLGrammar.g4 by ANTLR 4.12.0
package org.SPLGrammar.grammar;

import java.util.Objects;


@SuppressWarnings("CheckReturnValue")
public class SPLGrammarBaseInterpreter  {

	Symboltabelle tabelle = new Symboltabelle();
	 public void visitProgram(SPLGrammarParser.ProgramContext ctx) {
		 for(SPLGrammarParser.DeclarationContext cctx : ctx.declaration())
		 {
			 visitDeclaration(cctx);
		 }
		 //System.out.println(tabelle);
	}
	public void visitDeclaration(SPLGrammarParser.DeclarationContext ctx) {
		 if(ctx.statement()!=null)
		 {
			 visitStatement(ctx.statement());
		 }
		 else
		 {
			 visitVarDecl(ctx.varDecl());
		 }
	}

	public void visitVarDecl(SPLGrammarParser.VarDeclContext ctx) {
		 SymboltabellenEintrag eintrag = visitExpression(ctx.expression());		//Ermittle den Wert und Typ von Expression
		 tabelle.addVariable(ctx.IDENTIFIER().getText(),eintrag.getType());		//Generiere neuen Eintrag in der Symboltabelle
		switch (eintrag.getType()) {                                            //ermitteln des richtigen Datentyps
			case 0 -> tabelle.getEintrag(ctx.IDENTIFIER().getText()).setValue(eintrag.getValueB());// Den Wert in der Tabelle setzen x3
			case 1 -> tabelle.getEintrag(ctx.IDENTIFIER().getText()).setValue(eintrag.getValueN());
			case 2 -> tabelle.getEintrag(ctx.IDENTIFIER().getText()).setValue(eintrag.getValueS());
		}
	}

	 public void visitStatement(SPLGrammarParser.StatementContext ctx) {
		 if(ctx.exprStmt()!=null){visitExprStmt(ctx.exprStmt());}
		 else if(ctx.ifStmt()!=null){visitIfStmt(ctx.ifStmt());}
		 else if(ctx.printStmt()!=null){visitPrintStmt(ctx.printStmt());}
		 else if(ctx.whileStmt()!=null){visitWhileStmt(ctx.whileStmt());}
		 else if(ctx.block()!=null){{visitBlock(ctx.block());}}
	 }
	public void visitExprStmt(SPLGrammarParser.ExprStmtContext ctx) {
		 visitExpression(ctx.expression());
	}
	public void visitIfStmt(SPLGrammarParser.IfStmtContext ctx) {
		SymboltabellenEintrag eintrag = visitExpression(ctx.expression());
		if(eintrag.getValueB())
		{
			visitStatement(ctx.statement(0));
		}
		else if(ctx.statement().size()>1)
		{
			visitStatement(ctx.statement(1));
		}
	}
	public void visitPrintStmt(SPLGrammarParser.PrintStmtContext ctx) {
		SymboltabellenEintrag eintrag = visitExpression(ctx.expression());
		switch (eintrag.getType()) {                                            //ermitteln des richtigen Datentyps
			case 0 -> System.out.println(eintrag.getValueB());
			case 1 -> System.out.println(eintrag.getValueN());
			case 2 -> System.out.println(eintrag.getValueS());
		}
	}
	public void visitWhileStmt(SPLGrammarParser.WhileStmtContext ctx) {
		while(visitExpression(ctx.expression()).getValueB())
		{
			visitStatement(ctx.statement());
		}
	}
	public void visitBlock(SPLGrammarParser.BlockContext ctx) {
		tabelle.enterBlock();
		for(SPLGrammarParser.DeclarationContext cctx : ctx.declaration())
		{
			visitDeclaration(cctx);
		}
		tabelle.leaveBlock();
	}

	 public SymboltabellenEintrag visitExpression(SPLGrammarParser.ExpressionContext ctx) {
		 return visitAssignment(ctx.assignment());
	 }
	public SymboltabellenEintrag visitAssignment(SPLGrammarParser.AssignmentContext ctx) {
		SymboltabellenEintrag eintrag;
		 if(ctx.assignment()!=null)
		{
			 eintrag = visitAssignment(ctx.assignment());
			switch (eintrag.getType()) {                            //ermitteln des richtigen Datentyps
				case 0 -> tabelle.getEintrag(ctx.IDENTIFIER().getText()).setValue(eintrag.getValueB());
				case 1 -> tabelle.getEintrag(ctx.IDENTIFIER().getText()).setValue(eintrag.getValueN());
				case 2 -> tabelle.getEintrag(ctx.IDENTIFIER().getText()).setValue(eintrag.getValueS());
			}
		}
		 else {
			 eintrag = visitLogic_or(ctx.logic_or());
		 }
		return eintrag;
	}
	public SymboltabellenEintrag visitLogic_or(SPLGrammarParser.Logic_orContext ctx) {
		 SymboltabellenEintrag eintrag = visitLogic_and(ctx.logic_and(0));
		 if(ctx.logic_and().size()>1)
		 {
			 boolean bool=false;
			 for(SPLGrammarParser.Logic_andContext lactx : ctx.logic_and())
			 {
				 bool = bool || visitLogic_and(lactx).getValueB();
			 }
			 eintrag = new SymboltabellenEintrag(0,"",0);
			 eintrag.setValue(bool);
		 }
		 return eintrag;
	}
	public SymboltabellenEintrag visitLogic_and(SPLGrammarParser.Logic_andContext ctx) {
		SymboltabellenEintrag eintrag = visitEquality(ctx.equality(0));
		if(ctx.equality().size()>1)
		{
			boolean bool=true;
			for(SPLGrammarParser.EqualityContext ectx : ctx.equality())
			{
				bool = bool && visitEquality(ectx).getValueB();
			}
			eintrag = new SymboltabellenEintrag(0,"",0);
			eintrag.setValue(bool);
		}
		return eintrag;
	 }
	public SymboltabellenEintrag visitEquality(SPLGrammarParser.EqualityContext ctx) {
		SymboltabellenEintrag eintrag = visitComparison(ctx.comparison(0));
		if(ctx.comparison().size()>1)
		{
			boolean bool=true;
			int type= eintrag.getType();
			for(int i=0;i<ctx.comparison().size()-1;i++)
			{
				if(Objects.equals(ctx.children.get(i * 2 + 1).getText(), "=="))
					switch (type) {
						case 0 -> bool = bool && (visitComparison(ctx.comparison(i)).getValueB() == visitComparison(ctx.comparison(i + 1)).getValueB());
						case 1 -> bool = bool && (visitComparison(ctx.comparison(i)).getValueN() == visitComparison(ctx.comparison(i + 1)).getValueN());
						case 2 -> bool = bool && (Objects.equals(visitComparison(ctx.comparison(i)).getValueS(), visitComparison(ctx.comparison(i + 1)).getValueS()));
					}
				else{
					bool = switch (type) {
						case 0 -> bool && (visitComparison(ctx.comparison(i)).getValueB() != visitComparison(ctx.comparison(i + 1)).getValueB());
						case 1 -> bool && (visitComparison(ctx.comparison(i)).getValueN() != visitComparison(ctx.comparison(i + 1)).getValueN());
						case 2 -> bool && (!Objects.equals(visitComparison(ctx.comparison(i)).getValueS(), visitComparison(ctx.comparison(i + 1)).getValueS()));
						default -> throw new IllegalStateException("Unexpected value: " + type);
					};
				}
			}
			eintrag = new SymboltabellenEintrag(0,"",0);
			eintrag.setValue(bool);
		}
		return eintrag;
	 }
	 public SymboltabellenEintrag visitComparison(SPLGrammarParser.ComparisonContext ctx) {
		 SymboltabellenEintrag eintrag = visitTerm(ctx.term(0));
		 if(ctx.term().size()>1)
		 {
			 boolean bool=true;
			 for(int i=0;i<ctx.term().size()-1;i++)
			 {
				 switch (ctx.children.get(i * 2 + 1).getText()) {
					 case ">" -> bool = bool && (visitTerm(ctx.term(i)).getValueN() > visitTerm(ctx.term(i + 1)).getValueN());
					 case ">=" -> bool = bool && (visitTerm(ctx.term(i)).getValueN() >= visitTerm(ctx.term(i + 1)).getValueN());
					 case "<" -> bool = bool && (visitTerm(ctx.term(i)).getValueN() < visitTerm(ctx.term(i + 1)).getValueN());
					 case "<=" -> bool = bool && (visitTerm(ctx.term(i)).getValueN() <= visitTerm(ctx.term(i + 1)).getValueN());
				 }
			 }
			 eintrag = new SymboltabellenEintrag(0,"",0);
			 eintrag.setValue(bool);
		 }
		 return eintrag;
	 }
	 public SymboltabellenEintrag visitTerm(SPLGrammarParser.TermContext ctx){
		 SymboltabellenEintrag eintrag = visitFactor(ctx.factor(0));
		 if(ctx.factor().size()>1)
		 {
			 int type= eintrag.getType();
			 double num=visitFactor(ctx.factor(0)).getValueN();
			 StringBuilder str= new StringBuilder(visitFactor(ctx.factor(0)).getValueS());
			 for(int i=1;i<ctx.factor().size();i++)
			 {
				 if(Objects.equals(ctx.children.get(i * 2 - 1).getText(), "+"))
					 switch (type) {
						 case 1 -> num += visitFactor(ctx.factor(i)).getValueN();
						 case 2 -> str.append(visitFactor(ctx.factor(i)).getValueS());
					 }
				 else{
					 num -= visitFactor(ctx.factor(i)).getValueN();
				 }
			 }
			 switch (type) {
				 case 1 -> {
					 eintrag = new SymboltabellenEintrag(0, "", 1);
					 eintrag.setValue(num);
				 }
				 case 2 -> {
					 eintrag = new SymboltabellenEintrag(0, "", 2);
					 eintrag.setValue(str.toString());
				 }
			 }
		 }
		 return eintrag;
	 }
	public SymboltabellenEintrag visitFactor(SPLGrammarParser.FactorContext ctx){
		SymboltabellenEintrag eintrag = visitUnary(ctx.unary(0));
		if(ctx.unary().size()>1)
		{
			double num=visitUnary(ctx.unary(0)).getValueN();
			for(int i=1;i<ctx.unary().size();i++)
			{
				if(Objects.equals(ctx.children.get(i * 2 - 1).getText(), "*"))
						num *= visitUnary(ctx.unary(i)).getValueN();
				else{
					num /= visitUnary(ctx.unary(i)).getValueN();
				}
			}
			eintrag = new SymboltabellenEintrag(0, "", 1);
			eintrag.setValue(num);
		}
		return eintrag;
	}
	public SymboltabellenEintrag visitUnary(SPLGrammarParser.UnaryContext ctx){
		SymboltabellenEintrag eintrag = visitPrimary(ctx.primary());

		if(ctx.NOT()!=null)
		{
			boolean value = !eintrag.getValueB();
			eintrag = new SymboltabellenEintrag(0,"",0);
			eintrag.setValue(value);
		}
		if(ctx.MINUS()!=null)
		{
			double value = -eintrag.getValueN();
			eintrag = new SymboltabellenEintrag(0,"",1);
			eintrag.setValue(value);
		}
		return eintrag;
	}
	public SymboltabellenEintrag visitPrimary(SPLGrammarParser.PrimaryContext ctx){
		 SymboltabellenEintrag eintrag;
		 if(ctx.expression()!=null)
		 {
			 eintrag = visitExpression(ctx.expression());
		 }
		 else if(ctx.IDENTIFIER()!=null)
		 {
			 eintrag =tabelle.getEintrag(ctx.IDENTIFIER().getText());
		 }
		 else if(ctx.TRUE()!=null)
		 {
			 eintrag = new SymboltabellenEintrag(0,"",0);
			 eintrag.setValue(true);
		 }
		 else if(ctx.FALSE()!=null)
		 {
			 eintrag = new SymboltabellenEintrag(0,"",0);
			 eintrag.setValue(false);
		 }
		 else if(ctx.NUMBER()!=null)
		 {
			 eintrag = new SymboltabellenEintrag(0,"",1);
			 eintrag.setValue(Double.parseDouble(ctx.NUMBER().getText()));
		 }
		 else if(ctx.STRING()!=null)
		 {
			 eintrag = new SymboltabellenEintrag(0,"",2);
			 eintrag.setValue(ctx.STRING().getText());
		 }
		 else
		 {
			 eintrag = new SymboltabellenEintrag(0,"",-1);
		 }
		 return eintrag;
	}


}