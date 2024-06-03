import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Wall implements Runnable {
    public static JButton[] arrayOfButtons = new JButton[8]; // Array of buttons for the menus
    private static JFrame jFrameMenu = new JFrame("Main Menu"); // Main menu frame
    public static JFrame jFrame = new JFrame("BreakOut"); // Game frame
    public static JFrame jFramePause = new JFrame("Pause Menu"); // Pause menu frame
    public static Ball playBall = new Ball(); // Ball object
    public static PlayerBrick playPlayerBrick = new PlayerBrick(); // PlayerBrick object
    public static BrickBoard playBrickBoard = new BrickBoard(); // BrickBoard object
    private static boolean startPlay; // Flag to check if the game has started
    public static boolean restarted = false; // Flag to check if the game is restarted
    private static boolean paused = false; // Flag to check if the game is paused
    private static int saveX; // Save ball's X position during pause
    private static int saveY; // Save ball's Y position during pause

    // Create the game board and run the game
    public static void DrawBoard() {
        jFrame.setSize(800, 900); // Set size of the game frame
        jFrame.setResizable(false); // Make the game frame non-resizable
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        jFrameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation for menu frame
        jFrame.setVisible(true); // Make the game frame visible
        jFrame.getContentPane().setBackground(new Color(100, 140, 200)); // Set background color
        jFrame.getContentPane().add(playPlayerBrick); // Add player brick to the game frame
        jFrame.getContentPane().add(playBrickBoard); // Add brick board to the game frame
        jFrame.getContentPane().add(playBall); // Add ball to the game frame
    }

    // Close the specified window
    public static void WindowClose(String Frame) {
        if (Frame.equals("Main Menu")) {
            jFrameMenu.dispatchEvent(new WindowEvent(jFrameMenu, WindowEvent.WINDOW_CLOSING));
        } else if (Frame.equals("BreakOut")) {
            jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
        } else if (Frame.equals("Pause Menu")) {
            jFramePause.dispatchEvent(new WindowEvent(jFramePause, WindowEvent.WINDOW_CLOSING));
        } else {
            System.out.println("There is nothing to close");
        }
    }

    // Draw the pause menu
    public static void PauseMenu() {
        jFramePause.setSize(200, 100); // Set size of the pause menu frame
        jFramePause.setResizable(false); // Make the pause menu frame non-resizable
        jFramePause.setVisible(true); // Make the pause menu frame visible
        DrawBoard(); // Draw the game board
        pauseButton(); // Add pause button functionality
    }

    // Draw the main menu
    public static void DrawMenu() {
        if (restarted) {
            for (int i = 0; i < 7; i++) {
                jFrameMenu.remove(arrayOfButtons[i]); // Remove buttons if game is restarted
            }
        }

        startPlay = false; // Set startPlay flag to false
        jFrameMenu.setSize(700, 700); // Set size of the main menu frame
        jFrameMenu.setResizable(false); // Make the main menu frame non-resizable
        jFrameMenu.setLayout(new GridLayout(7, 0)); // Set layout of the main menu frame
        jFrameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        buttonsArray(); // Initialize buttons
        jFrameMenu.setVisible(true); // Make the main menu frame visible
        SetButtonPressedEvent(); // Set actions for buttons
    }

    // Initialize buttons array
    static void buttonsArray() {
        final JButton button1 = new JButton("Start Game");
        final JButton button2 = new JButton("Rules");
        final JButton button3 = new JButton("Change player brick color");
        final JButton button4 = new JButton("Change player brick size");
        final JButton button5 = new JButton("Change ball color");
        final JButton button6 = new JButton("Change background color");
        final JButton button7 = new JButton("Exit");

        arrayOfButtons[0] = button1;
        arrayOfButtons[1] = button2;
        arrayOfButtons[2] = button3;
        arrayOfButtons[3] = button4;
        arrayOfButtons[4] = button5;
        arrayOfButtons[5] = button6;
        arrayOfButtons[6] = button7;

        jFrameMenu.add(button1);
        jFrameMenu.add(button2);
        jFrameMenu.add(button3);
        jFrameMenu.add(button4);
        jFrameMenu.add(button5);
        jFrameMenu.add(button6);
        jFrameMenu.add(button7);

        final JButton pause = new JButton("Pause");
        arrayOfButtons[7] = pause;
        jFramePause.add(pause);
    }

    // Add functionality to the pause button
    public static void pauseButton() {
        arrayOfButtons[7].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!paused) {
                    saveX = Ball.ballBlock.getAddX(); // Save current ball X position
                    saveY = Ball.ballBlock.getAddY(); // Save current ball Y position
                    Ball.ballBlock.setAddX(0); // Stop ball movement
                    Ball.ballBlock.setAddY(0); // Stop ball movement
                    paused = true; // Set paused flag to true
                } else {
                    Ball.ballBlock.setAddX(saveX); // Restore ball X position
                    Ball.ballBlock.setAddY(saveY); // Restore ball Y position
                    paused = false; // Set paused flag to false
                }
            }
        });
    }

    // Set actions for main menu buttons
    public static void SetButtonPressedEvent() {
        arrayOfButtons[0].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jFrameMenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                WindowClose("Main Menu");
                PauseMenu();
                startPlay = true;
            }
        });

        arrayOfButtons[1].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show the rules of the game
                JOptionPane.showMessageDialog(null,
                        "These are the rules:\n" +
                                "1. If the ball hits a brick, it disappears and the player gets a point.\n" +
                                "2. If the ball hits the bottom of the board, the player loses and the game ends.\n" +
                                "3. The player's goal is to prevent the ball from hitting the bottom of the board and hitting all the bricks.\n" +
                                "4. If all the bricks disappear, the player wins.",
                        "",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        arrayOfButtons[2].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Change player brick color
                playPlayerBrick.colorMenuChoose();
            }
        });

        arrayOfButtons[3].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Change player brick size
                playPlayerBrick.sizeMenuChoose();
            }
        });

        arrayOfButtons[4].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Change ball color
                playBall.colorMenuChoose();
            }
        });

        arrayOfButtons[5].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Change background color
                Block.red = (int) (Math.random() * 255);
                Block.green = (int) (Math.random() * 255);
                Block.blue = (int) (Math.random() * 255);
                arrayOfButtons[5].setBackground(new Color(Block.red, Block.green, Block.blue));
            }
        });

        arrayOfButtons[6].addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Exit the game
                jFrameMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                WindowClose("Main Menu");
            }
        });
    }

    // Run the game
    public void run() {
        while (Ball.restart != 1) {
            if (Ball.restart == 0) {
                jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                WindowClose("BreakOut");
            }
            int counter = 0;
            for (Block block : BrickBoard.blocks) {
                if (block.destroyed) {
                    counter++;
                }
                if (counter >= 65) {
                    Ball.ballBlock.setAddX(0);
                    Ball.ballBlock.setAddY(0);
                    JOptionPane.showMessageDialog(null,
                            "You won!!!",
                            "",
                            JOptionPane.INFORMATION_MESSAGE);
                    if (JOptionPane.CANCEL_OPTION == 2 || JOptionPane.OK_OPTION == 0) {
                        WindowClose("BreakOut");
                    }
                }
            }

            playBall.updateBall();
            pauseButton();
            try {
                if (!startPlay) {
                    Thread.sleep(1500); // Very slow speed
                } else {
                    Thread.sleep(Block.speed); // Normal speed
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
