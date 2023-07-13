package org.SPLGrammar.grammar;

public class SymboltabellenEintrag {
    private final int block;
    private final int type; // 0=bool, 1=int,2=String
    private final String name;

    private boolean bool=false;
    private double num=0;
    private String str="";

    public SymboltabellenEintrag(int block,String name, int type){
        this.block=block;
        this.name=name;
        this.type=type;
    }
    public int getBlock(){return block;}
    public int getType() {return type;}
    public String getName() {return name;}

    public void setValue(double x){if(type==1)num=x; }
    public void setValue(boolean x){if(type==0)bool=x; }
    public void setValue(String x){if(type==2)str=x; }

    public double getValueN(){return num;}
    public boolean getValueB(){return bool;}
    public String getValueS(){return str;}

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
