import javax.swing.*;
import java.awt.*;
import java.util.*;

class MapPanel extends JPanel{
    private int GRID_SIZE;
    private int characterX;
    private int characterY;
    private gui g = new gui();
    

    public MapPanel(int gridSize){
        this.GRID_SIZE = gridSize;
        this.characterX = 0;
        this.characterY = 0;
    }

    public void moveCharacter(int dx, int dy){
        int newCharacterX = characterX + dx;
        int newCharacterY = characterY + dy;
        int battle;

        // Check boundaries
        if(newCharacterX >= 0 && newCharacterX < GRID_SIZE && newCharacterY >= 0 && newCharacterY < GRID_SIZE){
            characterX = newCharacterX;
            characterY = newCharacterY;
            battle = new Random().nextInt(1, 100);
            repaint();
            
            if(battle < 40){
                g.showCustomPanel();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int cellSize = 20; // Adjust the cell size as needed
        int xOffset = (getWidth() - GRID_SIZE * cellSize) / 2;
        int yOffset = (getHeight() - GRID_SIZE * cellSize) / 2;

        g.setColor(Color.BLACK);
        for(int i = 0; i < GRID_SIZE; i++) {
            for(int j = 0; j < GRID_SIZE; j++){
                g.drawString("0", xOffset + j * cellSize, yOffset + i * cellSize);
            }
        }

        g.setColor(Color.RED);
        g.drawString("C", xOffset + characterX * cellSize, yOffset + characterY * cellSize);
    }
}

class area3 extends MapPanel {
    public area3() {
        super(4);
    }
}

class area2 extends MapPanel {
    public area2() {
        super(3);
    }
}

public class MapTest {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Map Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // area1 a1 = new area1();
        area2 a2 = new area2();
        area3 a3 = new area3();

        JPanel panel = new JPanel();

        JButton upButton = new JButton("Up");
        JButton downButton = new JButton("Down");
        JButton leftButton = new JButton("Left");
        JButton rightButton = new JButton("Right");

        upButton.addActionListener(e -> a2.moveCharacter(0, -1));
        downButton.addActionListener(e -> a2.moveCharacter(0, 1));
        leftButton.addActionListener(e -> a2.moveCharacter(-1, 0));
        rightButton.addActionListener(e -> a2.moveCharacter(1, 0));

        panel.add(upButton);
        panel.add(downButton);
        panel.add(leftButton);
        panel.add(rightButton);

        // frame.add(a1);
        frame.add(a2);
        frame.add(panel, BorderLayout.SOUTH);
        // frame.add(a3);

        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
