// Generated from /vol/fob-vol7/mi21/blumenca/Dokumente/Semester4/CpB/Projekt/src/main/java/org/SPLGrammar/grammar/SPLGrammar.g4 by ANTLR 4.12.0
package org.SPLGrammar.grammar;

import java.util.Objects;


@SuppressWarnings("CheckReturnValue")
/**Diese Klasse fuehrt ein gegebenes Programm anhand vom Parse Tree aus.
 * Dazu wird die methode public void visitProgram(SPLGrammarParser.ProgramContext ctx) aufgerufen.
 * SPLGrammarParser.ProgramContext ist der Syntaxbaum der vom SPLGrammarParser.programm()Parser zurueckgegeben wird.
 * 
 * Die Klasse funktioniert sehr ähnlich zum BaseVisitor. Der Baum wird entlang der Regeln von 
 * oben nach unten abgelaufen. Dabei werden Werte immer als Objekt des Typs SymboltabellenEintrag 
 * hochgereicht, da der Typ oft nicht festgelegt ist. Das hat zum Vorteil das genutzte Variablen 
 * einfach aus der Tabelle referenziert werden koennen. Der Nachteil ist, das hochgereichte Objekte 
 * nicht veraendert werden duerfen, sondern immer neu angelegt werden muessen, da sonst die Werte in 
 * der Tabelle ungewollt veraendert werden.
 * 
 * Diese Klasse geht davon aus, dass das Program/der Baum Fehlerfrei ist. Es werden aslo keine Typen
 * ueberprueft, wenn der Typ implizit gegeben ist, es wird davon ausgegangen das referenzierte Variablen
 * korrekt definiert und initialisiert sind und es findet keine Fehlerabfangung in irgendeiner Form statt.
 * Es sollte also unbedingt vorher die Semantische Pruefung mit dem SPLGrammarBaseVisitor gemacht werden.
 */
public class SPLGrammarBaseInterpreter  {

	Symboltabelle tabelle = new Symboltabelle();//Ein Objekt der Klasse Symboltabelle das zur ausfuehrung genutzt wird

	/**
	 * Diese Methode wird mit dem Vollstaendigen Syntaxbaum aufgerufen und fuehrt dann den gegebenen Baum aus
	 * @param ctx Der Syntaxbaum
	 */
	public void visitProgram(SPLGrammarParser.ProgramContext ctx) {
		 for(SPLGrammarParser.DeclarationContext cctx : ctx.declaration())//Iteriert ueber alle declarations
		 {
			 visitDeclaration(cctx);
		 }
	}

	/** 
	 * Diese methode fuehrt die Regel declaration aus.
	*/
	private void visitDeclaration(SPLGrammarParser.DeclarationContext ctx) {
		 if(ctx.statement()!=null)//Unterscheidet zwischen statement und varDecl
		 {
			 visitStatement(ctx.statement());
		 }
		 else
		 {
			 visitVarDecl(ctx.varDecl());
		 }
	}

	/**
	 * Diese Methode fuehrt die Regel varDecl aus.
	 */
	private void visitVarDecl(SPLGrammarParser.VarDeclContext ctx) {
		 SymboltabellenEintrag eintrag = visitExpression(ctx.expression());		//Ermittle den Wert und Typ von Expression
		 tabelle.addVariable(ctx.IDENTIFIER().getText(),eintrag.getType());		//Generiere neuen Eintrag in der Symboltabelle
		switch (eintrag.getType()) {                                            //ermitteln des richtigen Datentyps
			case 0 -> tabelle.getEintrag(ctx.IDENTIFIER().getText()).setValue(eintrag.getValueB());// Den Wert in der Tabelle setzen x3
			case 1 -> tabelle.getEintrag(ctx.IDENTIFIER().getText()).setValue(eintrag.getValueN());
			case 2 -> tabelle.getEintrag(ctx.IDENTIFIER().getText()).setValue(eintrag.getValueS());
		}
	}

	/**
	 * Diese Methode fuehrt die Regel statement aus.
	 */
	private void visitStatement(SPLGrammarParser.StatementContext ctx) {
		 if(ctx.exprStmt()!=null){visitExprStmt(ctx.exprStmt());}//im Prinzip ein Swich ueber alle moeglichen folge Regeln
		 else if(ctx.ifStmt()!=null){visitIfStmt(ctx.ifStmt());}
		 else if(ctx.printStmt()!=null){visitPrintStmt(ctx.printStmt());}
		 else if(ctx.whileStmt()!=null){visitWhileStmt(ctx.whileStmt());}
		 else if(ctx.block()!=null){{visitBlock(ctx.block());}}
	 }

	 /**
	 * Diese Methode fuehrt die Regel exprStmt aus.
	 */
	private void visitExprStmt(SPLGrammarParser.ExprStmtContext ctx) {
		 visitExpression(ctx.expression());//Diese Regel fuehlt sich ueberfluessig an
	}

	/**
	 * Diese Methode fuehrt die Regel ifStmt aus.
	 */
	private void visitIfStmt(SPLGrammarParser.IfStmtContext ctx) {
		SymboltabellenEintrag eintrag = visitExpression(ctx.expression());//Ermittle den Wert von expression
		if(eintrag.getValueB())//fuehre je nach Bedingung das erste oder zweite Statement aus
		{
			visitStatement(ctx.statement(0));
		}
		else if(ctx.statement().size()>1)
		{
			visitStatement(ctx.statement(1));
		}
	}

	/**
	 * Diese Methode fuehrt die Regel printStmt aus.
	 */
	private void visitPrintStmt(SPLGrammarParser.PrintStmtContext ctx) {
		SymboltabellenEintrag eintrag = visitExpression(ctx.expression());		//Ermittle den Wert von expression
		switch (eintrag.getType()) {                                            //Switch fuer den richtigen Datentyp
			case 0 -> System.out.println(eintrag.getValueB());
			case 1 -> System.out.println(eintrag.getValueN());
			case 2 -> System.out.println(eintrag.getValueS());
		}
	}

	/**
	 * Diese Methode fuehrt die Regel whileStmt aus.
	 */
	private void visitWhileStmt(SPLGrammarParser.WhileStmtContext ctx) {
		while(visitExpression(ctx.expression()).getValueB())//fuer jede iteration wird die Bedingung erneut komplett geprueft
		{
			visitStatement(ctx.statement());
		}
	}

	/**
	 * Diese Methode fuehrt die Regel block aus.
	 */
	private void visitBlock(SPLGrammarParser.BlockContext ctx) {
		tabelle.enterBlock();	//In der Symboltabelle wird ein neuer Block betreten
		for(SPLGrammarParser.DeclarationContext cctx : ctx.declaration())//Iteriert ueber alle declarationen
		{
			visitDeclaration(cctx);
		}
		tabelle.leaveBlock();	// der Block wird am Ende wieder verlassen
	}

	/**
	 * Diese Methode fuehrt die Regel expression aus.
	 */
	private SymboltabellenEintrag visitExpression(SPLGrammarParser.ExpressionContext ctx) {
		 return visitAssignment(ctx.assignment());//trivial
	 }

	/**
	 * Diese Methode fuehrt die Regel assignment aus.
	 */
	private SymboltabellenEintrag visitAssignment(SPLGrammarParser.AssignmentContext ctx) {
		SymboltabellenEintrag eintrag;	//Dummy Object zur Rueckgabe
		 if(ctx.assignment()!=null)//Switch ueber die beiden moeglichen Anwendungen der Regel
		{
			 eintrag = visitAssignment(ctx.assignment());
			switch (eintrag.getType()) {                            //Switch fuer den richtigen Datentyp
				case 0 -> tabelle.getEintrag(ctx.IDENTIFIER().getText()).setValue(eintrag.getValueB());//Weise dem Symboltabelleneintrag den neuen Wert zu x3
				case 1 -> tabelle.getEintrag(ctx.IDENTIFIER().getText()).setValue(eintrag.getValueN());
				case 2 -> tabelle.getEintrag(ctx.IDENTIFIER().getText()).setValue(eintrag.getValueS());
			}
		}
		 else {
			 eintrag = visitLogic_or(ctx.logic_or());//trivial
		 }
		return eintrag;
	}

	/**
	 * Diese Methode fuehrt die Regel logic_or aus.
	 */
	private SymboltabellenEintrag visitLogic_or(SPLGrammarParser.Logic_orContext ctx) {
		 SymboltabellenEintrag eintrag = visitLogic_and(ctx.logic_and(0));//Standardmaessig wird der erste Wert zurueckgegeben, es koennte sich um ein bool, num oder string handeln
		 if(ctx.logic_and().size()>1)										//Es sei denn es gibt mehrere Werte
		 {
			 boolean bool=false;
			 for(SPLGrammarParser.Logic_andContext lactx : ctx.logic_and())	//Iteriere ueber alle Werte und veroder sie
			 {
				 bool = bool || visitLogic_and(lactx).getValueB();
			 }
			 eintrag = new SymboltabellenEintrag(0,"",0);	//generiere neues Object mit dem Ergebnis
			 eintrag.setValue(bool);
		 }
		 return eintrag;
	}
	
	/**
	 * Diese Methode fuehrt die Regel logic_and aus.
	 */
	private SymboltabellenEintrag visitLogic_and(SPLGrammarParser.Logic_andContext ctx) {
		SymboltabellenEintrag eintrag = visitEquality(ctx.equality(0));	//Standardmaessig wird der erste Wert zurueckgegeben, es koennte sich um ein bool, num oder string handeln
		if(ctx.equality().size()>1)											//Es sei denn es gibt mehrere Werte
		{
			boolean bool=true;
			for(SPLGrammarParser.EqualityContext ectx : ctx.equality())		//Iteriere ueber alle Werte und verunde sie
			{
				bool = bool && visitEquality(ectx).getValueB();
			}
			eintrag = new SymboltabellenEintrag(0,"",0);	//generiere neues Object mit dem Ergebnis
			eintrag.setValue(bool);
		}
		return eintrag;
	 }

	/**
	* Diese Methode fuehrt die Regel equality aus.
	*/
	private SymboltabellenEintrag visitEquality(SPLGrammarParser.EqualityContext ctx) {
		SymboltabellenEintrag eintrag = visitComparison(ctx.comparison(0));	//Standardmaessig wird der erste Wert zurueckgegeben, es koennte sich um ein bool, num oder string handeln
		if(ctx.comparison().size()>1)											//Es sei denn es gibt mehrere Werte
		{
			boolean bool=true;
			int type= eintrag.getType();										//Ermittle den Typ
			for(int i=0;i<ctx.comparison().size()-1;i++)						//Iteriere ueber alle 
			{
				if(Objects.equals(ctx.children.get(i * 2 + 1).getText(), "=="))	//Switch ueber die Operatoren == und !=
					switch (type) {												//Switch ueber den typ
						case 0 -> bool = bool && (visitComparison(ctx.comparison(i)).getValueB() == visitComparison(ctx.comparison(i + 1)).getValueB());	//verundung der werte x6
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
			eintrag = new SymboltabellenEintrag(0,"",0);//neues Objekt mit Typ boolean und ermitteltem Wert zurueckgeben
			eintrag.setValue(bool);
		}
		return eintrag;
	 }

	/**
	 * Diese Methode fuehrt die Regel comparison aus.
	 */
	private SymboltabellenEintrag visitComparison(SPLGrammarParser.ComparisonContext ctx) {
		 SymboltabellenEintrag eintrag = visitTerm(ctx.term(0));			//Standardmaessig wird der erste Wert zurueckgegeben, es koennte sich um ein bool, num oder string handeln
		 if(ctx.term().size()>1)											//Es sei denn es gibt mehrere Werte
		 {
			 boolean bool=true;
			 for(int i=0;i<ctx.term().size()-1;i++)							//Iteriere ueber alle terme
			 {
				 switch (ctx.children.get(i * 2 + 1).getText()) {			//Switch ueber die Operatoren
					 case ">" -> bool = bool && (visitTerm(ctx.term(i)).getValueN() > visitTerm(ctx.term(i + 1)).getValueN());		//Verundung der Ergebnisse x4
					 case ">=" -> bool = bool && (visitTerm(ctx.term(i)).getValueN() >= visitTerm(ctx.term(i + 1)).getValueN());
					 case "<" -> bool = bool && (visitTerm(ctx.term(i)).getValueN() < visitTerm(ctx.term(i + 1)).getValueN());
					 case "<=" -> bool = bool && (visitTerm(ctx.term(i)).getValueN() <= visitTerm(ctx.term(i + 1)).getValueN());
				 }
			 }
			 eintrag = new SymboltabellenEintrag(0,"",0);//neues Objekt mit Typ boolean und ermitteltem Wert zurueckgeben
			 eintrag.setValue(bool);
		 }
		 return eintrag;
	 }

	/**
	* Diese Methode fuehrt die Regel term aus.
	*/
	private SymboltabellenEintrag visitTerm(SPLGrammarParser.TermContext ctx){
		 SymboltabellenEintrag eintrag = visitFactor(ctx.factor(0));	//Standardmaessig wird der erste Wert zurueckgegeben, es koennte sich um ein bool, num oder string handeln
		 if(ctx.factor().size()>1)										//Es sei denn es gibt mehrere Werte
		 {
			 int type= eintrag.getType();								//Typ ermitteln
			 double num=visitFactor(ctx.factor(0)).getValueN();								//Variable mit dem jeweiligen Wert von factor[0] x2
			 StringBuilder str= new StringBuilder(visitFactor(ctx.factor(0)).getValueS());	
			 for(int i=1;i<ctx.factor().size();i++)												//Iteriere ueber factor[1] bis [n]
			 {
				 if(Objects.equals(ctx.children.get(i * 2 - 1).getText(), "+"))					//Switch ueber Operatoren + und -
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
					 eintrag = new SymboltabellenEintrag(0, "", 1);//neues Objekt mit Typ num und ermitteltem Wert zurueckgeben
					 eintrag.setValue(num);
				 }
				 case 2 -> {
					 eintrag = new SymboltabellenEintrag(0, "", 2);//neues Objekt mit Typ String und ermitteltem Wert zurueckgeben
					 eintrag.setValue(str.toString());
				 }
			 }
		 }
		 return eintrag;
	 }
	
	/**
	* Diese Methode fuehrt die Regel factor aus.
	*/
	private SymboltabellenEintrag visitFactor(SPLGrammarParser.FactorContext ctx){
		SymboltabellenEintrag eintrag = visitUnary(ctx.unary(0));					//Standardmaessig wird der erste Wert zurueckgegeben, es koennte sich um ein bool, num oder string handeln
		if(ctx.unary().size()>1)													//Es sei denn es gibt mehrere Werte
		{
			double num=visitUnary(ctx.unary(0)).getValueN();						//Variable mit dem Wert von unary[0]
			for(int i=1;i<ctx.unary().size();i++)									//Iteriere ueber unary[1] bis [n]
			{
				if(Objects.equals(ctx.children.get(i * 2 - 1).getText(), "*"))		//Switch ueber Operator * und /
						num *= visitUnary(ctx.unary(i)).getValueN();
				else{
					num /= visitUnary(ctx.unary(i)).getValueN();
				}
			}
			eintrag = new SymboltabellenEintrag(0, "", 1);			//neues Objekt mit Typ num und ermitteltem Wert zurueckgeben
			eintrag.setValue(num);
		}
		return eintrag;
	}
	
	/**
	 * Diese Methode fuehrt die Regel unary aus.
	 */
	private SymboltabellenEintrag visitUnary(SPLGrammarParser.UnaryContext ctx){
		SymboltabellenEintrag eintrag = visitPrimary(ctx.primary());			//Standardmaessig wird der erste Wert zurueckgegeben, es koennte sich um ein bool, num oder string handeln

		if(ctx.NOT()!=null)														//Es sei denn es ist ein negtionsoperator vorahnden
		{
			boolean value = !eintrag.getValueB();
			eintrag = new SymboltabellenEintrag(0,"",0);		//neues Objekt mit Typ bool und negiertem Wert zurueckgeben
			eintrag.setValue(value);
		}
		if(ctx.MINUS()!=null)													//analog
		{
			double value = -eintrag.getValueN();
			eintrag = new SymboltabellenEintrag(0,"",1);
			eintrag.setValue(value);
		}
		return eintrag;
	}

	/**
	 * Diese Methode fuehrt die Regel primary aus.
	 */
	private SymboltabellenEintrag visitPrimary(SPLGrammarParser.PrimaryContext ctx){
		 SymboltabellenEintrag eintrag;										//Dummy object zum zurueckgeben
		 if(ctx.expression()!=null)											//Switch ueber die moeglichen Anwendungen der Regel
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
			 String str= ctx.STRING().getText();
			 str=str.substring(1,str.length()-1);							//Abschneiden der " Am Anfang und Ende
			 eintrag.setValue(str);
		 }
		 else
		 {
			 eintrag = new SymboltabellenEintrag(0,"",-1);
		 }
		 return eintrag;
	}


}