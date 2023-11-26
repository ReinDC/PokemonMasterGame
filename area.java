public class area{
    private int col;
    private int row;
    private int limitCol = 7;
    private int limitRow = 3;
    public int pPosY = 1;
    public int pPosX = 1;
    public int advantage;
    public int eHP = 50;
    public int pHP = 50;
    private pokemons activePoke = null;

    /**
    * Prints the area and moves the player to the desired direction of the user
    * @param move - the move of the user; 1 - Right, 2 - Left, 3 - Back to menu
    */
    public void printArea(int move){
        if(move == 1 && pPosX != 5){ // Going right
            pPosX += 1;
        }
        
        else if(move == 2 && pPosX != 1){ // Going left
            pPosX -= 1;
        }

        else if ((move == 2 && pPosX == 1) || (move == 1 && pPosX == 5)){ // At the edge either side
            System.out.println("Cannot move any further!");
        }
        
        
        for(row = 0; row < limitRow; row++){
            for(col = 0; col < limitCol; col++){

                if(row == pPosY && col == pPosX){
                    System.out.print("P");
                }

                else if(row == 1 && col > 0 && col < 6)
                    System.out.print(" ");

                
                else{
                    System.out.print("*");
                }
                
            }   
            System.out.print("\n");
        }
    }

    /**
    * Prints the area and moves the player to the desired direction of the user
    * @param enemy - the enemy pokemon of the player
    * @param inv - the instance of the inventory class found in main
    */
    public void encounter(pokemons enemy, inventory inv){
        activePoke = inv.getActivePokemon();

        if((enemy.getType() == "Grass" && activePoke.getType() == "Fire") || (enemy.getType() == "Water" && activePoke.getType() == "Grass") || (enemy.getType() == "Fire" && activePoke.getType() == "Water")){
            advantage = 1;
        }

        System.out.println("====================Battle Phase====================");
        System.out.println("\t\t\t\t\tEnemy HP: " + eHP);
        System.out.println("\t\t\t\t\tEnemy: " + enemy.getName());
        System.out.println("\t\t\t\t\tEL: " + enemy.getLevel());
        System.out.println("\t\t\t\t\tType: " + enemy.getType());

        System.out.println("Your HP: " + pHP);
        System.out.println("You: " + activePoke.getName());
        System.out.println("EL: " + activePoke.getLevel());
        System.out.println("Type: " + activePoke.getType());
        System.out.println("===================================================");
    }

}
