package Moves;
import processing.core.*;

public class Move {

    private String name;
    private boolean isSpecial;
    private String type;
    private int dmg;
    private double critChance;

    public Move(String name, String type, int dmg, boolean isSpecial, double critChance) {
        this.name = name;
        this.type = type;
        this.dmg = dmg;
        this.isSpecial = isSpecial;
        this.critChance = critChance;
    }

    public Move(String name, String type, int dmg, boolean isSpecial) {
        this.name = name;
        this.type = type;
        this.dmg = dmg;
        this.isSpecial = isSpecial;
        this.critChance = 0.0625;
    }


    public boolean isSpecial() {
        return this.isSpecial;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public double getCritChance() {
        return critChance;
    }
}
