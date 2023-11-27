import java.util.*;

public class inventory{
    public List<pokemons> inventory = new ArrayList<>();
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

    public ArrayList<String> getNames(){
        ArrayList<String> names = new ArrayList<String>();

        for(pokemons p : inventory){
            names.add(p.getName());
        }

        return names;
    }

    public pokemons getEvloved(pokemons p1){
        List<pokemons> allList = pokemons.pokeList();
        pokemons test = null;

        for(pokemons p : allList){
            if(p.getFamily() == p1.getFamily() && p1.getLevel() + 1 == p.getLevel()){
                test = p;
            }
        }

        return test;
    }
    
    public int evolvePokemon(pokemons p1, pokemons p2){
        int ctr = 0;

        if(p1.getName() == p2.getName() && p1.getLevel() != 3){
            for(pokemons p : inventory){
                if(p.getName() == p1.getName()){
                    ctr++;
                }
            }

            if(ctr == 2){
                pokemons t = getEvloved(p1);
                addPokemon(t);

                inventory.remove(p1);
                inventory.remove(p2);
                return 1;
            }
        }

        return 0;
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

