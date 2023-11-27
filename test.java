import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class test {

    public static void main(String[] args){
        try {
            // Load a downloaded image from a file into a BufferedImage
            File imageFile = new File("download.png");

            if (!imageFile.exists()) {
                System.err.println("Error: Image file not found.");
                return;
            }
            BufferedImage downloadedImage = ImageIO.read(imageFile);

            // Display the downloaded image using a JFrame and JLabel
            JFrame frame = new JFrame("Display Downloaded Image");
            JLabel label = new JLabel(new ImageIcon(downloadedImage));
            frame.getContentPane().add(label);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
