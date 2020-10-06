import Pokemon.Pokemon;
import java.util.*;
public class CheckEffectiveness {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        while(true){
            System.out.print("Types: ");
            Pokemon p = new Pokemon(input.nextLine(), "Pokemon");
            System.out.println(p);
        }
    }
}
