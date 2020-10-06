package Pokemon;
import Moves.*;
import java.util.*;
public class Pokemon {
    private String name;
    private HashSet<String> types;
    private int maxHP;
    private int curHP;
    private int atk;
    private int def;
    private int spA;
    private int spD;
    private int speed;
    private ArrayList<String> bigweaknesses;
    private ArrayList<String> weaknesses;
    private ArrayList<String> bigresistances;
    private ArrayList<String> resistances;
    private ArrayList<String> immunities;
    private ArrayList<String> neutral;
    private Move move1;
    private Move move2;
    private Move move3;
    private Move move4;

    public Pokemon(String types, String name) {
        this.name = name;
        this.types = new HashSet(Arrays.asList(types.split("\\W+")));
        TypeChart tc = new TypeChart(this.types);
        this.weaknesses = tc.weakGen();
        this.resistances = tc.resGen();
        this.immunities = tc.imGen();
        setNeutral();
        bigweaknesses = new ArrayList<String>();
        bigresistances = new ArrayList<String>();
        setTypeChart();
    }
    public void setMoves(String move1, String move2, String move3, String move4) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class m1 = Class.forName("Moves." + move1);
        Class m2 = Class.forName("Moves." + move2);
        Class m3 = Class.forName("Moves." + move3);
        Class m4 = Class.forName("Moves." + move4);
        this.move1 = (Move) m1.newInstance();
        this.move2 = (Move) m2.newInstance();
        this.move3 = (Move) m3.newInstance();
        this.move4 = (Move) m4.newInstance();
    }

    private void setNeutral() {
        neutral = new ArrayList(Arrays.asList("normal", "fight", "flying",
                "poison", "ground", "rock", "bug", "ghost", "steel", "fire", "water", "grass", "electric"
                , "psychic", "ice", "dragon", "dark", "fairy"));
        int size = neutral.size();
        for(int i = 0; i < size; i++){
            String curType = neutral.get(i);
            if(resistances.contains(curType) || weaknesses.contains(curType) || immunities.contains(curType)){
                neutral.remove(curType);
                size--;
                i--;
            }
        }
    }

    private void setTypeChart() {
        HashMap<String, Integer> usedTypes = new HashMap<String, Integer>();
        int size = weaknesses.size();
        for(int i = 0; i < size; i++){
            String curType = weaknesses.get(i);
            if(immunities.contains(curType)){
                weaknesses.remove(curType);
                i--;
                size--;
            } else if(resistances.contains(curType)){
                weaknesses.remove(curType);
                resistances.remove(curType);
                neutral.add(curType);
                i--;
                size--;
            } else if(usedTypes.containsKey(curType)){
                if(!bigweaknesses.contains(curType)){
                    bigweaknesses.add(curType);
                }
                weaknesses.remove(curType);
                weaknesses.remove(usedTypes.get(curType));
                i -= 2;
                size -= 2;
            } else {
                usedTypes.put(curType, i);
            }
        }
        usedTypes = new HashMap<String, Integer>();
        size = resistances.size();
        for(int i = 0; i < size; i++){
            String curType = resistances.get(i);
            if(immunities.contains(curType)){
                resistances.remove(curType);
                i--;
                size--;
            } else if(weaknesses.contains(curType)){
                weaknesses.remove(curType);
                resistances.remove(curType);
                neutral.add(curType);
                i--;
                size--;
            } else if(usedTypes.containsKey(curType)){
                if(!bigresistances.contains(curType)){
                    bigresistances.add(curType);
                }
                resistances.remove(curType);
                resistances.remove(usedTypes.get(curType));
                i--;
                size -= 2;
            } else {
                usedTypes.put(curType, i);
            }
        }
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return this.name + "; Types: " + Arrays.toString(new HashSet[]{this.types})
                + "; 4x Weaknesses: " + Arrays.toString(new ArrayList[]{this.bigweaknesses}) + "; 2x Weaknesses: "
                + Arrays.toString(new ArrayList[]{this.weaknesses}) + "; Neutral: " +
                Arrays.toString(new ArrayList[]{this.neutral}) + "; 2x Resistances: " +
                Arrays.toString(new ArrayList[]{this.resistances}) + "; 4x Resistances: " +
                Arrays.toString(new ArrayList[]{this.bigresistances}) + "; Immunities: " +
                Arrays.toString(new ArrayList[]{this.immunities});
    }

    public int getCurHP() {
        return maxHP;
    }

    public void setCurHP(int dmg) {
        this.curHP -= dmg;
    }

    public void setStats(int maxHP, int atk, int def, int spA, int spD, int speed) {
        this.maxHP = maxHP;
        this.curHP = maxHP;
        this.atk = atk;
        this.def = def;
        this.spA = spA;
        this.spD = spD;
        this.speed = speed;
    }

    public String getStats() {
        return atk + " " + def + " " + spA + " " + spD + " " + speed;
    }

    public HashSet<String> getTypes() {
        return types;
    }

    public ArrayList<String> getBigweaknesses() {
        return bigweaknesses;
    }

    public ArrayList<String> getWeaknesses() {
        return weaknesses;
    }

    public ArrayList<String> getBigresistances() {
        return bigresistances;
    }

    public ArrayList<String> getResistances() {
        return resistances;
    }

    public ArrayList<String> getImmunities() {
        return immunities;
    }
}
