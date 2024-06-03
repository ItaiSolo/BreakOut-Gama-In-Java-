import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Ball extends JPanel implements ActionListener {

    // Static block representing the ball, starting at coordinates (400, 650) with size (20, 20)
    // Replace "blackball.png" with the correct path to your image file
    public static Block ballBlock = new Block(400, 650, 20, 20, "blackball.png");
    public static boolean notDead = true; // Indicates whether the ball is still in play
    public static int restart = 4; // A flag for game restart status, initialized to a non-0 or 1 value
    private JFrame colorFrame = new JFrame("Ball color choose"); // Frame for choosing ball color
    public JRadioButton[] colorRButtons = new JRadioButton[5]; // Array of radio buttons for color selection
    private final String[] imageName = { "redball.png", "blueball.png", "greenball.png", "yellowball.png", "blackball.png" }; // Array of ball image file names

    // Paint the ball and game components
    public void paint(Graphics g) {
        ballBlock.draw(g, this); // Draw the ball
        Wall.playBrickBoard.paint(g); // Draw the brick board
        Wall.playPlayerBrick.paint(g); // Draw the player's brick
    }

    // Handle the game lost scenario
    public void gameLost() {
        // Show a dialog asking if the player wants to restart the game
        restart = JOptionPane.showConfirmDialog(this, "You Lost The Game, would you like to restart?", "Game Lost", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (restart == JOptionPane.YES_OPTION) {
            restart = 1; // Set restart flag to 1 if the player chooses to restart
        } else if (restart == JOptionPane.NO_OPTION) {
            restart = 0; // Set restart flag to 0 if the player chooses not to restart
        } else if (JOptionPane.CANCEL_OPTION == 2) {
            gameLost(); // Recursively call gameLost() if the cancel option is selected
        }
    }

    // Update the ball's position and handle collisions
    public void updateBall() {
        if (notDead) {
            int size = 25; // Size of the ball
            ballBlock.x = ballBlock.getAddX() + ballBlock.x; // Update ball's x position

            // Handle collisions with the walls
            if (ballBlock.x > (getWidth() - size) || ballBlock.x < 0) {
                ballBlock.setAddX(-1 * ballBlock.getAddX()); // Reverse x direction on collision
            }

            // Handle collisions with the ceiling and the player's paddle
            if (ballBlock.y < 0 || ballBlock.intersects(PlayerBrick.paddle)) {
                ballBlock.setAddY(-1 * ballBlock.getAddY()); // Reverse y direction on collision
            }

            // Handle collision with the floor
            if (ballBlock.y > 950) {
                notDead = false; // Mark the ball as dead
                gameLost(); // Trigger the game lost scenario
                // Reset ball position and direction
                ballBlock.setPlaceX(400);
                ballBlock.setPlaceY(650);
                ballBlock.setAddX(3);
                ballBlock.setAddY(-3);
            }

            ballBlock.y = ballBlock.getAddY() + ballBlock.y; // Update ball's y position

            // Handle collisions with the bricks
            for (Block block : BrickBoard.blocks) {
                if ((block.left.intersects(ballBlock) || block.right.intersects(ballBlock)) && !block.destroyed) {
                    ballBlock.setAddX(-1 * ballBlock.getAddY()); // Reverse x direction on collision
                    block.destroyed = true; // Mark the block as destroyed
                } else if (ballBlock.intersects(block) && !block.destroyed) {
                    block.destroyed = true; // Mark the block as destroyed
                    ballBlock.setAddY(-1 * ballBlock.getAddY()); // Reverse y direction on collision
                }
            }
            repaint(); // Repaint the game panel
        }
    }

    // Display the color selection menu
    public void colorMenuChoose() {
        Font myFont = new Font("Courier", Font.BOLD | Font.ITALIC, 18); // Set font for the label
        this.colorFrame.setSize(500, 500); // Set size of the color selection frame
        this.colorFrame.setResizable(false); // Make the frame non-resizable
        this.colorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close frame on dispose

        // Create radio buttons for color selection
        JRadioButton redButton = new JRadioButton("RED", true);
        JRadioButton blueButton = new JRadioButton("BLUE");
        JRadioButton greenButton = new JRadioButton("GREEN");
        JRadioButton yellowButton = new JRadioButton("YELLOW");
        JRadioButton blackButton = new JRadioButton("BLACK");

        // Create and set properties for the label
        Label l = new Label("Which color do you want for your ball?");
        l.setFont(myFont);
        l.setBounds(50, 30, 400, 100);
        this.colorFrame.add(l);

        // Create a button group and add radio buttons to it
        ButtonGroup bg = new ButtonGroup();
        redButton.setBounds(100, 150, 100, 30);
        blueButton.setBounds(100, 200, 100, 30);
        greenButton.setBounds(100, 250, 100, 30);
        yellowButton.setBounds(100, 300, 100, 30);
        blackButton.setBounds(100, 350, 100, 30);

        this.colorFrame.setLayout(null);
        bg.add(redButton);
        bg.add(blueButton);
        bg.add(greenButton);
        bg.add(yellowButton);
        bg.add(blackButton);

        // Add radio buttons to the frame
        this.colorFrame.add(redButton);
        this.colorFrame.add(blueButton);
        this.colorFrame.add(greenButton);
        this.colorFrame.add(yellowButton);
        this.colorFrame.add(blackButton);

        // Store radio buttons in an array for easy access
        this.colorRButtons[0] = redButton;
        this.colorRButtons[1] = blueButton;
        this.colorRButtons[2] = greenButton;
        this.colorRButtons[3] = yellowButton;
        this.colorRButtons[4] = blackButton;

        // Create and add an OK button to the frame
        JButton ok = new JButton("OK");
        ok.setBounds(200, 430, 80, 30);
        this.colorFrame.add(ok);
        ok.addActionListener(this); // Add action listener to the OK button
        this.colorFrame.setVisible(true); // Make the frame visible
    }

    // Handle action events for the color selection
    public void actionPerformed(ActionEvent e) {
        System.out.println("action ball");
        boolean isContinue = true;
        for (int i = 0; i < this.colorRButtons.length && isContinue; i++) {
            if (this.colorRButtons[i].isSelected()) {
                System.out.println("selected!!!");
                ballBlock.setImage(this.imageName[i]); // Set the image of the ball based on selection
                this.colorFrame.dispatchEvent(new WindowEvent(this.colorFrame, WindowEvent.WINDOW_CLOSING)); // Close the color selection frame
                this.colorRButtons[i].setSelected(false); // Deselect the radio button
                isContinue = false; // Exit the loop
            }
        }
    }
}
