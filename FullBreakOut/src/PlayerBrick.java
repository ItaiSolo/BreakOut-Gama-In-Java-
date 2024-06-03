import java.awt.Font;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PlayerBrick extends JPanel implements KeyListener, ActionListener {

    public static Block paddle = new Block(400, 700, 150, 25, "black.png"); // Paddle block for the player
    private JFrame colorFrame; // Frame for color selection menu
    private JFrame sizeFrame; // Frame for size selection menu
    public JRadioButton[] colorRButtons = new JRadioButton[5]; // Radio buttons for color selection
    public JRadioButton[] sizeButtons = new JRadioButton[3]; // Radio buttons for size selection
    private final String[] imageName = {"red.png", "blue.png", "green.png", "yellow.png", "black.png"}; // Array of image file names for paddle colors
    Font myFont = new Font("Courier", Font.BOLD | Font.ITALIC, 18); // Font for labels
    private final int[] paddleWidth = {70, 110, 150}; // Array of paddle widths
    boolean swColorMenu = false; // Flag to indicate if the color menu is active

    public PlayerBrick() {
        addKeyListener(this); // Add key listener to the panel
        setFocusable(true); // Make the panel focusable
    }

    // Paint the player paddle
    public void paint(Graphics g) {
        paddle.drawPlayerBrick(g, this); // Draw the player paddle
    }

    // Update the player paddle's position based on key events
    public void updatePlayerBrick(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x > 0) {
            paddle.x -= 15; // Move paddle left
        }
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (800 - paddle.width - 15)) {
            paddle.x += 15; // Move paddle right
        }
        repaint(); // Repaint the panel
    }

    // Handle key press events
    public void keyPressed(KeyEvent e) {
        updatePlayerBrick(e);
    }

    public void keyTyped(KeyEvent e) {
        // Not used
    }

    public void keyReleased(KeyEvent e) {
        // Not used
    }

    // Close the specified menu window
    private void closeMenuWindow(String frameName) {
        if (frameName.equals("Color Menu")) {
            this.colorFrame.dispatchEvent(new WindowEvent(this.colorFrame, WindowEvent.WINDOW_CLOSING));
        } else if (frameName.equals("Size Menu")) {
            this.sizeFrame.dispatchEvent(new WindowEvent(this.sizeFrame, WindowEvent.WINDOW_CLOSING));
        } else {
            System.out.println("There is nothing to close");
        }
    }

    // Display the color selection menu
    public void colorMenuChoose() {
        this.swColorMenu = true;
        this.colorFrame = new JFrame("Paddle color choose");
        this.colorFrame.setSize(500, 500);
        this.colorFrame.setResizable(false);
        this.colorFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create radio buttons for color selection
        JRadioButton redButton = new JRadioButton("RED");
        JRadioButton blueButton = new JRadioButton("BLUE");
        JRadioButton greenButton = new JRadioButton("GREEN");
        JRadioButton yellowButton = new JRadioButton("YELLOW");
        JRadioButton blackButton = new JRadioButton("BLACK", true);

        // Create and set properties for the label
        Label l = new Label("Which color do you want for your paddle?");
        l.setFont(this.myFont);
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

    // Display the size selection menu
    public void sizeMenuChoose() {
        this.swColorMenu = false;
        this.sizeFrame = new JFrame("Paddle size choose");
        this.sizeFrame.setSize(400, 400);
        this.sizeFrame.setResizable(false);
        this.sizeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Create radio buttons for size selection
        JRadioButton smallJRButton = new JRadioButton("SMALL");
        JRadioButton mediumJRButton = new JRadioButton("MEDIUM");
        JRadioButton bigJRButton = new JRadioButton("BIG", true);

        // Create and set properties for the label
        Label l = new Label("Which size do you want for your paddle?");
        l.setFont(this.myFont);
        l.setBounds(20, 20, 400, 100);
        this.sizeFrame.add(l);

        // Create a button group and add radio buttons to it
        ButtonGroup bg = new ButtonGroup();
        smallJRButton.setBounds(100, 140, 100, 30);
        mediumJRButton.setBounds(100, 190, 100, 30);
        bigJRButton.setBounds(100, 240, 100, 30);

        this.sizeFrame.setLayout(null);
        bg.add(smallJRButton);
        bg.add(mediumJRButton);
        bg.add(bigJRButton);

        // Add radio buttons to the frame
        this.sizeFrame.add(smallJRButton);
        this.sizeFrame.add(mediumJRButton);
        this.sizeFrame.add(bigJRButton);

        // Store radio buttons in an array for easy access
        this.sizeButtons[0] = smallJRButton;
        this.sizeButtons[1] = mediumJRButton;
        this.sizeButtons[2] = bigJRButton;

        // Create and add an OK button to the frame
        JButton ok = new JButton("OK");
        ok.setBounds(150, 320, 80, 30);
        this.sizeFrame.add(ok);
        ok.addActionListener(this);
        this.sizeFrame.setVisible(true);
    }

    // Handle action events for the menus
    public void actionPerformed(ActionEvent e) {
        boolean isContinue = true;
        if (this.swColorMenu) {
            for (int i = 0; i < this.colorRButtons.length && isContinue; i++) {
                if (this.colorRButtons[i].isSelected()) {
                    paddle.setImage(this.imageName[i]); // Set the paddle image based on selection
                    closeMenuWindow("Color Menu"); // Close the color menu window
                    isContinue = false;
                }
            }
        } else {
            for (int i = 0; i < this.sizeButtons.length && isContinue; i++) {
                if (this.sizeButtons[i].isSelected()) {
                    paddle.setWidth(this.paddleWidth[i]); // Set the paddle width based on selection
                    closeMenuWindow("Size Menu"); // Close the size menu window
                    isContinue = false;
                }
            }
        }
    }
}
