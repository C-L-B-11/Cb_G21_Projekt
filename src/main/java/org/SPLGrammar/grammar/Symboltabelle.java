package org.SPLGrammar.grammar;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Repraesentiert die Symboltabelle in der wir nur Variablen (auch mit Wert) speichern koennen
 * Tabelle wird von Oben nach Unten aufgebaut
 * Achtung: Bloecke werten beim Verlassen geloescht
 *
 * Attribut Tabelle liste mit Symboltabelleneintraegen
 * Attribut block Nummer des Aktuellen Blocks
 */
public class Symboltabelle {
    private final List<SymboltabellenEintrag> Tabelle = new ArrayList<>();
    private int block =0;

    public Symboltabelle(){}

    /**
     * Fuegt eine Variable in die Symboltabelle im aktuellen Block ganz Unten ein
     * 
     * @param name Variablenname
     * @param type Variablentyp siehe @link Symboltabelleneintrag
     * 
     * @return -1 <=> fehler beim einfuegen und 0 <=> erfolgreiches einfuegen
     */
    public int addVariable(String name, int type)
    {
        int ret = getEintrag(name).getType();    //ret ist -1 wenn die Variable noch nicht definiert war. Sonst 0,1,2
        if(ret>0)ret=0;             //ret ist -1 wenn die Variable noch nicht definiert war. Sonst 0
        Tabelle.add(new SymboltabellenEintrag(block,name,type));
        return -1-ret;              //wenn die Variable schon definiert war wird -1 zurueckgegeben. sonst 0
    }

    /**
     * Betritt neuen Block
     */
    public void enterBlock(){
        block++;
    }

    /**
     * Verlaesst aktuellen Block
     * Weil wir faul sind halten wir die Bloecke nicht dauerhaft sondern loeschen einfach alle Eintraege eines Blockes, wenn wir ihn verlassen
     */
    public void leaveBlock() {
        if (!Tabelle.isEmpty()) {
            while (Tabelle.get(Tabelle.size() - 1).getBlock() == block) { //Loescht alle Blockeintraege zu aktuellem Block von Unten nach Oben
                Tabelle.remove(Tabelle.size() - 1);
                if(Tabelle.isEmpty()){break;}
            }

        }
        block--; // Zurueckgehen in den aeusseren Block
    }

    /**
     * Sucht eine Variable nach ihrem Namen in der Symboltabelle von Unten nach Oben ab und gibt erstes Ergebnis zurueck 
     * 
     * @param name Variablenname
     * @return Eintag zur 1. gefundenen Variable mit dem Namen sonst undefinierter Dummy-Eintrag bei erfolgloser Suche
     */
    public SymboltabellenEintrag getEintrag(String name){
        for(int i=Tabelle.size()-1;i>=0;i--)
        {
            if(Objects.equals(Tabelle.get(i).getName(), name))
            {
                return Tabelle.get(i);
            }
        }
        return new SymboltabellenEintrag(0,"",-1);
    }

    @Override
    public String toString() {
        return Tabelle.toString();
    }
}
