grammar SPLGrammar;




TRUE:   'true';
FALSE:  'false';
AND:    'and';
OR:     'or';
VAR:    'var';
PRINT:  'print';
IF:     'if';
ELSE:   'else';
WHILE:  'while';

SMCLN:  ';';
LPAR:   '(';
RPAR:   ')';
LBRC:   '{';
RBRC:   '}';

STRING:  ["]~["]*["];
NUMBER: [0-9]+('.'[0-9]+)?;

IDENTIFIER: [A-Za-z][A-Za-z0-9]*;

PLUS:       '+';
MINUS:      '-';
ASTERISK:   '*';
SLASH:      '/';
ASSIGN:     '=';
EQL:        '==';
NEQ:        '!=';
GRT:        '>';
LSS:        '<';
GEQ:        '>=';
LEQ:        '<=';
NOT:        '!';


COMMENT:    '//' ~[\n|\r]* -> skip;
WS:         (' '|'\t'|'\r'|'\n') -> skip;














program:        declaration* EOF
            ;

declaration:    varDecl
            |   statement
            ;
varDecl:        VAR IDENTIFIER (ASSIGN expression)? SMCLN
            ;
statement:      exprStmt
            |   ifStmt
            |   printStmt
            |   whileStmt
            |   block
            ;
exprStmt:       expression SMCLN
            ;
ifStmt:         IF LPAR expression RPAR statement (ELSE statement)?
            ;
printStmt:      PRINT expression SMCLN
            ;
whileStmt:      WHILE LPAR expression RPAR statement
            ;
block:          LBRC declaration* RBRC
            ;
expression:     assignment
            ;
assignment:     IDENTIFIER ASSIGN assignment
            |   logic_or
            ;
logic_or:       logic_and (OR logic_and)*
            ;
logic_and:      equality (AND equality)*
            ;
equality:       comparison((NEQ | EQL) comparison)*
            ;
comparison:     term ((GRT | GEQ | LSS | LEQ) term)*
            ;
term:           factor ((MINUS | PLUS) factor)*
            ;
factor:         unary ((SLASH | ASTERISK) unary)*
            ;
unary:          (NOT | MINUS) primary
            |   primary
            ;
primary:        TRUE | FALSE | NUMBER | STRING
            |   LPAR expression RPAR
            | IDENTIFIER
            ;