package org.SPLGrammar.grammar;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Symboltabelle {
    private final List<SymboltabellenEintrag> Tabelle = new ArrayList<>();
    private int block =0;

    public Symboltabelle(){}

    public int addVariable(String name, int type)
    {
        int ret = getEintrag(name).getType();    //ret ist -1 wenn die Variable noch nicht definiert war. Sonst 0,1,2
        if(ret>0)ret=0;             //ret ist -1 wenn die Variable noch nicht definiert war. Sonst 0
        Tabelle.add(new SymboltabellenEintrag(block,name,type));
        return -1-ret;              //wenn die Variable schon definiert war wird -1 zurueckgegeben. sonst 0
    }
    public void enterBlock(){
        block++;
    }
    public void leaveBlock() {
        if (!Tabelle.isEmpty()) {
            while (Tabelle.get(Tabelle.size() - 1).getBlock() == block) {
                Tabelle.remove(Tabelle.size() - 1);
                if(Tabelle.isEmpty()){break;}
            }

        }
        block--;
    }



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
