package org.SPLGrammar.grammar;


/**
 * Diese Klasse verwaltet den einen Eintrag in der Symboltabelle, in unserm Fall nur Variablen
 * 
 * Attribut block die Blocknummer der Variable
 * Attribut type der Typ der Variable wobei 0 <=> bool, 1 <=> int, 2 <=> String
 * Attribut name der Name der Variable
 * 
 * Attribut bool der boolsche Wert der Variable nur nutzbar wenn typ = 0
 * Attribut num der numerische Wert der Variable nur nutzbar wenn typ = 1
 * Attribut str der String-Wert der Variable nur nutzbar wenn typ = 2
 */
public class SymboltabellenEintrag {
    private final int block;
    private final int type; // 0=bool, 1=int,2=String
    private final String name;

    private boolean bool=false;
    private double num=0;
    private String str="";
    /**
     * Erzeugt einen Eintrag ohne spezifizierten Wert 
     */
    public SymboltabellenEintrag(int block,String name, int type){
        this.block=block;
        this.name=name;
        this.type=type;
    }

    public int getBlock(){return block;}

    public int getType() {return type;}

    public String getName() {return name;}

    public void setValue(double x){if(type==1)num=x; } //typkonformitaet

    public void setValue(boolean x){if(type==0)bool=x; } //typkonformitaet

    public void setValue(String x){if(type==2)str=x; } //typkonformitaet

    public double getValueN(){return num;}

    public boolean getValueB(){return bool;}

    public String getValueS(){return str;}

    /**
     * Konvertiert Objekt in einen String der Form (block, name, typ, wert) entsprechend des gewaehlten Typs
     * wird der konforme Wert ermittelt
     * 
     * @return Stringrepraesentation des Objekts
     */
    @Override
    public String toString() {
        String str2= switch (type) {
            case 0 -> Boolean.toString(bool);
            case 1 -> Double.toString(num);
            case 2 -> str;
            default -> "No Value";
        };
        return "(Block: " + block + " Name: " + name + " Type: " + type + " Value: "+ str2 +")\n";
    }
}
