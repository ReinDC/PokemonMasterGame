import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class gui extends misc{
    public inventory inv = new inventory();
    private List<pokemons> allPokemonList = pokemons.pokeList();
    private int cEHP = 50, ctr = 0;
    private pokemons ene;
    
    /**
    * The first panel of the game. It is where the player chooses their starter
    * pokemon
    */
    public gui(){
        JFrame frame;
        
        frame = new JFrame("Pokemon Master");
        JPanel panel = new JPanel();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 250);

        JLabel name = new JLabel("Choose a starter:");

        ArrayList<String> options = pokemons.loadNameLevel1();
        JComboBox<String> dropdown = new JComboBox<>(options.toArray(new String[0]));
        
        // ImageIcon icon = new ImageIcon(filepath);
        JLabel imageDisplay = new JLabel();
        ImageIcon icon = new ImageIcon("src\\strawander.png");
        imageDisplay.setIcon(icon);
        // imageDisplay.setText(chosenStarter);
        

        dropdown.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e){
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    updateImageLabel(imageDisplay ,(String) e.getItem());
                }
            }
        });

        JButton submitButton = new JButton("Choose");

        panel.add(name);      
        panel.add(dropdown);
        panel.add(submitButton);
        panel.add(imageDisplay);

        submitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // JOptionPane.showMessageDialog(null, (String) dropdown.getSelectedItem());
                for(pokemons pokemon : allPokemonList){
                    if(pokemon.getName() == (String) dropdown.getSelectedItem()){
                        inv.addPokemon(pokemon);
                        inv.setActivePokemon(pokemon);
                    }
                }
                // JOptionPane.showMessageDialog(null, filepath);
                frame.dispose();
                mainMenu();
            }
        });

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
    * The main menu, where the user can choose whether to look inside their inventory,
    * explore an area or evolve a pokemon. The user also can choose to exit the game here.
    */
    public void mainMenu(){
        JFrame frame;
        
        frame = new JFrame("Pokemon Master");
        JPanel panel = new JPanel(new GridLayout(4, 1));

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);


        JButton invButton = new JButton("Inventory");
        JButton expButton = new JButton("Explore an Area");
        JButton evoButton = new JButton("Evolve a pokemon");
        JButton exitButton = new JButton("Exit");

        evoButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                evolveWindow();

                
            }
        });

        expButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                exploreWindow();

                
            }
        });

        invButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                inventoryWindow();

                
            }
        });

        exitButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                JOptionPane.showMessageDialog(null, "Thanks for playing!");
            }
        });

        panel.add(invButton);
        panel.add(expButton);
        panel.add(evoButton);
        panel.add(exitButton);

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
    * The inventory, where the user can see their availble pokemons and their information
    * and choose to change their active pokemon if they want.
    */
    public void inventoryWindow(){
        List<pokemons> inventory = inv.getInventory(); 
        int active = 0;
        JFrame frame;
        
        frame = new JFrame("Pokemon Master");
        JPanel panel = new JPanel(new GridLayout(inv.getInventorySize() + 1, 5));
        JPanel buttons = new JPanel();

        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);

        JLabel Image = new JLabel("             Image");
        JLabel Name = new JLabel("     Name");
        JLabel EL = new JLabel("     EL");
        JLabel Type = new JLabel("     Type");
        JLabel Family = new JLabel("     Family");

        panel.add(Image);
        panel.add(Name);
        panel.add(EL);
        panel.add(Type);
        panel.add(Family);

        for(pokemons pokemon : inventory){
            JLabel pImage = new JLabel();
            JLabel pName = new JLabel();
            JLabel pEL = new JLabel();
            JLabel pType = new JLabel();
            JLabel pFamily = new JLabel();

            ImageIcon icon = new ImageIcon(getImagePath(pokemon.getName()));
            pImage.setIcon(icon);
            pName.setText(pokemon.getName());
            pEL.setText(Integer.toString(pokemon.getLevel()));
            pType.setText(pokemon.getType());
            pFamily.setText("" + pokemon.getFamily());

            if(pokemon == inv.getActivePokemon() && active != 1){
                pImage.setOpaque(true);
                pImage.setBackground(Color.YELLOW);

                pName.setOpaque(true);
                pName.setBackground(Color.YELLOW);

                pEL.setOpaque(true);
                pEL.setBackground(Color.YELLOW);

                pType.setOpaque(true);
                pType.setBackground(Color.YELLOW);

                pFamily.setOpaque(true);
                pFamily.setBackground(Color.YELLOW);

                active = 1;
            }

            panel.add(pImage);
            panel.add(pName);
            panel.add(pEL);
            panel.add(pType);
            panel.add(pFamily);
        }

        
        JButton changeActiveButton = new JButton("Change active pokemon");
        JButton backButton = new JButton("Back");

        backButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                mainMenu();
            }
        });

        changeActiveButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                changeActiveWindow();
            }
        });

        // panel.add(scrollPane);
        JScrollPane scrollPane = new JScrollPane(panel);

        if(inv.inventory.size() >= 2){
            buttons.add(changeActiveButton);
        }
        
        buttons.add(backButton);

        frame.add(scrollPane);
        frame.add(buttons, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
    * This window displays a dropdown which contains all of the users pokemon and they can choose
    * to select 1 to be their active pokemon
    * Part of the invetory feature.
    */
    public void changeActiveWindow(){
        JFrame frame;
        
        frame = new JFrame("Pokemon Master");
        JPanel panel = new JPanel();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 250);

        JLabel name = new JLabel("Choose: ");

        ArrayList<String> options = inv.getNames();
        JComboBox<String> dropdown = new JComboBox<>(options.toArray(new String[0]));
        
        JLabel imageDisplay = new JLabel();
        updateImageLabel(imageDisplay ,(String) dropdown.getSelectedItem());
        

        dropdown.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e){
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    updateImageLabel(imageDisplay ,(String) e.getItem());
                }
            }
        });

        JButton submitButton = new JButton("Choose");

        panel.add(name);      
        panel.add(dropdown);
        panel.add(submitButton);
        panel.add(imageDisplay);

        submitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // JOptionPane.showMessageDialog(null, (String) dropdown.getSelectedItem());
                for(pokemons pokemon : inv.inventory){
                    if(pokemon.getName() == (String) dropdown.getSelectedItem()){
                        inv.setActivePokemon(pokemon);
                    }
                }
                // JOptionPane.showMessageDialog(null, filepath);
                frame.dispose();
                inventoryWindow();
            }
        });

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
    * Same as before but this is called when the user is currently exploring areas
    * Part of the area exploration feature.
    */
    public void changeActiveWindow2(){
        ctr++;
        JFrame frame;
        
        frame = new JFrame("Pokemon Master");
        JPanel panel = new JPanel();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 250);

        JLabel name = new JLabel("Choose: ");

        ArrayList<String> options = inv.getNames();
        JComboBox<String> dropdown = new JComboBox<>(options.toArray(new String[0]));
        
        JLabel imageDisplay = new JLabel();
        updateImageLabel(imageDisplay ,(String) dropdown.getSelectedItem());
        

        dropdown.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e){
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    updateImageLabel(imageDisplay ,(String) e.getItem());
                }
            }
        });

        JButton submitButton = new JButton("Choose");

        panel.add(name);      
        panel.add(dropdown);
        panel.add(submitButton);
        panel.add(imageDisplay);

        submitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                // JOptionPane.showMessageDialog(null, (String) dropdown.getSelectedItem());
                for(pokemons pokemon : inv.inventory){
                    if(pokemon.getName() == (String) dropdown.getSelectedItem()){
                        inv.setActivePokemon(pokemon);
                    }
                }
                // JOptionPane.showMessageDialog(null, filepath);
                frame.dispose();
                battleWindow(ene);
            }
        });

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
    * The eploration window, has the list of all available windows
    * Part of the area exploration feature.
    */
    public void exploreWindow(){
        JFrame frame;
        
        frame = new JFrame("Pokemon Master");
        JPanel panel = new JPanel(new GridLayout(4, 1));

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);

        JButton Area1Button = new JButton("Area 1");
        JButton Area2Button = new JButton("Area 2");
        JButton Area3Button = new JButton("Area 3");
        JButton backButton = new JButton("Back");

        Area1Button.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                area1Window();
            }
        });

        Area2Button.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                area2Window();
            }
        });


        Area3Button.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                area3Window();
            }
        });


        backButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                mainMenu();
            }
        });

        panel.add(Area1Button);
        panel.add(Area2Button);
        panel.add(Area3Button);
        panel.add(backButton);


        frame.add(panel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
    * The evolution window, has the button to choose what pokemon they want to evolve
    * Part of the evolution feature.
    */
    public void evolveWindow(){
        JFrame frame;
        frame = new JFrame("Pokemon Master");
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JPanel buttons = new JPanel();

        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);


        JLabel poke1 = new JLabel();
        JLabel poke2 = new JLabel();

        JLabel poke1Name = new JLabel();
        JLabel poke2Name = new JLabel();

        JLabel poke1EL = new JLabel();
        JLabel poke2EL = new JLabel();

        ImageIcon icon1, icon2;

        if(chosen1 != null){
            icon1 = new ImageIcon(getImagePath(chosen1.getName()));
            poke1.setIcon(icon1);
            poke1Name.setText("Name: " + chosen1.getName());
            poke1EL.setText("Evolution Level: " + chosen1.getLevel());
        }

        if(chosen2 != null){
            icon2 = new ImageIcon(getImagePath(chosen2.getName()));
            poke2.setIcon(icon2);
            poke2Name.setText("Name: " + chosen2.getName());
            poke2EL.setText("Evolution Level: " + chosen2.getLevel());
        }

        

        JButton evolve1Button = new JButton("Choose the pokemon #1");
        JButton evolve2Button = new JButton("Choose the pokemon #2");
        JButton evolve = new JButton("Evolve");
        JButton backButton = new JButton("Back");
        if(chosen1 != null && chosen2 != null){
            buttons.add(evolve);
        }

        evolve1Button.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                choosePokeEvo1();
            }
        });

        evolve2Button.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                choosePokeEvo2();
            }
        });

        backButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                mainMenu();
            }
        });

        evolve.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                int i = inv.evolvePokemon(chosen1, chosen2);
                
                if(i == 1){
                    JOptionPane.showMessageDialog(null, "Evolution Success!");
                    chosen1 = null;
                    chosen2 = null;
                    frame.dispose();
                    mainMenu();
                }

                else{
                    JOptionPane.showMessageDialog(null, "Evolution Failed!");
                }
            }
        });

        panel.add(poke1);
        panel.add(poke2);

        panel.add(poke1Name);
        panel.add(poke2Name);
        
        panel.add(poke1EL);
        panel.add(poke2EL);

        buttons.add(evolve1Button);
        buttons.add(evolve2Button);
        buttons.add(backButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(buttons, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    /**
    * Has all of the pokemons listed in a dropdown list
    * Part of the evolution feature.
    */
    public void choosePokeEvo1(){
        JFrame frame;
        frame = new JFrame("Pokemon Master");
        JPanel panel = new JPanel();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 250);

        JLabel name = new JLabel("Choose the pokemon:");
        
        ArrayList<String> options = inv.getNames();
        JComboBox<String> dropdown = new JComboBox<>(options.toArray(new String[0]));
        
        JLabel imageDisplay = new JLabel();
        updateImageLabel(imageDisplay ,(String) dropdown.getSelectedItem());

        dropdown.addItemListener(new ItemListener(){
            @Override
            
            public void itemStateChanged(ItemEvent e){
                if (e.getStateChange() == ItemEvent.SELECTED){
                    // imageDisplay.setText((String) e.getItem());
                    updateImageLabel(imageDisplay ,(String) e.getItem());
                    // updateChosen1((String) e.getItem());
                }
            }
        });

        JButton submitButton = new JButton("Choose");

        panel.add(name);      
        panel.add(dropdown);
        panel.add(submitButton);
        panel.add(imageDisplay);

        submitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                updateChosen1((String) dropdown.getSelectedItem());
                frame.dispose();
                evolveWindow();
            }
        });

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
    * Has all of the pokemons listed in a dropdown list
    * Part of the evolution feature.
    */
    public void choosePokeEvo2(){
        JFrame frame;        
        frame = new JFrame("Pokemon Master");
        JPanel panel = new JPanel();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 250);

        JLabel name = new JLabel("Choose the pokemon:");

        

        ArrayList<String> options = inv.getNames();
        JComboBox<String> dropdown = new JComboBox<>(options.toArray(new String[0]));
        
        JLabel imageDisplay = new JLabel();
        updateImageLabel(imageDisplay ,(String) dropdown.getSelectedItem());
        

        dropdown.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                if (e.getStateChange() == ItemEvent.SELECTED){
                    // imageDisplay.setText((String) e.getItem());
                    updateImageLabel(imageDisplay ,(String) e.getItem());
                    // updateChosen2((String) e.getItem());
                }
            }
        });

        JButton submitButton = new JButton("Choose");

        panel.add(name);      
        panel.add(dropdown);
        panel.add(submitButton);
        panel.add(imageDisplay);

        submitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                updateChosen2((String) dropdown.getSelectedItem());                
                frame.dispose();
                evolveWindow();
            }
        });

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    /**
    * It is called upon when the 40% chance to have a battle is hit. It contains all of the
    * information of the current enemy and the current active pokemon of the user.
    * Part of the battle phase feature.
    * @param enemy - the enemy in the current battle
    */
    public void battleWindow(pokemons enemy){
        JFrame frame = new JFrame("Pokemon Master");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());       

        JPanel playerPanel = new JPanel();
        JPanel enemyPanel = new JPanel();

        JProgressBar healthBar = new JProgressBar(0, 50);
        healthBar.setValue(cEHP);
        healthBar.setStringPainted(true);

        JLabel enemyImage = new JLabel();
        enemyImage.setIcon(new ImageIcon(getImagePath(enemy.getName())));

        JLabel enemyName = new JLabel("Enemy: " + enemy.getName());
        JLabel enemyLevel = new JLabel("Level: " + enemy.getLevel());
        JLabel enemyType = new JLabel("Type: " + enemy.getType());

        JLabel activePokeImage = new JLabel();
        activePokeImage.setIcon(new ImageIcon(getImagePath(inv.getActivePokemon().getName())));

        JLabel activePokeName = new JLabel("Name: " + inv.getActivePokemon().getName());
        JLabel activePokeLevel = new JLabel("Level: " + inv.getActivePokemon().getLevel());
        JLabel activePokeType = new JLabel("Type: " + inv.getActivePokemon().getType());

        JButton atkBtn = new JButton("Attack");
        JButton swapBtn = new JButton("Swap");
        JButton catchBtn = new JButton("Catch");
        JButton runBtn = new JButton("Run");

        

        atkBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                atkEnemy(inv.getActivePokemon(), healthBar);
                if(cEHP == 0 || (cEHP == 0 && ctr == 3)){
                    JOptionPane.showMessageDialog(null, "You defeated the enemy!");
                    frame.dispose();
                    cEHP = 50;
                    ctr = 0;
                }

                else if(ctr == 3){
                    JOptionPane.showMessageDialog(null, "The enemy ran away!");
                    frame.dispose();
                    cEHP = 50;
                    ctr = 0;
                }
            }
        });

        swapBtn.addActionListener(new ActionListener(){
            @Override 
            
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                if(ctr != 3){
                    changeActiveWindow2();
                }
                else{
                    JOptionPane.showMessageDialog(null, "The enemy ran away!");
                    frame.dispose();
                    ctr = 0;
                }
                
            }
        });

        catchBtn.addActionListener(new ActionListener(){
            @Override 
            
            public void actionPerformed(ActionEvent e){
                if(ctr != 3){
                    int i = catchEnemy();

                    if(i == 1){ // Successful catch
                        frame.dispose();
                        ctr = 0;
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "The enemy ran away!");
                    frame.dispose();
                    ctr = 0;
                }
                
            }
        });
        

        runBtn.addActionListener(new ActionListener(){
            @Override 
            
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Ran away successfully!");
                frame.dispose();
            }
        });

        

        enemyPanel.add(healthBar);
        enemyPanel.add(enemyImage);
        enemyPanel.add(enemyName);
        enemyPanel.add(enemyLevel);
        enemyPanel.add(enemyType);

        playerPanel.add(activePokeImage);
        
        playerPanel.add(activePokeName);
        playerPanel.add(activePokeLevel);
        playerPanel.add(activePokeType);
        playerPanel.add(atkBtn);
        if(inv.inventory.size() >= 2){
            playerPanel.add(swapBtn);
        }
        playerPanel.add(catchBtn);
        playerPanel.add(runBtn);

        frame.add(enemyPanel, BorderLayout.CENTER);
        frame.add(playerPanel, BorderLayout.SOUTH);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
    * Deducts the health of the enemy based on the calculations provided for the attack
    * Part of the battle phasefeature.
    * @param active - the active pokemon of the user
    * @param healthbar - The JProgressBar that shows the health of the enemy
    */
    public void atkEnemy(pokemons active, JProgressBar healthbar){
        ctr++;
        int dmg = active.getLevel() * new Random().nextInt(1,10), ad = 0;
        
        if((active.getType() == "Fire" && ene.getType() == "Grass") || (active.getType() == "Water" && ene.getType() == "Fire") || (active.getType() == "Grass" && ene.getType() == "Water")){
                dmg *= 1.5;
                ad = 1;
        }
        cEHP -= dmg;

        if (cEHP < 0) {
            cEHP = 0;
        }

        healthbar.setValue(cEHP);
        healthbar.setString("Current Health: " + cEHP);
        if(ad == 1){
            JOptionPane.showMessageDialog(null, "(1.5x) Super effective damage: " + dmg);
        }
        else{
            JOptionPane.showMessageDialog(null, "Damage: " + dmg);
        }
        
    }

    /**
    * Catches (puts into inventory) the enemy based on the calculations provided for the attack
    * Part of the battle phase feature.
    * @return 0 for failure, 1 for success
    */
    public int catchEnemy(){
        int rate = new Random().nextInt(1, 100), catchRate = 90;
        catchRate -= cEHP;
        if(rate < catchRate){
            inv.addPokemon(ene);
            JOptionPane.showMessageDialog(null, "Successfully catched the enemy!");
            return 1;
        }


        JOptionPane.showMessageDialog(null, "Failed!");
        ctr++;
        return 0;
    }

    /**
    * The window of the 5x1 area also known as Area 1
    * Part of the area exploration feature.
    */
    public void area1Window(){
        JFrame frame = new JFrame("Pokemon Master");
        area1 a1 = new area1();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        List<pokemons> lvl1 = new ArrayList<>();

        for(pokemons poke : allPokemonList){
            if(poke.getLevel() == 1){
                lvl1.add(poke);
            }
        }

        JPanel buttons = new JPanel();

        JButton leftButton = new JButton("Left");
        JButton rightButton = new JButton("Right");
        JButton backButton = new JButton("Exit");

        leftButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                
                int i = a1.moveCharacter(-1, 0);
                if(i == 1){
                    if(new Random().nextInt(1, 100) < 40){
                        int a = new Random().nextInt(0, lvl1.size());
                        ene = lvl1.get(a);
                        battleWindow(ene);
                    }
                }
            }
        });

        rightButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                int i = a1.moveCharacter(1, 0);
                if(i == 1){
                    if(new Random().nextInt(1, 100) < 40){
                        int a = new Random().nextInt(0, lvl1.size());
                        ene = lvl1.get(a);
                        battleWindow(ene);
                    }

                }
            }
        });

        

        backButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                exploreWindow();
            }
        });
        
        buttons.add(leftButton);
        buttons.add(rightButton);
        buttons.add(backButton);


        frame.add(a1);
        frame.add(buttons, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    /**
    * The window of the 3x3 area also known as Area 2
    * Part of the area exploration feature.
    */
    public void area2Window(){
        JFrame frame = new JFrame("Pokemon Master");
        area2 a2 = new area2();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        List<pokemons> lvl1 = new ArrayList<>();

        for(pokemons poke : allPokemonList){
            if(poke.getLevel() == 1 || poke.getLevel() == 2){
                lvl1.add(poke);
            }
        }

        JPanel buttons = new JPanel();

        JButton leftButton = new JButton("Left");
        JButton rightButton = new JButton("Right");
        JButton upButton = new JButton("Up");
        JButton downButton = new JButton("Down");
        JButton backButton = new JButton("Exit");

        downButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                
                int i = a2.moveCharacter(0, 1);
                if(i == 1){
                    if(new Random().nextInt(1, 100) < 40){
                        int a = new Random().nextInt(0, lvl1.size());
                        ene = lvl1.get(a);
                        battleWindow(ene);
                    }
                }
            }
        });

        upButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                
                int i = a2.moveCharacter(0, -1);
                if(i == 1){
                    if(new Random().nextInt(1, 100) < 40){
                        int a = new Random().nextInt(0, lvl1.size());
                        ene = lvl1.get(a);
                        battleWindow(ene);
                    }
                }
            }
        });

        leftButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                
                int i = a2.moveCharacter(-1, 0);
                if(i == 1){
                    if(new Random().nextInt(1, 100) < 40){
                        int a = new Random().nextInt(0, lvl1.size());
                        ene = lvl1.get(a);
                        battleWindow(ene);
                    }
                }
            }
        });

        rightButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                int i = a2.moveCharacter(1, 0);
                if(i == 1){
                    if(new Random().nextInt(1, 100) < 40){
                        int a = new Random().nextInt(0, lvl1.size());
                        ene = lvl1.get(a);
                        battleWindow(ene);
                    }

                }
            }
        });

        

        backButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                exploreWindow();
            }
        });
        
        buttons.add(upButton);        
        buttons.add(leftButton);
        buttons.add(rightButton);
        buttons.add(downButton);
        buttons.add(backButton);


        frame.add(a2);
        frame.add(buttons, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    /**
    * The window of the 4x4 area also known as Area 3
    * Part of the area exploration feature.
    */
    public void area3Window(){
        JFrame frame = new JFrame("Pokemon Master");
        area3 a3 = new area3();
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        List<pokemons> lvl1 = new ArrayList<>();

        for(pokemons poke : allPokemonList){
            lvl1.add(poke);
        }

        JPanel buttons = new JPanel();

        JButton leftButton = new JButton("Left");
        JButton rightButton = new JButton("Right");
        JButton upButton = new JButton("Up");
        JButton downButton = new JButton("Down");
        JButton backButton = new JButton("Exit");

        downButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                
                int i = a3.moveCharacter(0, 1);
                if(i == 1){
                    if(new Random().nextInt(1, 100) < 40){
                        int a = new Random().nextInt(0, lvl1.size());
                        ene = lvl1.get(a);
                        battleWindow(ene);
                    }
                }
            }
        });

        upButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                
                int i = a3.moveCharacter(0, -1);
                if(i == 1){
                    if(new Random().nextInt(1, 100) < 40){
                        int a = new Random().nextInt(0, lvl1.size());
                        ene = lvl1.get(a);
                        battleWindow(ene);
                    }
                }
            }
        });

        leftButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                
                int i = a3.moveCharacter(-1, 0);
                if(i == 1){
                    if(new Random().nextInt(1, 100) < 40){
                        int a = new Random().nextInt(0, lvl1.size());
                        ene = lvl1.get(a);
                        battleWindow(ene);
                    }
                }
            }
        });

        rightButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                int i = a3.moveCharacter(1, 0);
                if(i == 1){
                    if(new Random().nextInt(1, 100) < 40){
                        int a = new Random().nextInt(0, lvl1.size());
                        ene = lvl1.get(a);
                        battleWindow(ene);
                    }

                }
            }
        });

        

        backButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                exploreWindow();
            }
        });
        
        buttons.add(upButton);        
        buttons.add(leftButton);
        buttons.add(rightButton);
        buttons.add(downButton);
        buttons.add(backButton);


        frame.add(a3);
        frame.add(buttons, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}

class misc{
    private List<pokemons> allPokemonList = pokemons.pokeList();
    public pokemons chosen1 = null, chosen2 = null;
    /* 
    public String getListAsString(List<pokemons> a){
        StringBuilder result = new StringBuilder();
        for(pokemons pokemon : a){
            result  .append("<img src='").append(getImagePath(pokemon.getName())).append("'>")
                    .append("Name: ").append(pokemon.getName())
                    .append(" | EL: ").append(pokemon.getLevel())
                    .append(" | Type: ").append(pokemon.getType())
                    .append(" | Family: ").append(pokemon.getFamily())
                    .append("<br>");
        }
        return "<html>" + result.toString() + "</html>"; // Enable HTML rendering for line breaks
    }
     */
    
    /**
    * Converts the name of the pokemon into a filepath to get their image
    * @param name - The string containing name of the pokemon
    * @return imagePath - The string containing filpath of the pokemons' image file
    */
    public String getImagePath(String name){
            String imagePath = "src\\" + name + ".png";
            return imagePath;
    }

    /**
    * Converts the name of the pokemon into a filepath to get their image
    * @param imageLabel - The JLabel to update the icon
    * @param selectedItem - The string containing name of the pokemon the is currently selected
    */
    public void updateImageLabel(JLabel imageLabel, String selectedItem){
        String path = getImagePath(selectedItem);
        ImageIcon icon = new ImageIcon(path);
        imageLabel.setIcon(icon);
    }
    
    /**
    * Converts the name of the pokemon into a filepath to get their image
    * @param source - The string of the pokemon name that will be converted into a pokemon datatype
    */
    public void updateChosen1(String source){
        pokemons temp = null;

        for(pokemons p : allPokemonList){
            if(p.getName() == source){
                temp = p;
            }
        }

        chosen1 = temp;
    }

    /**
    * Converts the name of the pokemon into a filepath to get their image
    * @param source - The string of the pokemon name that will be converted into a pokemon datatype
    */
    public void updateChosen2(String source){
        pokemons temp = null;

        for(pokemons p : allPokemonList){
            if(p.getName() == source){
                temp = p;
            }
        }

        chosen2 = temp;
    }

}

class map extends JPanel{
    private int mapSize;
    private int pPosX;
    private int pPosY;

    /**
    * A contructor for the superclass to display areas 2 and 3
    * @param size - The size of the grid area
    */
    public map(int size){
        this.mapSize = size;
        this.pPosX = 0;
        this.pPosY = 0;
    }

    /**
    * A method to make the character move inside the area
    * @param dx - The number that will be added to make the user left or right
    * @param dy - The number that will be added to make the user up or down
    */
    public int moveCharacter(int dx, int dy){
        int newCharacterX = pPosX + dx;
        int newCharacterY = pPosY + dy;

        if(newCharacterX >= 0 && newCharacterX < mapSize && newCharacterY >= 0 && newCharacterY < mapSize){
            pPosX = newCharacterX;
            pPosY = newCharacterY;
            repaint();
            return 1;            
        }

        return 0;
    }

    /**
    * A method display the area
    * @param g - The display object that has the capability to display the map and the character
    */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellSize = 20; // Adjust the cell size as needed
        int xOffset = (getWidth() - mapSize * cellSize) / 2;
        int yOffset = (getHeight() - mapSize * cellSize) / 2;

        g.setColor(Color.BLACK);
        for(int i = 0; i < mapSize; i++) {
            for(int j = 0; j < mapSize; j++){
                g.drawString("⬛", xOffset + j * cellSize, yOffset + i * cellSize);
            }
        }

        g.setColor(Color.RED);
        g.drawString("⬛", xOffset + pPosX * cellSize, yOffset + pPosY * cellSize);
    }
}

class area1Map extends JPanel{
    private int mapSize;
    private int pPosX;
    private int pPosY;

    /**
    * A contructor for the superclass to display areas 2 and 3
    * @param size - The size of the line
    */
    public area1Map(int size){
        this.mapSize = size;
        this.pPosX = 0;
        this.pPosY = 0;
    }

    /**
    * A method to make the character move inside the area
    * @param dx - The number that will be added to make the user left or right
    * @param dy - The number that will be added to make the user up or down
    */
    public int moveCharacter(int dx, int dy){
        int newCharacterX = pPosX + dx;

        if(newCharacterX >= 0 && newCharacterX < mapSize){
            pPosX = newCharacterX;
            repaint();
            return 1;
        }
        return 0;
    }


    /**
    * A method display the area
    * @param g - The display object that has the capability to display the map and the character
    */
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        int cellSize = 20; // Adjust the cell size as needed
        int xOffset = (getWidth() - mapSize * cellSize) / 2;
        int yOffset = (getHeight() - mapSize * cellSize) / 2;

        g.setColor(Color.BLACK);
        for(int i = 0; i < mapSize; i++) {
            g.drawString("⬛", xOffset + i * cellSize, yOffset);
        }

        g.setColor(Color.RED);
        g.drawString("⬛", xOffset + pPosX * cellSize, yOffset + pPosY * cellSize);
    }
}

class area1 extends area1Map{
    public area1(){
        super(5);
    }
}

class area2 extends map{
    public area2(){
        super(3); // 3x3
    }
}

class area3 extends map{
    public area3(){
        super(4); // 4x4
    }
}

