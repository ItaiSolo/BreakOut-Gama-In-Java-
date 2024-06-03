import javax.swing.JFrame;

public class Game {
    public static void main(String[] args) {
        do {
            // If the game needs to be restarted
            if (Ball.restart == 1) {
                // Reset all blocks to not destroyed
                for (Block block : BrickBoard.blocks) {
                    block.destroyed = false;
                }

                // Close the existing game and pause menus
                Wall.jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                Wall.WindowClose("BreakOut");
                Wall.WindowClose("Pause Menu");

                // Indicate that the game has been restarted
                Wall.restarted = true;
            }

            Ball.notDead = true; // Set the ball to not dead
            Wall playWall = new Wall(); // Create a new Wall object
            Wall.DrawMenu(); // Draw the game menu

            Ball.restart = 4; // Reset the restart flag
            playWall.run(); // Run the game
            Thread thread = new Thread(); // Create a new thread
            thread.start(); // Start the thread
        } while (Ball.restart == 1); // Repeat the loop if the game needs to be restarted
    }
}
