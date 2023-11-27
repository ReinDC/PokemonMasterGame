import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class gui extends misc{
    private inventory inv = new inventory();
    private List<pokemons> allPokemonList = pokemons.pokeList();
   
    public void chooseStarter(){
        JFrame frame;
        mainGame a = new mainGame();
        
        frame = new JFrame("Pokemon Master");
        JPanel panel = new JPanel();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 250);

        JLabel name = new JLabel("Choose a starter:");

        ArrayList<String> options = a.loadNameLevel1();
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

        buttons.add(changeActiveButton);
        buttons.add(backButton);

        frame.add(scrollPane);
        frame.add(buttons, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

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
                // Area1Window();
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
                updateChosen1(null);
                updateChosen2(null);
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
                    inv.removePokemon(chosen1);
                    inv.removePokemon(chosen2);
                }

                else{
                    JOptionPane.showMessageDialog(null, "Evolution Failed!");
                }
                
                frame.dispose();
                mainMenu();
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
    

    public static void main(String[] args){
        gui a = new gui();
        a.chooseStarter();
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
    
    public String getImagePath(String name){
            String imagePath = "src\\" + name + ".png";
            return imagePath;
    }

    public void updateImageLabel(JLabel imageLabel, String selectedItem){
        String path = getImagePath(selectedItem);
        ImageIcon icon = new ImageIcon(path);
        imageLabel.setIcon(icon);
    }

    public void updateChosen1(String source){
        pokemons temp = null;

        for(pokemons p : allPokemonList){
            if(p.getName() == source){
                temp = p;
            }
        }

        chosen1 = temp;
    }

    public void updateChosen2(String source){
        pokemons temp = null;

        for(pokemons p : allPokemonList){
            if(p.getName() == source){
                temp = p;
            }
        }

        chosen2 = temp;
    }

    public void getChosen1(pokemons dest, pokemons chosen1){
        dest = chosen1;
    }

    public void getChosen2(pokemons dest, pokemons chosen2){
        dest = chosen2;
    }
}
