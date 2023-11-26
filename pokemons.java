import java.util.*;


public class pokemons{
    private String name;
    private int level;
    private String type;
    private char family;
        
    /**
    * Initializes the pokemons name, type, level and family
    * @param name - a String; the name of the pokemon
    * @param type - a String; the type of the pokemon
    * @param level - a int; the level of the pokemon
    * @param family - a char; the family of the pokemon
    */
    public pokemons(String name, String type, int level, char family){
        this.name = name;
        this.type = type;
        this.level = level;
        this.family = family;
    }

    /**
    * Initializes the variable as the name of the pokemon
    * @return name - the name of the pokemon
    */
    public String getName(){
        return name;
    }

    /**
    * Initializes the variable as the evolution level of the pokemon
    * @return level - the level of the pokemon
    */
    public int getLevel(){
        return level;
    }

    /**
    * Initializes the variable as the type of the pokemon
    * @return type - the type of the pokemon
    */
    public String getType(){
        return type;
    }

    /**
    * Initializes the variable as the family of the pokemon
    * @return family - the family of the pokemon
    */
    public char getFamily(){
        return family;
    }
    
    /**
    * Initializes the variable as the list of all the available pokemon
    * @return pokeList - the list of all available pokemon
    */
    public static List<pokemons> pokeList(){
            List<pokemons> Pokemons = new ArrayList<>();
            //* Fire types

            // Family A
            Pokemons.add(new pokemons("Strawander", "Fire", 1, 'A')); // 0
            Pokemons.add(new pokemons("Strawleon", "Fire", 2, 'A'));
            Pokemons.add(new pokemons("Strawizard", "Fire", 3, 'A'));

            // Family B
            Pokemons.add(new pokemons("Chocowool", "Fire", 1, 'B')); // 3
            Pokemons.add(new pokemons("Chocofluff", "Fire", 2, 'B'));
            Pokemons.add(new pokemons("Candaros", "Fire", 3, 'B'));

            // Family C
            Pokemons.add(new pokemons("Parfwit", "Fire", 1, 'C')); // 6
            Pokemons.add(new pokemons("Parfure", "Fire", 2, 'C'));
            Pokemons.add(new pokemons("Parfelure", "Fire", 3, 'C'));


            //* Grass types

            // Family D
            Pokemons.add(new pokemons("Brownisaur", "Grass", 1, 'D')); // 9
            Pokemons.add(new pokemons("Chocosaur", "Grass", 2, 'D'));
            Pokemons.add(new pokemons("Fudgasaur", "Grass", 3, 'D'));

            // Family E
            Pokemons.add(new pokemons("Frubat", "Grass", 1, 'E')); // 12
            Pokemons.add(new pokemons("Golberry", "Grass", 2, 'E'));
            Pokemons.add(new pokemons("Croberry", "Grass", 3, 'E'));

            // Family F
            Pokemons.add(new pokemons("Malts", "Grass", 1, 'F')); // 15
            Pokemons.add(new pokemons("Krilicake", "Grass", 2, 'F'));
            Pokemons.add(new pokemons("Velveior", "Grass", 3, 'F'));

            //* Water types

            // Family G
            Pokemons.add(new pokemons("Squirepie", "Water", 1, 'G')); // 18
            Pokemons.add(new pokemons("Tartortle", "Water", 2, 'G'));
            Pokemons.add(new pokemons("Piestoise", "Water", 3, 'G'));

            // Family H
            Pokemons.add(new pokemons("Chocolite", "Water", 1, 'H')); // 21
            Pokemons.add(new pokemons("Chocolish", "Water", 2, 'H'));
            Pokemons.add(new pokemons("Icesundae", "Water", 3, 'H'));

            // Family I
            Pokemons.add(new pokemons("Oshacone", "Water", 1, 'I')); // 24
            Pokemons.add(new pokemons("Dewice", "Water", 2, 'I'));
            Pokemons.add(new pokemons("Samuracone", "Water", 3, 'I'));

            return Pokemons;
        }
}

