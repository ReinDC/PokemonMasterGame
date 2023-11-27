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
                JOptionPane.showMessageDialog(null, (String) dropdown.getSelectedItem());
                for(pokemons pokemon : allPokemonList){
                    if(pokemon.getName() == (String) dropdown.getSelectedItem()){
                        inv.addPokemon(pokemon);
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
        JFrame frame;
        
        frame = new JFrame("Pokemon Master");
        JPanel panel = new JPanel();
        JPanel buttons = new JPanel();

        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);


        JLabel invLabel = new JLabel(getListAsString(inventory));
        JScrollPane scrollPane = new JScrollPane(invLabel);

        JButton changeActiveButton = new JButton("Change active pokemon");
        JButton backButton = new JButton("Back");

        backButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                mainMenu();
            }
        });

        panel.add(scrollPane);

        buttons.add(changeActiveButton);
        buttons.add(backButton);


        frame.add(panel);
        frame.add(buttons, BorderLayout.SOUTH);

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
