import java.util.*;
import Moves.Move;
import Pokemon.Pokemon;
import java.lang.reflect.*;
public class DamageCalculator {
    private HashSet<String> pkmn1type;
    private Pokemon defPokemon;
    private Pokemon atkPokemon;
    private int atk;
    private int def;
    private int spA;
    private int spD;
    private Random prob;
    public DamageCalculator(String pokemon1, String pokemon2) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Pokemon atkPokemon = (Pokemon) Class.forName("Pokemon." + pokemon1).newInstance();
        Pokemon defPokemon = (Pokemon) Class.forName("Pokemon." + pokemon2).newInstance();
        pkmn1type = atkPokemon.getTypes();
        String[] stats = (atkPokemon.getStats().split("\\W+"));
        this.atk = Integer.parseInt(stats[1]);
        this.spA = Integer.parseInt(stats[2]);
        stats = (defPokemon.getStats().split("\\W+"));
        this.def = Integer.parseInt(stats[3]);
        this.spD = Integer.parseInt(stats[4]);
        this.defPokemon = defPokemon;
        this.atkPokemon = atkPokemon;
        prob = new Random();
    }
    public int getDamage(String move) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Move curMove = (Move) Class.forName("Moves." + move).newInstance();
        String moveType = curMove.getType();
        int power = curMove.getDmg();
        double modifier = 1 - prob.nextDouble() / 5;
        double ad;
        System.out.println(atkPokemon.getName() + " used " + curMove.getName() + " on " + defPokemon.getName());
        if (curMove.isSpecial()) {
            ad = (spA * 1.0) / spD;
        } else {
            ad = (atk * 1.0) / def;
        }
        double baseFrac = (42 * power * ad) / 50 + 2;
        boolean crit = prob.nextDouble() < curMove.getCritChance();
        if (crit) {
            System.out.println("A critical hit!");
            modifier *= 1.5;
        }
        boolean STAB = this.pkmn1type.contains(moveType);
        if (STAB) {
            System.out.println("Same type attack bonus!");
            modifier *= 1.5;
        }
        double effectiveness = 1.0;
        if(defPokemon.getBigweaknesses().contains(moveType)){
            System.out.println("It's super effective (4x) on the opposing " + defPokemon.getName());
            effectiveness = 4.0;
        } else if(defPokemon.getWeaknesses().contains(moveType)){
            System.out.println("It's super effective on the opposing " + defPokemon.getName());
            effectiveness = 2.0;
        } else if(defPokemon.getBigresistances().contains(moveType)){
            System.out.println("It's not very effective (4x) on the opposing " + defPokemon.getName());
            effectiveness = 0.25;
        } else if(defPokemon.getResistances().contains(moveType)){
            System.out.println("It's not very effective on the opposing " + defPokemon.getName());
            effectiveness = 0.5;
        } else if(defPokemon.getImmunities().contains(moveType)){
            System.out.println("The move had no effect on the opposing " + defPokemon.getName());
            effectiveness = 0;
        }
        modifier *= effectiveness;
        int damage = (int) Math.round(baseFrac * modifier);
        return damage;
    }
}
