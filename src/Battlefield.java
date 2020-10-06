import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.io.*;

import Pokemon.*;
import Moves.*;
import processing.core.PApplet;
import processing.core.PImage;
public class Battlefield extends PApplet {
    private String pkmnName2;
    private String pkmnName1;
    private PImage[] genGifFront;
    private PImage[] genGifBack;
    private DamageCalculator p1;
    private DamageCalculator p2;
    private Pokemon player2Pkmn;
    private Pokemon player1Pkmn;

    private PImage[] genGif(String folder, int frames, String delay) {
        PImage[] Frames = new PImage[frames];
        int len = Integer.toString(frames).length();
        for(int i = 0; i < frames; i++){
            Frames[i] = loadImage(folder + "/frame_" + genNum(len, i) + "_delay-" + delay + ".gif", "gif");
        }
        return Frames;
    }

    private PImage[] genGif(String folder, int frames, boolean front) {
        PImage[] Frames = new PImage[frames];
        int len = Integer.toString(frames).length();
        File dir = new File(folder);
        File[] directoryListing = dir.listFiles();
        for (File child : directoryListing) {
            int frameNum = Integer.parseInt(child.toString().substring(folder.length() + 6, folder.length() + 6 + len));
            Frames[frameNum] = loadImage(child.toString(), "gif");
            if(front){
                Frames[frameNum].resize(0, (int) Math.round((3 * height)/16.0));
            } else {
                Frames[frameNum].resize(0, (int) Math.round((5 * height)/11.0));
            }
        }
        return Frames;
    }

    private String genNum(int len, int i) {
        String newI = Integer.toString(i);
        while(newI.length() < len){
            newI = "0" + newI;
        }
        return newI;
    }

    private PImage background;
    static public void main(String[] args) {
        PApplet.main(new String[] { "Battlefield" });
    }
    public void setup() {
        size(800, 600);
        System.out.println("Welcome to the SimplePkmnSimulator! This currently features 1v1 battles!");
        System.out.println("Choosing pokemon");
        Pokemon[] pokemons = choosePkmn();
        player2Pkmn = pokemons[0];
        player1Pkmn = pokemons[1];
        p1 = null;
        p2 = null;
        try {
            p1 = new DamageCalculator(player1Pkmn.getName(), player2Pkmn.getName());
            p2 = new DamageCalculator(player2Pkmn.getName(), player1Pkmn.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println("Player 1's Pokemon: " + player1Pkmn);
        System.out.println("Player 2's Pokemon: " + player2Pkmn);
        pkmnName2 = player2Pkmn.getName();
        genGifFront = genGif("GIFs/" + pkmnName2 + "Frames/", countFrames(pkmnName2), true);
        pkmnName1 = player1Pkmn.getName();
        genGifBack = genGif("GIFs/" + pkmnName1 + "_BackFrames/", countFrames(pkmnName1 + "_Back"), false);
        background = loadImage("Background.png", "png");
        frameRate(20);
    }

    private int countFrames(String pkmnName) {
        File f = new File("GIFs/" + pkmnName + "Frames");
        int count = 0;
        for (File file : f.listFiles()) {
            if (file.isFile()) {
                count++;
            }
        }
        return count;
    }

    public void draw() {
        image(background, 0, 0, width, height);
        image(genGifFront[frameCount % genGifFront.length], Math.round((2662.0 * width)/3600), Math.round((35.0 * height)/144));
        image(genGifBack[frameCount % genGifBack.length], Math.round((132.0 * width)/588), Math.round((12.0 * height)/25));
        try {
            System.out.println(p1.getDamage("ShadowBall"));
            System.out.println(p2.getDamage("ShadowBall"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private Pokemon[] choosePkmn() {
        String[] chosenPkmn = new String[0];
        try {
            chosenPkmn = randomPkmn();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Class pkmn1 = null;
        Class pkmn2 = null;
        try {
            pkmn1 = Class.forName("Pokemon." + chosenPkmn[0]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            pkmn2 = Class.forName("Pokemon." + chosenPkmn[1]);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Pokemon player1Pkmn = null;
        Pokemon player2Pkmn = null;
        try {
            player1Pkmn = (Pokemon) pkmn1.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            player2Pkmn = (Pokemon) pkmn2.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return new Pokemon[] {player1Pkmn, player2Pkmn};
    }

    private String[] randomPkmn() throws FileNotFoundException {
        Scanner pkmnScan = new Scanner(new File("pokemonNames.txt")); //Change to pokemon.txt
        int len = 0;
        ArrayList<String> pokemon = new ArrayList<String>();
        while(pkmnScan.hasNext()){
            pokemon.add(pkmnScan.next());
        }
        Random rand = new Random();
        int randPkmn = rand.nextInt(pokemon.size());
        int rand2Pkmn = rand.nextInt(pokemon.size());
        while(rand2Pkmn == randPkmn){
            rand2Pkmn = rand.nextInt(pokemon.size());
        }
        return new String[] {pokemon.get(randPkmn), pokemon.get(rand2Pkmn)};
    }
}
