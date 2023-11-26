import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class gui{

    public void chooseStarter(){
        JFrame frame;
        final String chosenStarter;
        
        frame = new JFrame("Pokemon Master");
        JPanel panel = new JPanel();

        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 250);

        mainGame a = new mainGame();

        JLabel name = new JLabel();
        name.setText("Choose a starter: ");

        ArrayList<String> options = a.loadNameLevel1();
        JComboBox<String> dropdown = new JComboBox<>(options.toArray(new String[0]));

        JButton submitButton = new JButton("Choose");
                

        panel.add(name);        
        panel.add(dropdown);
        panel.add(submitButton);

        
        chosenStarter = (String) dropdown.getSelectedItem();

        submitButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){                
                JOptionPane.showMessageDialog(null, "You have selected the pokemon:" + chosenStarter);
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
        invButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                inventoryWindow();

                
            }
        });
        */

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
        JFrame frame;
        
        frame = new JFrame("Pokemon Master");
        JPanel panel = new JPanel();
        JPanel buttons = new JPanel();

        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 200);

        

        JButton changeActiveButton = new JButton("Change active pokemon");
        JButton backButton = new JButton("Back");





        backButton.addActionListener(new ActionListener(){
            @Override
            
            public void actionPerformed(ActionEvent e){
                frame.dispose();
                mainMenu();

                
            }
        });

        buttons.add(changeActiveButton);
        buttons.add(backButton);


        frame.add(panel);
        frame.add(buttons, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    public static void main(String[] args){
        gui a = new gui();
        a.chooseStarter();
    }    
}
