Implizite Boolean-Konvertierung:
    Implizite Boolean-Konvertierung ist generell nicht erlaubt, um logischen Fehlern bei der Programmierung vorzubeugen (ausversehen falsche Variable eingetragen, die nicht vom Typ boolean ist, wird trotzdem verarbeitet, was dann als logischer Fehler schwieriger zu finden ist). Zudem findet dieses Feature in der Praxis nur selten Verwendung. Zusätzlich können potenzielle Anwendungsfälle leicht anders implementiert werden. Bsp.:
        int x=10; if(x){...}         =>          int x=10; if(x!=0){...}
        (wie in Java: 0=false, sonst true)

    Geplante Fehlermeldung:
    "Fehler: Wert vom Typ boolean erwartet in Zeile XY"

Operator-Überladen:
    Operator-Überladen ist im Allgemeinen nicht erlaubt, weil wir einerseits keine Notwendigkeit dafür sehen und andererseits der Code dadurch leichter zu lesen ist. Zudem ist dies in den meisten anderen Programmiersprachen genauso. Die Konkatenation von Strings mittels + lassen wir allerdings zu, da dies allgemein üblich ist und von uns als nützlich empfunden wird.

    Geplante Fehlermeldung:
    "Fehler: Operator XY erwartet Argumente vom Typ XY/XY"


Neudefinition von Variablen:
    Neudefinition von Variablen lassen wir zu, planen aber eine Warnung auszugeben, um mögliche logische Fehler zu vermeiden (wir wissen nicht, inwiefern wir in diesem Projekt in der Lage sein werden, Warnungen auszugeben, aber das würden wir tun, wenn es möglich ist).

    Geplante Warnung:
    "Warnung: Variable XY wird neudefiniert in Zeile XY"

Shadowing und Scoping:
    Wir überlegen noch.

Uninitialisierte Werte:
    Uninitialisierte Werte lassen wir nicht zu, um auf Variablen, die vergessen wurden zu initialisieren, aufmerksam zu machen. Deklaration und Initialisierung müssen im gleichen Block (siehe Symboltabelle), in der Reihenfolge und vor der ersten Verwendung passieren.

    Geplante Fehlermeldung:
    "Fehler: Variable XY wurde nicht initialisiert und in Zeile XY genutzt"

