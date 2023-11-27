import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class gui extends misc{
    public inventory inv = new inventory();
    public List<pokemons> allPokemonList = pokemons.pokeList();
   
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
                        inv.setActivePokemon(pokemon);
                        inv.addPokemon(allPokemonList.get(18));
                        inv.addPokemon(allPokemonList.get(22));
                        inv.addPokemon(allPokemonList.get(3));
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

        
      
        /* 
        expButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                exploreWindow();

                
            }
        });
        */

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
        List<pokemons> a = new ArrayList<>();
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
        mainGame a = new mainGame();
        
        frame = new JFrame("Pokemon Master");
        JPanel panel = new JPanel();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 250);

        JLabel name = new JLabel("Choose: ");

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
                        inv.setActivePokemon(pokemon);
                        inv.addPokemon(allPokemonList.get(18));
                        inv.addPokemon(allPokemonList.get(22));
                        inv.addPokemon(allPokemonList.get(3));
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
                Area1Window();
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

    public void Area1Window(){
        JFrame frame;
        
        frame = new JFrame("Pokemon Master");
        JPanel panel = new JPanel(new GridLayout(4, 1));

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);




        
        JButton backButton = new JButton("Back");

        
        backButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                exploreWindow();
            }
        });

        panel.add(backButton);


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
}
