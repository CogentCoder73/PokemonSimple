package Pokemon;

import java.util.*;
public class TypeChart {
    private HashSet<String> types;

    public TypeChart(HashSet<String> types) {
        this.types = types;
    }

    public ArrayList<String> weakGen() {
        ArrayList<String> weaknesses = new ArrayList<String>();
        if (types.contains("normal")) {
            weaknesses.add("fight");
        }
        if (types.contains("fight")) {
            weaknesses.add("flying");
            weaknesses.add("psychic");
            weaknesses.add("fairy");
        }
        if (types.contains("flying")) {
            weaknesses.add("rock");
            weaknesses.add("electric");
            weaknesses.add("ice");
        }
        if (types.contains("poison")) {
            weaknesses.add("ground");
            weaknesses.add("psychic");
        }
        if (types.contains("ground")) {
            weaknesses.add("water");
            weaknesses.add("grass");
            weaknesses.add("ice");
        }
        if (types.contains("rock")) {
            weaknesses.add("fight");
            weaknesses.add("ground");
            weaknesses.add("steel");
            weaknesses.add("water");
            weaknesses.add("grass");
        }
        if (types.contains("bug")) {
            weaknesses.add("flying");
            weaknesses.add("rock");
            weaknesses.add("fire");
        }
        if (types.contains("ghost")) {
            weaknesses.add("ghost");
            weaknesses.add("dark");
        }
        if (types.contains("steel")) {
            weaknesses.add("fight");
            weaknesses.add("ground");
            weaknesses.add("fire");
        }
        if (types.contains("fire")) {
            weaknesses.add("ground");
            weaknesses.add("rock");
            weaknesses.add("water");
        }
        if (types.contains("water")) {
            weaknesses.add("grass");
            weaknesses.add("electric");
        }
        if (types.contains("grass")) {
            weaknesses.add("flying");
            weaknesses.add("poison");
            weaknesses.add("bug");
            weaknesses.add("fire");
            weaknesses.add("ice");
        }
        if (types.contains("electric")) {
            weaknesses.add("ground");
        }
        if (types.contains("psychic")) {
            weaknesses.add("bug");
            weaknesses.add("ghost");
            weaknesses.add("dark");
        }
        if (types.contains("ice")) {
            weaknesses.add("fight");
            weaknesses.add("rock");
            weaknesses.add("steel");
            weaknesses.add("fire");
        }
        if (types.contains("dragon")) {
            weaknesses.add("ice");
            weaknesses.add("dragon");
            weaknesses.add("fairy");
        }
        if (types.contains("dark")) {
            weaknesses.add("fight");
            weaknesses.add("bug");
            weaknesses.add("fairy");
        }
        if (types.contains("fairy")) {
            weaknesses.add("poison");
            weaknesses.add("steel");
        }
        return weaknesses;
    }

    public ArrayList<String> resGen() {
        ArrayList<String> resistances = new ArrayList<String>();
        if (types.contains("fight")) {
            resistances.add("rock");
            resistances.add("bug");
            resistances.add("dark");
        }
        if (types.contains("flying")) {
            resistances.add("fight");
            resistances.add("bug");
            resistances.add("grass");
        }
        if (types.contains("poison")) {
            resistances.add("fight");
            resistances.add("poison");
            resistances.add("bug");
            resistances.add("grass");
            resistances.add("fairy");
        }
        if (types.contains("ground")) {
            resistances.add("poison");
            resistances.add("rock");
        }
        if (types.contains("rock")) {
            resistances.add("normal");
            resistances.add("flying");
            resistances.add("poison");
            resistances.add("fire");
        }
        if (types.contains("bug")) {
            resistances.add("fight");
            resistances.add("ground");
            resistances.add("grass");
        }
        if (types.contains("ghost")) {
            resistances.add("poison");
            resistances.add("bug");
        }
        if (types.contains("steel")) {
            resistances.add("normal");
            resistances.add("flying");
            resistances.add("rock");
            resistances.add("bug");
            resistances.add("steel");
            resistances.add("grass");
            resistances.add("psychic");
            resistances.add("ice");
            resistances.add("dragon");
            resistances.add("fairy");
        }
        if (types.contains("fire")) {
            resistances.add("bug");
            resistances.add("steel");
            resistances.add("fire");
            resistances.add("grass");
            resistances.add("ice");
            resistances.add("fairy");
        }
        if (types.contains("water")) {
            resistances.add("steel");
            resistances.add("fire");
            resistances.add("water");
            resistances.add("ice");
        }
        if (types.contains("grass")) {
            resistances.add("ground");
            resistances.add("water");
            resistances.add("grass");
            resistances.add("electric");
        }
        if (types.contains("electric")) {
            resistances.add("flying");
            resistances.add("steel");
            resistances.add("electric");
        }
        if (types.contains("psychic")) {
            resistances.add("fight");
            resistances.add("psychic");
        }
        if (types.contains("ice")) {
            resistances.add("ice");
        }
        if (types.contains("dragon")) {
            resistances.add("fire");
            resistances.add("water");
            resistances.add("grass");
            resistances.add("electric");
        }
        if (types.contains("dark")) {
            resistances.add("ghost");
            resistances.add("dark");
        }
        if (types.contains("fairy")) {
            resistances.add("fight");
            resistances.add("bug");
            resistances.add("dark");
        }
        return resistances;
    }

    public ArrayList<String> imGen() {
        HashSet<String> immunities = new HashSet<String>();
        if (types.contains("normal")) {
            immunities.add("ghost");
        }
        if (types.contains("flying")) {
            immunities.add("ground");
        }
        if (types.contains("ground")) {
            immunities.add("electric");
        }
        if (types.contains("ghost")) {
            immunities.add("normal");
            immunities.add("fight");
        }
        if (types.contains("steel")) {
            immunities.add("poison");
        }
        if (types.contains("dark")) {
            immunities.add("psychic");
        }
        if (types.contains("fairy")) {
            immunities.add("dragon");
        }
        return new ArrayList<String>(immunities);
    }
}