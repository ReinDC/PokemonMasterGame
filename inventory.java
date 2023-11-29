import java.util.*;

public class inventory{
    public List<pokemons> inventory = new ArrayList<>();
    private pokemons activePokemon = null;

    /**
    * Adds the chosen pokemon to the inventory of the user
    * @param pokemon - the pokemon to be added inside of the inventory
    */
    public void addPokemon(pokemons pokemon){
        inventory.add(pokemon);
    }

    /**
    * Initializes the activePokemon as the chosen pokemon of the user
    * from their inventory.
    * @param pokemon - the pokemon to be set as the active pokemons of the user
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

    /**
    * Gets the pokemons names inside of the inventory of the user
    * Its to display inside the GUI
    * @return names - the list of the user's pokemon names
    */
    public ArrayList<String> getNames(){
        ArrayList<String> names = new ArrayList<String>();

        for(pokemons p : inventory){
            names.add(p.getName());
        }

        return names;
    }

    /**
    * Removes the specified pokemon from the inventory
    * Part of the evolution process
    * @param target - the target pokemon to be removed from the inventory
    */
    public void removePokemon(pokemons target){
        for(pokemons p : inventory){
            if(p.getName() == target.getName()){
                inventory.remove(p);
                break;
            }
        }

    }

    /**
    * Gets the evolution of the specified pokemon
    * Part of the evolution process
    * @param p1 - the pokemon to get the evolution of
    * @return the evolution of the specified pokemon
    */
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
    
    /**
    * Trys to evolve the two chosend pokemons and adds it into the user's inventory
    * @param p1 - 1st chosen pokemon of the user to try to evlove
    * @param p2 - 2nd chosen pokemon of the user to try to evlove
    * @return 1 for success, 0 for failure
    */
    public int evolvePokemon(pokemons p1, pokemons p2){
        int ctr = 0;

        if(p1.getName() == p2.getName() && p1.getLevel() != 3){
            for(pokemons p : inventory){
                if(p.getName() == p1.getName()){
                    ctr++;
                }
            }

            if(ctr > 1){
                pokemons t = getEvloved(p1);
                removePokemon(p1);
                removePokemon(p2);
                addPokemon(t);
                setActivePokemon(t);

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

