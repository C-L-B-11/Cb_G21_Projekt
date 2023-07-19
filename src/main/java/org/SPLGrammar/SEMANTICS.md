Implizite Boolean-Konvertierung:
    Implizite Boolean-Konvertierung ist generell nicht erlaubt, um logischen Fehlern bei der Programmierung vorzubeugen (aus Versehen falsche Variable eingetragen, die nicht vom Typ Boolean ist, wird trotzdem verarbeitet, was dann als logischer Fehler schwieriger zu finden ist). Zudem findet dieses Feature in der Praxis nur selten Verwendung. Zusätzlich können potenzielle Anwendungsfälle leicht anders implementiert werden. Bsp.:
        int x=10; if(x){...}         =>          int x=10; if(x!=0){...}
        (wie in Java: 0=false, sonst true)

    Geplante Fehlermeldung:
    "Error: Expression is not a bool in Line: X"

Operator-Überladen:
    Operator-Überladen ist im Allgemeinen nicht erlaubt, weil wir einerseits keine Notwendigkeit dafür sehen und andererseits der Code dadurch leichter zu lesen ist. Zudem ist dies in den meisten anderen Programmiersprachen genauso. Die Konkatenation von Strings mittels + lassen wir allerdings zu, da dies allgemein üblich ist und von uns als nützlich empfunden wird.
    Erlaubt sind also:

    Num + Num = Addition (Num)
    Str + Str = Konkatination (Str)

    Num - Num = Subtraktion (Num)

    Num * Num = Multiplikation (Num)

    Num / Num = Division (Num)

    Num > Num = Greater than (Bool)

    Num >= Num = Greater or Equal to (Bool)

    Num < Num = Less than (Bool)

    Num <= Num = Less or Equal to (Bool)

    Num == Num = Equal to (Bool)
    Bool == Bool = Equal to (Bool)
    Str == Str = Equal to (Bool)

    Num != Num = Not Equal to (Bool)
    Bool != Bool = Not Equal to (Bool)
    Str != Str = Not Equal to (Bool)
    
    -Num = Negation (Num)

    !Bool = Komplement (Bool)

    Bool and Bool = Logic And (Bool)

    Bool or Bool = Logic OR (Bool)

    Geplante Fehlermeldung:
    Error: Variable X expects type Y in Line: Z"


Neudefinition von Variablen:
    Neudefinition von Variablen lassen wir zu, planen aber eine Warnung auszugeben, um mögliche logische Fehler zu vermeiden.

    Geplante Warnung:
    "Warning: Variable X was defined before (Shadowing/Neudefinition) in Line: Y"

Shadowing und Scoping:
    Eine Variable ist ab der Definition im eigenen und allen untergeordneten Blöcken lesbar. Wenn eine Variable in einem untergeordnetem Block neu definiert wird, so wird der alte Wert nicht gelöscht, sondern tritt wieder in Kraft sobald der Block der Neudefinition verlassen wurde.

Uninitialisierte Werte:
    Uninitialisierte Werte lassen wir nicht zu, um auf Variablen, die vergessen wurden zu initialisieren, aufmerksam zu machen. Deklaration und Initialisierung müssen im gleichen Block (siehe Symboltabelle), in der Reihenfolge und vor der ersten Verwendung passieren.

    Geplante Fehlermeldung:
    "Error: Variable X is not beeing initalized in Line: Y"

