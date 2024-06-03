import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

public class BrickBoard extends JPanel {
    // List to hold all the blocks in the brick board
    public static ArrayList<Block> blocks = new ArrayList<Block>();

    // Constructor to initialize the brick board with blocks
    public BrickBoard() {
        // Add blue blocks in the first row
        for (int i = 0; i < 13; i++) {
            blocks.add(new Block((i * 60 + 2), 0, 60, 25, "blue.png"));
        }
        // Add blue blocks in the second row
        for (int i = 0; i < 13; i++) {
            blocks.add(new Block((i * 60 + 2), 25, 60, 25, "blue.png"));
        }
        // Add red blocks in the third row
        for (int i = 0; i < 13; i++) {
            blocks.add(new Block((i * 60 + 2), 50, 60, 25, "red.png"));
        }
        // Add green blocks in the fourth row
        for (int i = 0; i < 13; i++) {
            blocks.add(new Block((i * 60 + 2), 75, 60, 25, "green.png"));
        }
        // Add yellow blocks in the fifth row
        for (int i = 0; i < 13; i++) {
            blocks.add(new Block((i * 60 + 2), 100, 60, 25, "yellow.png"));
        }
    }

    // Paint the brick board by drawing each block
    public void paint(Graphics g) {
        for (Block b : blocks) {
            b.drawBrickBoard(g, this); // Draw each block
        }
    }
}
