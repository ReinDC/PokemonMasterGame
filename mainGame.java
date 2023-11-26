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
    public ArrayList<String> loadNameLevel1(){
        List<pokemons> list = pokemons.pokeList();
        ArrayList<String> names = new ArrayList<>();


        for (pokemons pokemon : list){
            if(pokemon.getLevel() == 1){
                String name = pokemon.getName();
                names.add(name);
            }

        }
        return names;
    }
    

    /**
    * Prints all of the starter pokemons aka (evolution level 1 pokemons) and
    * asks the user to choose then adds the chosen pokemon to their inventory.
    * @param inv - the instance of the inventory class found in main
    * @param input - the instance of a Scanner object found in main
    */
    public void chooseStarter(inventory inv, Scanner input){
        mainGame game = new mainGame();
        List<pokemons> list = pokemons.pokeList();
        pokemons starter = null;
       

        int exit = 0;

        while(exit != 1){ // Loops until the user chooses a starter pokemon
            game.printLevel1();
            System.out.print("Choose your starter pokemon: ");
            int choice = input.nextInt();



            if(choice == 1){
                starter = list.get(0);
                inv.addPokemon(starter);
                inv.setActivePokemon(starter);
                exit = 1;
            }

            else if(choice >= 2 && choice <= 9){
                starter = list.get((choice - 1) * 3);
                inv.addPokemon(starter);
                inv.setActivePokemon(starter);
                exit = 1;
            }

            else
                System.out.println("Please choose from the list.");

        } // end loop
    }

    /**
    * Prints the the inventory of the user which contains all of the
    * pokemons caught by the user and highlights what pokemon is active.
    * @param inv - the instance of the inventory class found in main
    */
    public void printInv(inventory inv){
        int active = 0;
        System.out.println();
        System.out.println("====================Inventory====================");
        int count = 1;
        
        for(pokemons pokemon : inv.getInventory()){

            if(inv.getActivePokemon() == pokemon && active != 1){
                System.out.println("[" + count + "] " + pokemon.getName() + " EL: " + pokemon.getLevel() + " Type: " + pokemon.getType() + " Family: " + pokemon.getFamily() + " (Active)");
                active = 1;
            }

            else{
                System.out.println("[" + count + "] " + pokemon.getName() + " EL: " + pokemon.getLevel() + " Type: " + pokemon.getType() + " Family: " + pokemon.getFamily());
            }
                
            count++;
        }
        System.out.println("=================================================");
    
    }

    /**
    * Prints the choices the user have for their inventory.
    * It lets the user chooses what to set as their active pokemon.
    * @param inv - the instance of the inventory class found in main
    * @param input - the instance of a Scanner object found in main
    */
    public void inventoryMenu(inventory inv, Scanner input){
        mainGame game = new mainGame();
        int size = inv.getInventorySize();
        List<pokemons> list = inv.getInventory();
        int exit = 0, choice = 0;
        
        while(exit != 1){
            game.printInv(inv);
            if(size != 1){
                System.out.println("[1] Set active pokemon");
                System.out.println("[2] Back");
                System.out.print("Choice: ");
            }

            else{
                 System.out.println("[1] Back");
                System.out.print("Choice: ");
            }

            choice = input.nextInt();

            switch(choice){
                default:    System.out.println("Please choose from the list.");
                            break;

                case 1: if(size != 1){
                            System.out.println("Choose from your inventory");
                            System.out.print("Choice: ");
                            int invChoice = input.nextInt();

                            if(invChoice-1 <= size-1 && invChoice-1 >= 0){
                                inv.setActivePokemon(list.get(invChoice-1));
                            }

                            exit = 1;
                        }

                        else{
                            exit = 1;
                        }

                        break;
                        
                case 2: if(size == 1){
                           System.out.println("Please choose from the list.");
                        }

                        else{
                            exit = 1;
                        } 

                        break;
            }
        }
    }

    /**
    * Prints the list of evolution level 1 pokemons from the list
    * of pokemons.
    * @param inv - the instance of the inventory class found in main
    * @return choice - the choice of the user from the given list
    */
    public int mainMenu(Scanner input){
        int mainChoice = 0;
        
        System.out.println();
        System.out.println("====================Main Menu====================");
        System.out.println("[1] View Inventory");
        System.out.println("[2] Explore an Area");
        System.out.println("[3] Evolve a Creature");
        System.out.println("[4] Exit Game");
        System.out.println("=================================================");
        System.out.print("Choice: ");
        
        mainChoice = input.nextInt();

        return mainChoice;
    }

    /**
    * It calls the function printArea from the area class to print and move 
    * the player within the area. This also contains whether the player will
    * encounter a wild pokemon or not.
    * @param inv - the instance of the inventory class found in main
    * @param input - the instance of a Scanner object found in main
    */
    public void areaExplo(Scanner input, inventory inv){
        Random random = new Random();
        area area = new area();
        int move = 0, exit = 0, mCounter = 0, battle = 100;
        mainGame m = new mainGame();
        List<pokemons> lvl1 = new ArrayList<>();
        List<pokemons> list = pokemons.pokeList();

        for(pokemons poke : list){
            if(poke.getLevel() == 1){
                lvl1.add(poke);
            }
        }

        Collections.shuffle(lvl1); // Randomly shuffle the list

        while(exit != 1){
            if(move != 3 && battle > 40){
                area.printArea(move);
                System.out.println("[1] Right");
                System.out.println("[2] Left");
                System.out.println("[3] Back to menu");
                System.out.print("Move: ");
                        
                move = input.nextInt();

                if((move == 1 && area.pPosX != 5) || (move == 2 && area.pPosX != 1)){
                    battle = random.nextInt(1,100);
                }
            }
            
            else if(battle <= 40){
                    mCounter = 0;
                    area area1 = new area();
                    System.out.println();
                    System.out.println("Wild Pokemon encountered!");
                    System.out.println();
                    
                    while(mCounter != 3){
                        System.out.println();
                        mCounter = m.battlePhase(area1, input, inv, lvl1.get(0), mCounter);
                        if(mCounter == 3){

                            battle = 100;
                        }
                    }
                }
                
            else if(move == 3){
                exit = 1;
            }
                    

            else{
                System.out.println("Please choose from the list.");
            }
 
        }
    }
    

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
    public int battlePhase(area area, Scanner input, inventory inv, pokemons enemy, int tick){
        Random r = new Random();
        mainGame m = new mainGame();
        int rands = r.nextInt(1,10), dmg = 0, rate = r.nextInt(1, 100), catchRate;
        
       
        area.encounter(enemy, inv);

        if(inv.getInventorySize() == 1){
            System.out.println("[1] Attack");
            System.out.println("[2] Catch");
            System.out.println("[3] Run");
        }
        
        else{
            System.out.println("[1] Attack");
            System.out.println("[2] Catch");
            System.out.println("[3] Swap");
            System.out.println("[4] Run");
        }
        
        System.out.print("Move: ");

        int choice = input.nextInt();

        if(area.eHP < 0){
            tick = 3;
        }


        switch(choice){
            default: System.out.println("Please choose from the list."); break;

            case 1: dmg = rands * inv.getActivePokemon().getLevel();
                    if(area.advantage != 0){
                        dmg *= 1.5f;
                    }

                    System.out.println("Dmg: -" + dmg);
                    area.eHP -= dmg;
                    tick += 1;
                    break;

            case 2: catchRate = 90 - area.eHP;

                    if(rate < catchRate){
                        System.out.println("Successfully catched!");
                        inv.addPokemon(enemy);
                        tick = 3;
                    }

                    else{
                        System.out.println("Not catched!");
                        tick += 1;
                    }
                    break;

            case 3: if(inv.getInventorySize() == 1){
                        tick = 3;
                    }

                    else{
                        m.inventoryMenu(inv, input);
                    }
                    break;
                    
            case 4: if(inv.getInventorySize() == 1){
                        System.out.println("Please choose from the list.");
                    }

                    else{
                        tick = 3;
                    }
                    break;
        }


        return tick;
    }


    public void play(){
        mainGame game = new mainGame();
        Scanner input = new Scanner(System.in);
        inventory mainInven = new inventory();
        int choice = 0, exit = 0;
    

        game.chooseStarter(mainInven, input); // Let the player choose


        while(exit != 1){
            choice = game.mainMenu(input);
        
            if(choice == 1){
                game.inventoryMenu(mainInven, input);
            }

            else if(choice == 2){
                int exit2 = 0;
                while(exit2 != 1){
                    System.out.println();
                    System.out.println("Choose the area you would like to explore: ");
                    System.out.println("[1] Area 1");
                    System.out.println("[2] LOCKED");
                    System.out.println("[3] LOCKED");
                    System.out.println("[4] Back");
                    System.out.print("Choice: ");
                    int areaChoice = input.nextInt();

                    switch(areaChoice){
                        default: System.out.println("Please choose from the list."); break;

                        case 1: game.areaExplo(input, mainInven); break;
                        
                        case 2: System.out.println();
                                System.out.println("AREA LOCKED"); 
                                System.out.println(); 
                                break;

                        case 3: System.out.println();
                                System.out.println("AREA LOCKED"); 
                                System.out.println(); 
                                break;

                        case 4: exit2 = 1; break;
                    }
                }
            }

            else if(choice == 3){
                System.out.println("Feature yet to be implemented...");
            }

            else if(choice == 4){
                System.out.println("Thank you for playing!");
                exit = 1;
            }

            else{
                System.out.println("Please choose from the list.");
            }
        }

        input.close();
    }
}
