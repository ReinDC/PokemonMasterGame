import java.util.*;

public class mainGame{
    
    /**
    * Prints the list of evolution level 1 pokemons from the list
    * of pokemons.
    */
    public void printLevel1(){
        List<pokemons> list = pokemons.pokeList();

        System.out.println("====================Pokemon List====================");
        int count = 1;
        for (pokemons pokemon : list){
            if(pokemon.getLevel() == 1){
                System.out.println("[" + count + "] " + "Name: " + pokemon.getName() + " Type: " + pokemon.getType() + " EL: " + pokemon.getLevel());
                count++;
            }
        }
        System.out.println("====================================================");
        
    }

    /**
    * Gets the names of evolution level 1 pokemons from the list
    * of pokemons.
    */
   
    

    /**
    * Prints all of the starter pokemons aka (evolution level 1 pokemons) and
    * asks the user to choose then adds the chosen pokemon to their inventory.
    * @param inv - the instance of the inventory class found in main
    * @param input - the instance of a Scanner object found in main
    */
    
    /**
    * Prints the the inventory of the user which contains all of the
    * pokemons caught by the user and highlights what pokemon is active.
    * @param inv - the instance of the inventory class found in main
    */
    
    /**
    * Prints the choices the user have for their inventory.
    * It lets the user chooses what to set as their active pokemon.
    * @param inv - the instance of the inventory class found in main
    * @param input - the instance of a Scanner object found in main
    */
   

    /**
    * Prints the list of evolution level 1 pokemons from the list
    * of pokemons.
    * @param inv - the instance of the inventory class found in main
    * @return choice - the choice of the user from the given list
    */
    
    /**
    * It calls the function printArea from the area class to print and move 
    * the player within the area. This also contains whether the player will
    * encounter a wild pokemon or not.
    * @param inv - the instance of the inventory class found in main
    * @param input - the instance of a Scanner object found in main
    */
    
    /**
    * It calls the function printArea from the area class to print and move 
    * the player within the area. This also contains whether the player will
    * encounter a wild pokemon or not.
    * @param area - the instance of the area class found in areaExplo function
    * @param input - the instance of a Scanner object found in main
    * @param inv - the instance of the inventory class found in main
    * @param enemy - the enemy pokemon that is randomly picked from the list of EL 1 pokemons
    * @param tick - the counter of how many moves the player has made or the exit
    *               variable if the player have successfully catched the wild pokemon
    *               or chooses to run away from the encounter.
    *
    * @return tick - the counter of how many moves the player has made or the exit
    *                variable if the player have successfully catched the wild pokemon
    *                or chooses to run away from the encounter.
    */
}
