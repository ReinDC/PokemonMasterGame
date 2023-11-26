import java.util.*;

public class inventory{
    private List<pokemons> inventory = new ArrayList<>();
    private pokemons activePokemon = null;

    /**
    * Adds the chosen pokemon to the inventory of the user
    */
    public void addPokemon(pokemons pokemon){
        inventory.add(pokemon);
    }

    /**
    * Initializes the activePokemon as the chosen pokemon of the user
    * from their inventory.
    */
    public void setActivePokemon(pokemons pokemon){
        activePokemon = pokemon;
    }

    /**
    * Initializes the variable as the active pokemons of the user 
    * @return activePokemon - the active pokemons of the user
    */
    public pokemons getActivePokemon(){
        return activePokemon;
    }

    /**
    * Initializes the variable as the list of all the user's caught pokemons 
    * @return inventory - the list of all the user's caught pokemons
    */
    public List<pokemons> getInventory(){
        return inventory;
    }

    /**
    * Initializes the variable as the size of the user's inventory
    * @return size - the size of the list of the user's inventory
    */
    public int getInventorySize(){
        int size = inventory.size();
        
        return size;
    }

    /* 
    ! testing phase
    public static void main(String[] args){
        inventory mainInven = new inventory();
        pokemons pokee= new pokemons("test", "sad", 21, 'c');
        mainInven.addPokemon(pokee);

        System.out.println();
        System.out.println("====================Inventory====================");
        int count = 1;
        
        for(pokemons pokemon : printInven.getInventory()){
                System.out.println("[" + count + "] " + pokemon.getName() + " EL: " + pokemon.getLevel() + " Type: " + pokemon.getType() + " Family: " + pokemon.getFamily());
        }
        System.out.println("=================================================");
         
    }
    */
}

